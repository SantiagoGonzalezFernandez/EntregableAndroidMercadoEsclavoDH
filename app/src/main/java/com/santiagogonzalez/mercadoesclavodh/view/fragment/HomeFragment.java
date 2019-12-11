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
import com.santiagogonzalez.mercadoesclavodh.controller.ControllerProducto;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;
import com.santiagogonzalez.mercadoesclavodh.view.adapter.AdapterProducto;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    private RecyclerView myRecyclerViewListaProductos;
    private AdapterProducto myAdapterProducto;
    private ControllerProducto myControllerProducto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        encontrarComponentesPorId(view);

        inicializarAdapters();

        cargarYSetearRecyclers();

        return view;
    }

    public void encontrarComponentesPorId(View view){
        myRecyclerViewListaProductos = view.findViewById(R.id.HomeFragment_RecyclerView_ListaProductos);
    }

    public void inicializarAdapters(){
        myAdapterProducto = new AdapterProducto(new ArrayList<Producto>());
        myControllerProducto = new ControllerProducto();
    }

    public void cargarYSetearRecyclers(){
        myRecyclerViewListaProductos.setLayoutManager(new LinearLayoutManager(getContext(), myRecyclerViewListaProductos.HORIZONTAL, false));
        myRecyclerViewListaProductos.setAdapter(myAdapterProducto);
        myRecyclerViewListaProductos.setHasFixedSize(true);
    }
}
