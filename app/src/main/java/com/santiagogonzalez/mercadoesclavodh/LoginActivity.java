package com.santiagogonzalez.mercadoesclavodh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button myButtonCrearUnaCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        encuentroComponentesPorId();

        configuroBotones();

    }

    private void encuentroComponentesPorId(){
        myButtonCrearUnaCuenta = findViewById(R.id.LoginActivity_Button_CrearUnaCuenta);
    }

    private void configuroBotones(){

        //Boton de Crear una cuenta
        myButtonCrearUnaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Le creo un intent para poder pasar de esta activity a la del RegistroActivity
                Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });

    }

}
