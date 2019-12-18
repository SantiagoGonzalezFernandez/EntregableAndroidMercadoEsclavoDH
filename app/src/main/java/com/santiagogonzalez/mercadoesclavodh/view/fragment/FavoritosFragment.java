package com.santiagogonzalez.mercadoesclavodh.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.controller.FirestoreController;
import com.santiagogonzalez.mercadoesclavodh.controller.ProductoController;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.util.ResultListener;
import com.santiagogonzalez.mercadoesclavodh.view.activity.DetalleProductoActivity;
import com.santiagogonzalez.mercadoesclavodh.view.activity.MainActivity;
import com.santiagogonzalez.mercadoesclavodh.view.adapter.FavoritoAdapter;
import com.santiagogonzalez.mercadoesclavodh.view.adapter.ProductoRVAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements FavoritoAdapter.ListenerDelAdapter {

    private ListenerDelFragment myListenerDelFragment;
    private RecyclerView myRecyclerView;
    private FavoritoAdapter myFavoritoAdapter;
    private FirestoreController myFirestoreController;
    private FirebaseUser myCurrentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        encuentroComponentePorId(view);

        myFirestoreController = new FirestoreController();
        myFavoritoAdapter = new FavoritoAdapter(this);
        myCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        myFirestoreController.traerListaDeFavorito(new ResultListener<List<Producto>>() {
            @Override
            public void finish(List<Producto> result) {
                myFavoritoAdapter.setFavoritoList(result);
            }
        });

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        DividerItemDecoration myDividerItemDecoration = new DividerItemDecoration(myRecyclerView.getContext(),
                myLinearLayoutManager.getOrientation());

        myRecyclerView.addItemDecoration(myDividerItemDecoration);
        myRecyclerView.setLayoutManager(myLinearLayoutManager);
        myRecyclerView.setAdapter(myFavoritoAdapter);
        myRecyclerView.setHasFixedSize(true);

        return view;
    }

    private void encuentroComponentePorId(View view) {
        myRecyclerView = view.findViewById(R.id.FavoritoFragment_RecyclerView_ListaDeFavoritos);
    }

    @Override
    public void informarProducto(Producto myProducto) {
        myListenerDelFragment.recibirProducto(myProducto);
    }

    public interface ListenerDelFragment {
        public void recibirProducto(Producto myProducto);
    }

    //Acabo de terminar la app (:
}
