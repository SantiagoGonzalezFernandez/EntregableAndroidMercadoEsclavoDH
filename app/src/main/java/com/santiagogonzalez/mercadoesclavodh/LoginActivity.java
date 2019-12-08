package com.santiagogonzalez.mercadoesclavodh;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private TextInputLayout myTextInputLayoutEmail;
    private TextInputEditText myTextInputEditTextEmail;

    private TextInputLayout myTextInputLayoutPassword;
    private TextInputEditText myTextInputEditTextPassword;

    private Button myButtonInciarSesion;

    private Button myButtonCrearUnaCuenta;

    private TextView myTextViewIngresarSinRegistrarse;

    private FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        encuentroComponentesPorId();

        myFirebaseAuth = FirebaseAuth.getInstance();

        configuroLosInputEditText();

        configuroBotones();

    }

    private void encuentroComponentesPorId(){

        myTextInputLayoutEmail = findViewById(R.id.LoginActivity_TextInputLayout_Email);
        myTextInputEditTextEmail = findViewById(R.id.LoginActivity_TextInputEditText_Email);

        myTextInputLayoutPassword = findViewById(R.id.LoginActivity_TextInputLayout_Password);
        myTextInputEditTextPassword = findViewById(R.id.LoginActivity_TextInputEditText_Password);

        myButtonInciarSesion = findViewById(R.id.LoginActivity_Button_IniciarSesion);

        myButtonCrearUnaCuenta = findViewById(R.id.LoginActivity_Button_CrearUnaCuenta);

        myTextViewIngresarSinRegistrarse = findViewById(R.id.LoginActivity_TextView_IngresarSinRegistrarse);
    }

    private void configuroBotones(){

        myButtonInciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocamos el metodo
                iniciarSesionFirebase();
            }
        });

        //Boton de Crear una cuenta
        myButtonCrearUnaCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Le creo un intent para poder pasar de esta activity a la del RegistroActivity
                Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });

        myTextViewIngresarSinRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }

    private void configuroLosInputEditText(){

        myTextInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() < 6){
                    //le seteo el mensaj de error al text imput layout
                    myTextInputLayoutPassword.setError("Tiene que tener minimo 6 caracteres");
                }else {
                    //le borro el mensaje de error cuando el contenido este bien
                    myTextInputLayoutPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void iniciarSesionFirebase() {
        //Obtenemos el email y el password desde las cajas de texto
        String stringEmail = myTextInputEditTextEmail.getText().toString().trim();
        String stringPassword = myTextInputEditTextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacias
        if (TextUtils.isEmpty(stringEmail)) {
            Toast.makeText(this, "Se debe ingresar un Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(stringPassword)) {
            Toast.makeText(this, "Se debe ingresar un Password", Toast.LENGTH_SHORT).show();
            return;
        }

        myFirebaseAuth.signInWithEmailAndPassword(stringEmail, stringPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = myFirebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Fallo Logueo.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });

    }

    //Este metodo si tiene un usuario valido va a pasar directamente a la MainActivity
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //chekea si el usuario ya esta logueado y te manda directamente a la mainActivity
        FirebaseUser myCurrentUser = myFirebaseAuth.getCurrentUser();

        //Llamo a los metodo que me comprueban eso
        updateUI(myCurrentUser);
    }

}
