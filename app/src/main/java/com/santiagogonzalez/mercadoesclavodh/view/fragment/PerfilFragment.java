package com.santiagogonzalez.mercadoesclavodh.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santiagogonzalez.mercadoesclavodh.R;


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
