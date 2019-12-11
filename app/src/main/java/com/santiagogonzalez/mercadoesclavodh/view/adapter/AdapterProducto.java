package com.santiagogonzalez.mercadoesclavodh.view.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;

import java.util.List;

public class AdapterProducto extends RecyclerView.Adapter {

    private List<Producto> myProductoList;

    public AdapterProducto(List<Producto> myProductoList) {
        this.myProductoList = myProductoList;
    }

    public void setProductoList(List<Producto> myProductoList){
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
        private TextView mytextViewTitulo;
        private TextView myTextViewPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            myImageViewImagen = itemView.findViewById(R.id.CeldaProducto_ImageView_ImagenDeProducto);
            mytextViewTitulo = itemView.findViewById(R.id.CeldaProducto_TextView_TituloDeProducto);
            myTextViewPrecio = itemView.findViewById(R.id.CeldaProducto_TextView_TituloDeProducto);
        }

        public void bindProducto(Producto myProducto){
            Glide
                    .with(itemView)
                    .load(myProducto.getMyStringImagen())
                    .into(myImageViewImagen);

            mytextViewTitulo.setText(myProducto.getMyStringTitulo());

            myTextViewPrecio.setText("$ "+myProducto.getMyStringPrecio());
        }
    }
}
