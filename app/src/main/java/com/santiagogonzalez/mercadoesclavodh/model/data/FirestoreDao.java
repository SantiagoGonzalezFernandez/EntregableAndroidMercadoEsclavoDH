package com.santiagogonzalez.mercadoesclavodh.model.data;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;
import com.santiagogonzalez.mercadoesclavodh.view.activity.DetalleProductoActivity;

import java.util.ArrayList;
import java.util.List;

public class FirestoreDao {

    public static final String PRODUCTOS_FAVORITOS = "productos_favoritos";
    private FirebaseFirestore myFirebaseFirestore;
    private FirebaseUser myFirebaseUser;
    private ProductoContainer myProductoContainer;


    public FirestoreDao() {
        myFirebaseFirestore = FirebaseFirestore.getInstance();
        myFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        traerProductosFavoritos();
    }

    public void agregarProductoAFav(Producto myProducto){
        //le pregunto si el producto que quiero agregar ya se encuentra en favs
        if (myFirebaseUser == null)
            return;
        if (myProductoContainer.contieneElProducto(myProducto)){
            myProductoContainer.removerProducto(myProducto);
        }
        else {
            myProductoContainer.agregarProducto(myProducto);
        }
        myFirebaseFirestore.collection(PRODUCTOS_FAVORITOS)
                .document(myFirebaseUser.getEmail())
                .set(myProductoContainer);
    }

    private void traerProductosFavoritos() {
        if (myFirebaseUser== null){
            myProductoContainer = new ProductoContainer();
            return;
        }
        myFirebaseFirestore.collection(PRODUCTOS_FAVORITOS)
                .document(myFirebaseUser.getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            //en el listener del on succes intento tranfomar el documento a un container
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                myProductoContainer = documentSnapshot.toObject(ProductoContainer.class);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    //en el on failure del listener inicializo un container vacio
                    public void onFailure(@NonNull Exception e) {
                        myProductoContainer = new ProductoContainer();
                    }
                });
    }

    public void traerProductosFavoritos(final ResultListener<List<Producto>> myListenerDelController){
        if (myFirebaseUser == null){
            myProductoContainer = new ProductoContainer();
            myListenerDelController.finish(myProductoContainer.getMyProductoListResultado());
            return;
        }
        myFirebaseFirestore.collection(PRODUCTOS_FAVORITOS)
                .document(myFirebaseUser.getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            //en el listener del on succes intento tranfomar el documento a un container
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                myProductoContainer = documentSnapshot.toObject(ProductoContainer.class);
                if (myProductoContainer == null){
                    myProductoContainer = new ProductoContainer();
                }
                myListenerDelController.finish(myProductoContainer.getMyProductoListResultado());
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    //en el on failure del listener inicializo un container vacio
                    public void onFailure(@NonNull Exception e) {
                        myProductoContainer = new ProductoContainer();
                        myListenerDelController.finish(myProductoContainer.getMyProductoListResultado());
                    }
                });
    }
}
