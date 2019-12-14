package com.santiagogonzalez.mercadoesclavodh.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private TextView myTextViewNombre;
    private TextView myTextViewApellido;
    private TextView myTextViewEdad;
    private TextView myTextViewNacionalidad;
    private TextView myTextViewEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        encontrarComponentesPorId(view);

        return view;
    }

    private void encontrarComponentesPorId(View view){
        myTextViewNombre = view.findViewById(R.id.FragmentPerfil_TextView_Nombre);
        myTextViewApellido = view.findViewById(R.id.FragmentPerfil_TextView_Apellido);
        myTextViewEdad = view.findViewById(R.id.FragmentPerfil_TextView_Edad);
        myTextViewNacionalidad = view.findViewById(R.id.FragmentPerfil_TextView_Nacionalidad);
        myTextViewEmail = view.findViewById(R.id.FragmentPerfil_TextView_Email);
    }

}
