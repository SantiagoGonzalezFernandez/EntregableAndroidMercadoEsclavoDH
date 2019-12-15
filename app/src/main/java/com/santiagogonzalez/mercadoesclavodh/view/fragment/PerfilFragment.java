package com.santiagogonzalez.mercadoesclavodh.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private TextView myTextViewNombre;
    private TextView myTextViewApellido;
    private TextView myTextViewEdad;
    private TextView myTextViewNacionalidad;
    private TextView myTextViewEmail;

    private FirebaseFirestore myFirebaseFirestore;
    private Usuario myUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        encontrarComponentesPorId(view);

        inicializarComponentes();

        myFirebaseFirestore.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            QuerySnapshot myQuerySnapshot = task.getResult();
                            for(DocumentSnapshot myDocumentSnapshot : myQuerySnapshot.getDocuments()){
                                myUsuario = myDocumentSnapshot.toObject(Usuario.class);
                            }
                            myTextViewNombre.setText(myUsuario.getNombre());
                            myTextViewApellido.setText(myUsuario.getApellido());
                            myTextViewEdad.setText(myUsuario.getEdad());
                            myTextViewEmail.setText(myUsuario.getEmail());
                            myTextViewNacionalidad.setText(myUsuario.getNacionalidad());
                        }
                    }
                });
        return view;
    }

    private void encontrarComponentesPorId(View view){
        myTextViewNombre = view.findViewById(R.id.FragmentPerfil_TextView_Nombre);
        myTextViewApellido = view.findViewById(R.id.FragmentPerfil_TextView_Apellido);
        myTextViewEdad = view.findViewById(R.id.FragmentPerfil_TextView_Edad);
        myTextViewNacionalidad = view.findViewById(R.id.FragmentPerfil_TextView_Nacionalidad);
        myTextViewEmail = view.findViewById(R.id.FragmentPerfil_TextView_Email);
    }

    private void inicializarComponentes(){
        myFirebaseFirestore = FirebaseFirestore.getInstance();
        myUsuario = new Usuario();
    }

}
