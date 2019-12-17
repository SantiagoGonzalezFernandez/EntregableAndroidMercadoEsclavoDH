package com.santiagogonzalez.mercadoesclavodh.view.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.controller.FirestoreController;
import com.santiagogonzalez.mercadoesclavodh.controller.ProductoController;
import com.santiagogonzalez.mercadoesclavodh.model.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;
import com.santiagogonzalez.mercadoesclavodh.view.fragment.PerfilFragment;

import java.util.List;

public class DetalleProductoActivity extends AppCompatActivity {

    public static final String KEY_PRODUCTO = "key_producto";

    private Toolbar myToolbar;

    private ImageView myImageViewImagenDelProducto;
    private TextView myTextViewTituloDelProducto;
    private TextView myTextViewPrecioDelProducto;
    private TextView myTextViewDescripcionDelProducto;

    private Producto myProducto;

    private DescripcioDeProducto myDescripcioDeProducto;

    private ProductoController myProductoController;

    private FirestoreController myFirestoreController;

    private Boolean myBooleanEsFavorita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        encontrarComponentesPorId();

        inizializoLosComponentes();

        configuroToolbar();

        obtengoLosDatosDelIntentYBundle();

        myProductoController.traerDescripcionPorId(myProducto.getId(), new ResultListener<DescripcioDeProducto>() {
            @Override
            public void finish(DescripcioDeProducto results) {
                 myTextViewDescripcionDelProducto.setText(results.getPlain_text());
            }
        });

        configuroLosComponentesConLosDatosDelProducto();

    }

    private void inizializoLosComponentes(){
        myProductoController = new ProductoController();
        myDescripcioDeProducto = new DescripcioDeProducto();
        myFirestoreController = new FirestoreController();
    }

    private void configuroLosComponentesConLosDatosDelProducto(){
        Glide.with(myImageViewImagenDelProducto.getContext()).load(myProducto.getThumbnail()).into(myImageViewImagenDelProducto);
        myTextViewTituloDelProducto.setText(myProducto.getTitle());
        myTextViewPrecioDelProducto.setText("$" + myProducto.getPrice());
    }

    private void obtengoLosDatosDelIntentYBundle(){
        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getExtras();
        myProducto = (Producto) myBundle.getSerializable(KEY_PRODUCTO);
    }

    private void encontrarComponentesPorId() {
        myToolbar = findViewById(R.id.DetalleProductoActivity_Include_Toolbar);

        myImageViewImagenDelProducto = findViewById(R.id.DetalleProductoActivity_ImageView_ImagenDelProducto);
        myTextViewTituloDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_TituloDelProducto);
        myTextViewPrecioDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_PrecioDelProducto);
        myTextViewDescripcionDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_DescripcionDelProducto);
    }

    private void configuroToolbar() {
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("DetalleProductoActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflo el toolbar
        getMenuInflater().inflate(R.menu.toolbar_detalle_menu, menu);

        //Encuentro los componentes del menu
        final MenuItem myMenuItemFavorito = menu.findItem(R.id.ToolBarDetalleMenu_Item_favorito);

        //Configuro que pasa si se clickea el Favorito
        myMenuItemFavorito.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.ToolBarDetalleMenu_Item_favorito) {
                    Toast.makeText(getApplicationContext(), "Favorito", Toast.LENGTH_SHORT).show();
                    myFirestoreController.agregarProductoAFav(myProducto);
                    myBooleanEsFavorita = !myBooleanEsFavorita;
                    actualizarFav(myMenuItemFavorito);
                }
                return true;
            }
        });
        return true;
    }

    private void actualizarFav(MenuItem myMenuItemFavorito){
        //si es favorita muestra corazon lleno
        if (myBooleanEsFavorita){
            myMenuItemFavorito.setIcon(R.drawable.ic_favorite_black_24dp);
        }
        else
        {
            //sino vacio
            myMenuItemFavorito.setIcon(R.drawable.ic_favorite_border_black_24dp);
        }
    }


}
