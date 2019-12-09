package com.santiagogonzalez.mercadoesclavodh.view.activity;

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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.santiagogonzalez.mercadoesclavodh.R;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity{


    private TextInputLayout myTextInputLayoutEmail;
    private TextInputEditText myTextInputEditTextEmail;

    private TextInputLayout myTextInputLayoutPassword;
    private TextInputEditText myTextInputEditTextPassword;

    private Button myButtonInciarSesion;

    private Button myButtonCrearUnaCuenta;

    private LoginButton myLoginButtonFacebook;

    private TextView myTextViewIngresarSinRegistrarse;

    private FirebaseAuth myFirebaseAuth;

    private CallbackManager myCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        encuentroComponentesPorId();

        configuroLogInFacebook();

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

        myLoginButtonFacebook = findViewById(R.id.LoginActivity_LoginButton_IniciarSesionFacebook);

        myTextViewIngresarSinRegistrarse = findViewById(R.id.LoginActivity_TextView_IngresarSinRegistrarse);
    }

    private void configuroLogInFacebook(){
        myCallbackManager = CallbackManager.Factory.create();
        myLoginButtonFacebook.setReadPermissions(Arrays.asList("email"));
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
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        //Boton de Facebook
        LoginManager.getInstance().registerCallback(myCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Se cancelo el LogIn", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
            }
        });

        myTextViewIngresarSinRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMainScreen();
            }
        });
    }

    private void handleFacebookToken(AccessToken accessToken) {
        AuthCredential myAuthCredential = FacebookAuthProvider.getCredential(accessToken.getToken());
        myFirebaseAuth.signInWithCredential(myAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
                            updateUI(myFirebaseUser);
                        }else{
                            Toast.makeText(LoginActivity.this, "No se pudo registrar con firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        myCallbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void goMainScreen(){
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);
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
        String myStringEmail = myTextInputEditTextEmail.getText().toString().trim();
        String myStringPassword = myTextInputEditTextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacias
        if (TextUtils.isEmpty(myStringEmail)) {
            Toast.makeText(this, "Se debe ingresar un Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(myStringPassword)) {
            Toast.makeText(this, "Se debe ingresar un Password", Toast.LENGTH_SHORT).show();
            return;
        }

        myFirebaseAuth.signInWithEmailAndPassword(myStringEmail, myStringPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser myFirebaseUser = myFirebaseAuth.getCurrentUser();
                            updateUI(myFirebaseUser);
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
