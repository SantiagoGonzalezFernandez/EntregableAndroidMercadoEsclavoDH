package com.santiagogonzalez.mercadoesclavodh.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

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
import com.santiagogonzalez.mercadoesclavodh.controller.ControllerProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;
import com.santiagogonzalez.mercadoesclavodh.view.adapter.AdapterProducto;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.AboutUsFragment;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.FavoritosFragment;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.HomeFragment;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.PerfilFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , HomeFragment.ListenerDelFragment,AdapterProducto.ListenerDelAdapter{

    private Toolbar myToolbar;
    private DrawerLayout myDrawerLayout;
    private NavigationView myNavigationView;

    private SearchView mySearchView;

    private FirebaseAuth myFirebaseAuth;

    private FirebaseUser myFirebaseUser; //El firebaseUser te da las cosas del user

    private BottomNavigationView myBottomNavigationView;

    private ControllerProducto myControllerProducto;

    private AdapterProducto myAdapterProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        myControllerProducto = new ControllerProducto();

        myAdapterProducto = new AdapterProducto(this);

        encuentroComponentesPorId();

        configuroToolbar();

        configuroBottomNavigationView();

        pegarPrimerFragment(new HomeFragment());
    }

    private void encuentroComponentesPorId() {
        myToolbar = findViewById(R.id.MainActivity_Include_Toolbar);
        myDrawerLayout = findViewById(R.id.MainActivity_DrawerLayout_Contenedor);
        myNavigationView = findViewById(R.id.MainActivity_NavigationView);
        myBottomNavigationView = findViewById(R.id.MainActivity_BottomNavigationView);
    }

    private void configuroBottomNavigationView() {
        myBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment myFragmentSelecionado = null;

                switch (menuItem.getItemId()) {
                    case R.id.BottomNavigationView_Item_Home:
                        myFragmentSelecionado = new HomeFragment();
                        break;
                    case R.id.BottomNavigationView_Item_Favoritos:
                        myFragmentSelecionado = new FavoritosFragment();
                        break;
                    case R.id.BottomNavigationView_Item_Perfil:
                        myFragmentSelecionado = new PerfilFragment();
                        break;
                }

                pegarFragment(myFragmentSelecionado);

                return true;
            }
        });
    }

    private void pegarPrimerFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.MainActivity_FrameLayout_ContenedorDeFragments, fragment)
                .commit();
    }

    private void remplazarFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.MainActivity_FrameLayout_ContenedorDeFragments, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void pegarFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.MainActivity_FrameLayout_ContenedorDeFragments, fragment)
                .addToBackStack(null)
                .commit();
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
                remplazarFragment(new HomeFragment());
                break;
            case R.id.MenuPrincipal_Item_Perfil:
                Toast.makeText(this, "Perfil", Toast.LENGTH_LONG).show();
                remplazarFragment(new PerfilFragment());
                break;
            case R.id.MenuPrincipal_Item_AboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_LONG).show();
                remplazarFragment(new AboutUsFragment());
                break;
            case R.id.MenuPrincipal_Item_CerrarSesion:
                Toast.makeText(this, "Cerrar sesion", Toast.LENGTH_LONG).show();
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
                pegarFragment(new HomeFragment());
                myControllerProducto.traerProductoPorBusqueda(query, new ResultListener<List<Producto>>() {
                    @Override
                    public void finish(List<Producto> result) {
                     myAdapterProducto.setMyProductoList(result);
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


    @Override
    public void informarProducto(Producto myProducto) {
        Toast.makeText(this, myProducto.getMyStringTitulo(), Toast.LENGTH_SHORT).show();
        /*FragmentDetallePelicula fragment_detallePelicula = new FragmentDetallePelicula();
        Bundle bundle = new Bundle();
        bundle.putSerializable(fragment_detallePelicula.CLAVE_PELICULA, pelicula);
        fragment_detallePelicula.setArguments(bundle);
        currentFragment = fragment_detallePelicula;
        pegarFragment(fragment_detallePelicula);*/
    }

    @Override
    public void recibirProducto(Producto producto) {
        Toast.makeText(this, producto.getMyStringTitulo(), Toast.LENGTH_SHORT).show();
        /*FragmentDetallePelicula fragment_detallePelicula = new FragmentDetallePelicula();
        Bundle bundle = new Bundle();
        bundle.putSerializable(fragment_detallePelicula.CLAVE_PELICULA, pelicula);
        fragment_detallePelicula.setArguments(bundle);
        currentFragment = fragment_detallePelicula;
        pegarFragment(fragment_detallePelicula);*/
    }
}
