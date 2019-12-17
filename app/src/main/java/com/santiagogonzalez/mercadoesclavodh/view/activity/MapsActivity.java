package com.santiagogonzalez.mercadoesclavodh.view.activity;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.model.CaracteristicasDelProducto;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String KEY_CARACTERISTICAS = "key_caracteristicas";

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        int intStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if(intStatus == ConnectionResult.SUCCESS){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else{
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(intStatus, (Activity) getApplicationContext(),10);
            dialog.show();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CaracteristicasDelProducto caracteristicasDelProducto = (CaracteristicasDelProducto) bundle.getSerializable(KEY_CARACTERISTICAS);

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(caracteristicasDelProducto.getMyGeolocation().getLatitude(), caracteristicasDelProducto.getMyGeolocation().getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Vendedor").icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_maps)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
