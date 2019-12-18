package com.santiagogonzalez.mercadoesclavodh.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.controller.FirestoreController;
import com.santiagogonzalez.mercadoesclavodh.controller.ProductoController;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.CaracteristicasDelProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

import java.util.List;

public class DetalleProductoActivity extends AppCompatActivity {

    public static final String KEY_PRODUCTO = "key_producto";
    public static final String KEY_CARACTERISTICAS = "key_caracteristicas";

    private Toolbar myToolbar;

    private ImageView myImageViewImagenDelProducto;
    private TextView myTextViewTituloDelProducto;
    private TextView myTextViewUsoDelProducto;
    private TextView myTextViewPrecioDelProducto;
    private TextView myTextViewUnidadesDisponiblesDelProducto;
    private TextView myTextViewUnidadesVendidasDelProducto;
    private TextView myTextViewDescripcionDelProducto;
    private Button myButtonUbicacionDelVendedor;

    private Producto myProducto;

    private DescripcioDeProducto myDescripcioDeProducto;

    private ProductoController myProductoController;

    private CaracteristicasDelProducto myCaracteristicasDelProducto;

    private FloatingActionButton myFloatingActionButtonFavorito;
    private FirebaseUser myFirebaseUser;
    private FirestoreController myFirestoreController;
    private Boolean esFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        encontrarComponentesPorId();

        inizializoLosComponentes();

        configuroToolbar();

        obtengoLosDatosDelIntentYBundle();

        traerDescripcionPorId();

        traerCaracteristicasPorId();

        configuroElBotonUbicacionDelVendedor();

        configuroLosComponentesConLosDatosDelProducto();

        myFloatingActionButtonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myFirebaseUser == null) {
                    Toast.makeText(getApplicationContext(), "Debes estar Logeado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                myFirestoreController.agregarProductoAFav(myProducto);
                esFavorito = !esFavorito;
                actualizarFav();
            }
        });

        myFirestoreController.traerListaDeFavorito(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> result) {
                esFavorito = result.contains(myProducto);
                actualizarFav();
                habilitarOnClickDeFav();
            }
        });

    }

    private void actualizarFav(){
        if (esFavorito){
            myFloatingActionButtonFavorito.setImageResource(R.drawable.ic_favorite_black_24dp); }
        else {
            myFloatingActionButtonFavorito.setImageResource(R.drawable.ic_favorite_border_black_24dp); }
    }

    private void habilitarOnClickDeFav() {
        myFloatingActionButtonFavorito.setClickable(true);
    }

    private void configuroElBotonUbicacionDelVendedor() {
        myButtonUbicacionDelVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetalleProductoActivity.this, MapsActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putSerializable(DetalleProductoActivity.KEY_CARACTERISTICAS, myCaracteristicasDelProducto);
                myIntent.putExtras(myBundle);
                startActivity(myIntent);
            }
        });
    }

    private void traerDescripcionPorId(){
        myProductoController.traerDescripcionPorId(myProducto.getMyStringId(), new ResultListener<DescripcioDeProducto>() {
            @Override
            public void finish(DescripcioDeProducto myResults) {
                myTextViewDescripcionDelProducto.setText(myResults.getMyStringTextoDescripcion());
            }
        });
    }

    private void traerCaracteristicasPorId(){
        myProductoController.traerCaracteristicasPorId(myProducto.getMyStringId(), new ResultListener<CaracteristicasDelProducto>() {
            @Override
            public void finish(CaracteristicasDelProducto myResults) {
                myCaracteristicasDelProducto = myResults;
                if (myResults.getMyStringCondicion().equals("new")){
                    myTextViewUsoDelProducto.setText("Condicion: Nuevo");
                }else{
                    myTextViewUsoDelProducto.setText("Condicion: Usado");
                }
                myTextViewUnidadesDisponiblesDelProducto.setText("Unidades Disponibles: "+myResults.getMyStringCantidadDisponible());
                myTextViewUnidadesVendidasDelProducto.setText("Unidades Vendidas: "+myResults.getMyStringCantidadVendida());
            }
        });
    }

    private void inizializoLosComponentes(){
        myProductoController = new ProductoController();
        myDescripcioDeProducto = new DescripcioDeProducto();
        myFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        myFirestoreController = new FirestoreController();
    }

    private void configuroLosComponentesConLosDatosDelProducto(){
        Glide.with(myImageViewImagenDelProducto.getContext()).load(myProducto.getMyStringImagen()).into(myImageViewImagenDelProducto);
        myTextViewTituloDelProducto.setText(myProducto.getMyStringTitulo());
        myTextViewPrecioDelProducto.setText("Precio: $" + myProducto.getMyStringPrecio());
    }

    private void obtengoLosDatosDelIntentYBundle(){
        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getExtras();
        myProducto = (Producto) myBundle.getSerializable(KEY_PRODUCTO);
    }

    private void encontrarComponentesPorId() {
        myToolbar = findViewById(R.id.DetalleProductoActivity_Include_Toolbar);

        myFloatingActionButtonFavorito = findViewById(R.id.DetalleProductoActivity_FloatingActionButton_Favorito);

        myImageViewImagenDelProducto = findViewById(R.id.DetalleProductoActivity_ImageView_ImagenDelProducto);
        myTextViewTituloDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_TituloDelProducto);
        myTextViewUsoDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_UsoDelProducto);
        myTextViewPrecioDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_PrecioDelProducto);
        myTextViewUnidadesDisponiblesDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_UnidadesDisponiblesDelProducto);
        myTextViewUnidadesVendidasDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_UnidadesVendidasDelProducto);
        myTextViewDescripcionDelProducto = findViewById(R.id.DetalleProductoActivity_TextView_DescripcionDelProducto);

        myButtonUbicacionDelVendedor = findViewById(R.id.DetalleProductoActivity_Button_UbicaionDelVendedor);
    }

    private void configuroToolbar() {
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("DetalleProductoActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflo el toolbar
        getMenuInflater().inflate(R.menu.toolbar_detalle_menu, menu);
        return true;
    }

}
