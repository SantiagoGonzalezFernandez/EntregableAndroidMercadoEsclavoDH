package com.santiagogonzalez.mercadoesclavodh.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoRVAdapter extends RecyclerView.Adapter {

    private List<Producto> myProductoList;
    private List<Producto> productoListFiltrada;
    private ListenerDelAdapter myListenerDelAdapter;

    public ProductoRVAdapter(List<Producto> myProductoList) {
        this.myProductoList = myProductoList;
    }

    public ProductoRVAdapter(ListenerDelAdapter myListenerDelAdapter) {
        myProductoList = new ArrayList<>();
        productoListFiltrada = new ArrayList<>();
        this.myListenerDelAdapter = myListenerDelAdapter;
    }

    public List<Producto> getMyProductoList() {
        return myProductoList;
    }

    public void setMyProductoList(List<Producto> myProductoList) {
        this.myProductoList = myProductoList;
    }

    public void addProductoList(List<Producto> results) {
        this.myProductoList.addAll(results);
        notifyDataSetChanged();
    }

    public List<Producto> getProductoListFiltrada() {
        return productoListFiltrada;
    }

    public void setProductoListFiltrada(List<Producto> productoListFiltrada) {
        this.productoListFiltrada = productoListFiltrada;
        notifyDataSetChanged();
    }

    public ListenerDelAdapter getMyListenerDelAdapter() {
        return myListenerDelAdapter;
    }

    public void setMyListenerDelAdapter(ListenerDelAdapter myListenerDelAdapter) {
        this.myListenerDelAdapter = myListenerDelAdapter;
    }

    public void actualizarLista(List<Producto> myProductoList) {
        this.myProductoList = myProductoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_producto, parent, false);
        ProductoViewHolder myProductoViewHolder = new ProductoViewHolder(myView);
        return myProductoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Producto myProducto = myProductoList.get(position);
        ProductoViewHolder myProductoViewHolder = (ProductoViewHolder) holder;
        myProductoViewHolder.bindProducto(myProducto);
    }

    @Override
    public int getItemCount() {
        return myProductoList.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        private ImageView myImageViewImagen;
        private TextView myTextViewTitulo;
        private TextView myTextViewPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            myImageViewImagen = itemView.findViewById(R.id.CeldaProducto_ImageView_ImagenDeProducto);
            myTextViewTitulo = itemView.findViewById(R.id.CeldaProducto_TextView_TituloDeProducto);
            myTextViewPrecio = itemView.findViewById(R.id.CeldaProducto_TextView_PrecioDeProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Producto myProducto = myProductoList.get(getAdapterPosition());
                    myListenerDelAdapter.informarProducto(myProducto);
                }
            });
        }

        public void bindProducto(Producto myProducto) {
            Glide
                    .with(itemView)
                    .load(myProducto.getThumbnail())
                    .into(myImageViewImagen);

            myTextViewTitulo.setText(myProducto.getTitle());

            myTextViewPrecio.setText("$ " + myProducto.getPrice());
        }
    }

    public interface ListenerDelAdapter {
        public void informarProducto(Producto myProducto);
    }
}

