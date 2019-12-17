package com.santiagogonzalez.mercadoesclavodh.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.controller.ProductoController;
import com.santiagogonzalez.mercadoesclavodh.model.ProductoContainer;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.view.adapter.ProductoRVAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements ProductoRVAdapter.ListenerDelAdapter{

    private RecyclerView myRecyclerView;
    private ProductoRVAdapter myProductoRVAdapter;
    private ProductoContainer myProductoContainer;

    private List<Producto> myProductoList;

    private ListenerDeFragment myListenerDeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        encontrarComponentesPorId(view);

        inicializarComponentes();

        traerProductosFavoritos();

        configurarRecycler();
        return view;
    }

    private void encontrarComponentesPorId(View view){
        myRecyclerView = view.findViewById(R.id.FavoritoFragment_RecyclerView_ListaDeFavoritos);
    }

    private void inicializarComponentes() {
        myProductoRVAdapter = new ProductoRVAdapter(this);
        myProductoContainer = new ProductoContainer();
        myProductoList = new ArrayList<>();
    }

    private void traerProductosFavoritos(){
        myProductoList = myProductoContainer.getMyProductoListResultado();
        myProductoRVAdapter.setMyProductoList(myProductoList);
    }

    private void configurarRecycler(){
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),myRecyclerView.VERTICAL,false));
        myRecyclerView.setAdapter(myProductoRVAdapter);
    }

/*    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myListenerDeFragment = (ListenerDeFragment) context;
    }*/

    @Override
    public void informarProducto(Producto myProducto) {
        myListenerDeFragment.recibirProducto(myProducto);
    }

    public interface ListenerDeFragment{
        public void recibirProducto(Producto myProducto);
    }
}
