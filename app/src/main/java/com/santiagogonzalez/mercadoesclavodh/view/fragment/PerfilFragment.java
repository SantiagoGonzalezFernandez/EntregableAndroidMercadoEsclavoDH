package com.santiagogonzalez.mercadoesclavodh.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;
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
    private FirebaseAuth myFirebaseAuth;
    private DocumentReference myDocumentReference;
    private Profile myProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        encontrarComponentesPorId(view);

        inicializarComponentes();

        if(myFirebaseAuth.getCurrentUser() != null){
            myDocumentReference = myFirebaseFirestore.collection("usuarios").document(myFirebaseAuth.getCurrentUser().getEmail());

            myDocumentReference.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot myDocumentSnapshot = task.getResult();
                                myTextViewNombre.setText(myProfile.getFirstName());
                                myTextViewApellido.setText(myProfile.getLastName());
                                if (myDocumentSnapshot.exists()){
                                    myUsuario = myDocumentSnapshot.toObject(Usuario.class);

                                    myTextViewNombre.setText(myUsuario.getNombre());
                                    myTextViewApellido.setText(myUsuario.getApellido());
                                    myTextViewEdad.setText(myUsuario.getEdad());
                                    myTextViewEmail.setText(myUsuario.getEmail());
                                    myTextViewNacionalidad.setText(myUsuario.getNacionalidad());
                                }else{
                                    Toast.makeText(getContext(), "Si quieres un perfil mas completo registrate usando nuestra app", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }else{
            Toast.makeText(getContext(), "Tenes que registrarte!", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void encontrarComponentesPorId(View view) {
        myTextViewNombre = view.findViewById(R.id.FragmentPerfil_TextView_Nombre);
        myTextViewApellido = view.findViewById(R.id.FragmentPerfil_TextView_Apellido);
        myTextViewEdad = view.findViewById(R.id.FragmentPerfil_TextView_Edad);
        myTextViewNacionalidad = view.findViewById(R.id.FragmentPerfil_TextView_Nacionalidad);
        myTextViewEmail = view.findViewById(R.id.FragmentPerfil_TextView_Email);
    }

    private void inicializarComponentes() {
        myFirebaseFirestore = FirebaseFirestore.getInstance();
        myFirebaseAuth = FirebaseAuth.getInstance();
        myProfile = Profile.getCurrentProfile();
    }

}
