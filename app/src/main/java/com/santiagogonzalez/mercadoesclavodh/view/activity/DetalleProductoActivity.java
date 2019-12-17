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
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.controller.FirestoreController;
import com.santiagogonzalez.mercadoesclavodh.controller.ProductoController;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.CaracteristicasDelProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.DescripcioDeProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;

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

    private FirestoreController myFirestoreController;

    private Boolean myBooleanEsFavorita;

    private CaracteristicasDelProducto caracteristicasDelProducto;

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

    }

    private void configuroElBotonUbicacionDelVendedor() {
        myButtonUbicacionDelVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleProductoActivity.this, MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DetalleProductoActivity.KEY_CARACTERISTICAS, caracteristicasDelProducto);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void traerDescripcionPorId(){
        myProductoController.traerDescripcionPorId(myProducto.getMyStringId(), new ResultListener<DescripcioDeProducto>() {
            @Override
            public void finish(DescripcioDeProducto results) {
                myTextViewDescripcionDelProducto.setText(results.getPlain_text());
            }
        });
    }

    private void traerCaracteristicasPorId(){
        myProductoController.traerCaracteristicasPorId(myProducto.getMyStringId(), new ResultListener<CaracteristicasDelProducto>() {
            @Override
            public void finish(CaracteristicasDelProducto results) {
                caracteristicasDelProducto = results;
                if (results.getCondition().equals("new")){
                    myTextViewUsoDelProducto.setText("Condicion: Nuevo");
                }else{
                    myTextViewUsoDelProducto.setText("Condicion: Usado");
                }
                myTextViewUnidadesDisponiblesDelProducto.setText("Unidades Disponibles: "+results.getAvailable_quantity());
                myTextViewUnidadesVendidasDelProducto.setText("Unidades Vendidas: "+results.getSold_quantity());
            }
        });
    }

    private void inizializoLosComponentes(){
        myProductoController = new ProductoController();
        myDescripcioDeProducto = new DescripcioDeProducto();
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
