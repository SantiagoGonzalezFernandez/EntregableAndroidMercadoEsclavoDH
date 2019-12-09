package com.santiagogonzalez.mercadoesclavodh.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.santiagogonzalez.mercadoesclavodh.R;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private TextInputLayout myTextInputLayoutEmail;
    private TextInputEditText myTextInputEditTextEmail;

    private TextInputLayout myTextInputLayoutPassword;
    private TextInputEditText myTextInputEditTextPassword;

    private TextInputLayout myTextInputLayoutNombre;
    private TextInputEditText myTextInputEditTextNombre;

    private TextInputLayout myTextInputLayoutApellido;
    private TextInputEditText myTextInputEditTextApellido;

    private TextInputLayout myTextInputLayoutEdad;
    private TextInputEditText myTextInputEditTextEdad;

    private TextInputLayout myTextInputLayoutNacionalidad;
    private TextInputEditText myTextInputEditTextNacionalidad;

    private Button myButtonRegistrarse;
    private Button myButtonRegresar;

    //Variables de los datos que vamos a registrar
    private String myStringEmail = "";
    private String myStringPassword = "";
    private String myStringNombre = "";
    private String myStringApellido = "";
    private String myStringEdad = "";
    private String myStringNacionalidad = "";

    private FirebaseAuth myFirebaseAuth;
    private DatabaseReference myDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        myFirebaseAuth = FirebaseAuth.getInstance();
        myDatabaseReference = FirebaseDatabase.getInstance().getReference();

        encontrarComponentesPorId();

        configuroBotones();
    }

    private void encontrarComponentesPorId(){

        myTextInputLayoutEmail = findViewById(R.id.LoginActivity_TextInputLayout_Email);
        myTextInputEditTextEmail = findViewById(R.id.RegistroActivity_TextInputEditText_Email);

        myTextInputLayoutPassword = findViewById(R.id.RegistroActivity_TextInputLayout_Password);
        myTextInputEditTextPassword = findViewById(R.id.RegistroActivity_TextInputEditText_Password);

        myTextInputLayoutNombre = findViewById(R.id.RegistroActivity_TextInputLayout_Nombre);
        myTextInputEditTextNombre = findViewById(R.id.RegistroActivity_TextInputEditText_Nombre);

        myTextInputLayoutApellido = findViewById(R.id.RegistroActivity_TextInputLayout_Apellido);
        myTextInputEditTextApellido = findViewById(R.id.RegistroActivity_TextInputEditText_Apellido);

        myTextInputLayoutEdad = findViewById(R.id.RegistroActivity_TextInputLayout_Edad);
        myTextInputEditTextEdad = findViewById(R.id.RegistroActivity_TextInputEditText_Edad);

        myTextInputLayoutNacionalidad = findViewById(R.id.RegistroActivity_TextInputLayout_Nacionalidad);
        myTextInputEditTextNacionalidad = findViewById(R.id.RegistroActivity_TextInputEditText_Nacionalidad);

        myButtonRegistrarse = findViewById(R.id.RegistroActivity_Button_Registrarse);

        myButtonRegresar = findViewById(R.id.RegistroActivity_Button_Regresar);
    }

    private void configuroBotones(){

        myButtonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStringEmail = myTextInputEditTextEmail.getText().toString();
                myStringPassword = myTextInputEditTextPassword.getText().toString();
                myStringNombre = myTextInputEditTextNombre.getText().toString();
                myStringApellido = myTextInputEditTextApellido.getText().toString();
                myStringEdad = myTextInputEditTextEdad.getText().toString();
                myStringNacionalidad = myTextInputEditTextNacionalidad.getText().toString();

                boolean losCamposEstanCompletos = !myStringEmail.isEmpty() &&
                                                !myStringPassword.isEmpty() &&
                                                !myStringNombre.isEmpty() &&
                                                !myStringEdad.isEmpty() &&
                                                !myStringApellido.isEmpty() &&
                                                !myStringNacionalidad.isEmpty();
                if(losCamposEstanCompletos){

                    if(myStringPassword.length() >= 6){
                        registrarUsuario();
                    }else{
                        Toast.makeText(RegistroActivity.this, "El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistroActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Configuro el boton regresar
        myButtonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void registrarUsuario(){
        myFirebaseAuth.createUserWithEmailAndPassword(myStringEmail, myStringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String stringId = myFirebaseAuth.getCurrentUser().getUid();

                    Map<String, Object> map = new HashMap<>();
                    map.put("email", myStringEmail);
                    map.put("password", myStringPassword);
                    map.put("nombre", myStringNombre);
                    map.put("apellido", myStringApellido);
                    map.put("edad", myStringEdad);
                    map.put("nacionalidad", myStringNacionalidad);

                    myDatabaseReference.child("Usuarios").child(stringId).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Toast.makeText(RegistroActivity.this, "Se a registrado el usuario correctamente", Toast.LENGTH_SHORT).show();
                                Intent myIntent = new Intent(RegistroActivity.this,LoginActivity.class);
                                startActivity(myIntent);
                                finish();
                            }else{
                                Toast.makeText(RegistroActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistroActivity.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
