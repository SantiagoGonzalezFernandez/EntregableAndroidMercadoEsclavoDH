package com.santiagogonzalez.mercadoesclavodh.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.controller.ProductoController;
import com.santiagogonzalez.mercadoesclavodh.model.Producto;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoDao;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;
import com.santiagogonzalez.mercadoesclavodh.view.adapter.ProductoRVAdapter;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.AboutUsFragment;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.FavoritosFragment;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.PerfilFragment;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.SobreDesarrolladorFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ProductoRVAdapter.ListenerDelAdapter {

    private Toolbar myToolbar;
    private DrawerLayout myDrawerLayout;
    private NavigationView myNavigationView;

    private SearchView mySearchView;

    private FirebaseAuth myFirebaseAuth;

    private FirebaseUser myFirebaseUser; //El firebaseUser te da las cosas del user

    private BottomNavigationView myBottomNavigationView;

    private ProductoController myControllerProducto;

    private ProductoRVAdapter myAdapterProducto;

    private RecyclerView myRecyclerViewProductos;

    private List<Producto> myListProdutoVacia;

    private Fragment myFragmentActual;

    private String productoBuscado;

    private Boolean primeraBusqueda = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanciarComponentes();

        encuentroComponentesPorId();

        configuroRecyclerView();

        configuroToolbar();

        configuroBottomNavigationView();
    }

    private void instanciarComponentes() {
        myFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        myControllerProducto = new ProductoController();

        myAdapterProducto = new ProductoRVAdapter(this);

        myListProdutoVacia = new ArrayList<>();
    }

    private void configuroRecyclerView() {

        final LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        DividerItemDecoration myDividerItemDecoration = new DividerItemDecoration(myRecyclerViewProductos.getContext(),
                myLinearLayoutManager.getOrientation());

        myRecyclerViewProductos.addItemDecoration(myDividerItemDecoration);
        myRecyclerViewProductos.setLayoutManager(myLinearLayoutManager);
        myRecyclerViewProductos.setAdapter(myAdapterProducto);
        myRecyclerViewProductos.setHasFixedSize(true);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mySimpleCallback);
        itemTouchHelper.attachToRecyclerView(myRecyclerViewProductos);

        myRecyclerViewProductos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Integer ultimaPosicionVisible = myLinearLayoutManager.findLastVisibleItemPosition(); //Aca encontramos la ultima posicion visible
                Integer ultimoElementoDelRecycler = myLinearLayoutManager.getItemCount(); //Aca encontramos al ultimo elemento del recycler

                //Aca le agregamos la logica de cuando va agregar users
                if(ultimaPosicionVisible >= ultimoElementoDelRecycler - 5){
                    agregarProductos();
                }
            }
        });
    }

    private void encuentroComponentesPorId() {
        myToolbar = findViewById(R.id.MainActivity_Include_Toolbar);
        myDrawerLayout = findViewById(R.id.MainActivity_DrawerLayout_Contenedor);
        myNavigationView = findViewById(R.id.MainActivity_NavigationView);
        myBottomNavigationView = findViewById(R.id.MainActivity_BottomNavigationView);
        myRecyclerViewProductos = findViewById(R.id.MainActivity_RecyclerView_ListaProductos);
    }

    private void configuroBottomNavigationView() {
        myBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.BottomNavigationView_Item_Buscar:
                        removerFragments();
                        break;
                    case R.id.BottomNavigationView_Item_Favoritos:
                        pegarFragment(new FavoritosFragment());
                        break;
                    case R.id.BottomNavigationView_Item_Perfil:
                        pegarFragment(new PerfilFragment());
                        break;
                }

                return true;
            }
        });
    }

    private void removerFragments() {
        if (myFragmentActual != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(getSupportFragmentManager().findFragmentById(R.id.MainActivity_FrameLayout_ContenedorDeFragment))
                    .commit();
        }
    }

    private void pegarFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivity_FrameLayout_ContenedorDeFragment, fragment)
                .addToBackStack(null)
                .commit();
        myFragmentActual = fragment;
        myAdapterProducto.actualizarLista(myListProdutoVacia);
    }

    private void configuroToolbar() {
        setSupportActionBar(myToolbar);

        ActionBarDrawerToggle myActionBarDrawerToggle =
                new ActionBarDrawerToggle(this,
                        myDrawerLayout,
                        myToolbar,
                        R.string.open_drawer,
                        R.string.close_drawer); //Animacion de la hamburguesa (Open, Close Drawer)

        myDrawerLayout.addDrawerListener(myActionBarDrawerToggle);
        myActionBarDrawerToggle.syncState();

        //le seteo el onClick,le digo this por que la MainActivity implementa el listener (NavigationView.OnNavigationItemSelectedListener)
        myNavigationView.setNavigationItemSelectedListener(this);
    }

    //Este metodo se tiene que implementar por (NavigationView.OnNavigationItemSelectedListener) para configurar los items Del NavigationView
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int intItemSeleccionadoId = menuItem.getItemId();

        switch (intItemSeleccionadoId) {
            case R.id.MenuPrincipal_Item_Home:
                Toast.makeText(this, "Home", Toast.LENGTH_LONG).show();
                removerFragments();
                break;
            case R.id.MenuPrincipal_Item_Perfil:
                Toast.makeText(this, "Perfil", Toast.LENGTH_LONG).show();
                pegarFragment(new PerfilFragment());
                break;
            case R.id.MenuPrincipal_Item_AboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_LONG).show();
                pegarFragment(new AboutUsFragment());
                break;
            case R.id.MenuPrincipal_Item_SobreElDesarrollador:
                Toast.makeText(this, "Sobre El Desarrollador", Toast.LENGTH_LONG).show();
                pegarFragment(new SobreDesarrolladorFragment());
                break;
            case R.id.MenuPrincipal_Item_CerrarSesion:
                Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_LONG).show();
                myAdapterProducto.actualizarLista(myListProdutoVacia);
                //Cierro la sesion de firebase y de facebook y abro el LoginActivity
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
        }
        myDrawerLayout.closeDrawers();
        return true;
    }

    //Este metodo te lo da la extencion con AppCompatActivity
    //En este caso lo vamos a usar para configurar una parte de la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflo el toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        //Encuentro los componentes del menu
        MenuItem myMenuItemSearch = menu.findItem(R.id.ToolBarMenu_Item_action_search);
        MenuItem myMenuItemProfile = menu.findItem(R.id.ToolBarMenu_Item_perfilUser);

        //Configuro que pasa si se clickea el Perfil
        myMenuItemProfile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.ToolBarMenu_Item_perfilUser) {
                    Toast.makeText(getApplicationContext(), "Perfil", Toast.LENGTH_SHORT).show();
                    pegarFragment(new PerfilFragment());
                }
                return true;
            }
        });

        mySearchView = (SearchView) myMenuItemSearch.getActionView();
        mySearchView.setQueryHint("Que buscas?");

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productoBuscado = query;
                Bundle myBundle = new Bundle();
                myBundle.putString(ProductoDao.PRODUCTO_SELECCIONADO, query);
                myControllerProducto = new ProductoController();
                myControllerProducto.traerProductoPorBusqueda(query, new ResultListener<List<Producto>>() {
                    @Override
                    public void finish(List<Producto> result) {
                        myAdapterProducto.setMyProductoList(result);
                        agregarProductos();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }

    //Este metodo setea nuevos Productos
    public void agregarProductos() {
        if(!primeraBusqueda){
            //Le pedimos al controller que traiga users
            myControllerProducto.obtenerResultadoController(myAdapterProducto.getItemCount(), productoBuscado,new ResultListener<ProductoContainer>() {
                @Override
                public void finish(ProductoContainer result) {
                    //Despues le seteamos una nueva lista al adapter segun los resultado que vinieron asincronicamente del controller
                    myAdapterProducto.addProductoList(result.getResults());
                    Toast.makeText(MainActivity.this, "Mira, ahora hay mas...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void informarProducto(Producto producto) {
        //Creamos el intent para poder cambiar de activity
        Intent myIntent = new Intent(MainActivity.this, DetalleProductoActivity.class);
        //Luego creamos el bundle para poder llevar informacion dentro
        Bundle myBundle = new Bundle();
        //Le ponemos la key y el user
        myBundle.putSerializable(DetalleProductoActivity.KEY_PRODUCTO, producto);
        //Le agregamos al Intent el bundle
        myIntent.putExtras(myBundle);
        //Iniciamos la activity
        startActivity(myIntent);
    }

    ItemTouchHelper.SimpleCallback mySimpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int myIntPosicionActual = viewHolder.getAdapterPosition();
            int myIntParaLaPosicion = target.getAdapterPosition();
            List<Producto> myProductoList = myAdapterProducto.getMyProductoList();

            Collections.swap(myProductoList, myIntPosicionActual, myIntParaLaPosicion);

            recyclerView.getAdapter().notifyItemMoved(myIntPosicionActual, myIntParaLaPosicion);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
}
