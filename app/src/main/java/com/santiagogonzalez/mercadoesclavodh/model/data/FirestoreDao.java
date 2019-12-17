package com.santiagogonzalez.mercadoesclavodh.model.data;

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

import java.util.List;

public class FirestoreDao {

    public static final String PRODUCTOS_FAVORITOS = "productos_favoritos";
    private FirebaseFirestore myFirebaseFirestore;
    private FirebaseUser myFirebaseUser;
    private ProductoContainer myProductoContainer;

    //pasos a seguir para que esto funcione
    //1 el container tiene que tener metodos para agregar sacar chequear si exite (no obligatorio pero mas comodo
    //2 productos tien que tenes equals para compara y contructor vacio


    //inicializo mis servicion en el contructor del DAO
    public FirestoreDao() {
        myFirebaseFirestore = FirebaseFirestore.getInstance();
        myFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //tambien traigo la lista de favoritos del usuario
        traerProductosFavoritos();
    }



    public void agregarProductoAFav(Producto myProducto){
        //le pregunto si el producto que quiero agregar ya se encuentra en favs
        if (myProductoContainer.contieneElProducto(myProducto)){
            //si esta la saco
            myProductoContainer.removerProducto(myProducto);
        }
        else {
            //si no esta la agrego
            myProductoContainer.agregarProducto(myProducto);
        }
        //updateo la lista en firebase
        myFirebaseFirestore.collection("productos_favoritos")
                .document(myFirebaseUser.getEmail())
                .set(myProductoContainer);
    }

    private void traerProductosFavoritos() {
        //traigo la referencia y le intento traer la lista
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
        //traigo la referencia y le intento traer la lista
        myFirebaseFirestore.collection(PRODUCTOS_FAVORITOS)
                .document(myFirebaseUser.getUid())
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
                        //ademas de actualizar la lista se lo doy a la vista que lo va a necesitar
                        myListenerDelController.finish(myProductoContainer.getMyProductoListResultado());
                    }
                });
    }
}
