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
    private TextView myTextViewUsoDelProducto;
    private TextView myTextViewPrecioDelProducto;
    private TextView myTextViewDescripcionDelProducto;

    private Producto myProducto;

    private DescripcioDeProducto myDescripcioDeProducto;

    private ProductoController myProductoController;

    private String Plain_Text;

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
        myTextViewUsoDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_UsoDelProducto);
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
        MenuItem myMenuItemFavorito = menu.findItem(R.id.ToolBarDetalleMenu_Item_favorito);

        //Configuro que pasa si se clickea el Favorito
        myMenuItemFavorito.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.ToolBarDetalleMenu_Item_favorito) {
                    Toast.makeText(getApplicationContext(), "Favorito", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }
}