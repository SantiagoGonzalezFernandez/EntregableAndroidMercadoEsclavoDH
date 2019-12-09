package com.santiagogonzalez.mercadoesclavodh.view.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ViewHolderProducto> {

    private List<Producto> myProductoList;
    private ListenerDelAdapter myListenerDelAdapter;

    public AdapterProducto(List<Producto> myProductoList) {
        this.myProductoList = myProductoList;
    }

    public AdapterProducto(ListenerDelAdapter myListenerDelAdapter) {
        this.myListenerDelAdapter = myListenerDelAdapter;
        myProductoList = new ArrayList<>();
    }

    public void setMyProductoList(List<Producto> myProductoList) {
        this.myProductoList = myProductoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterProducto.ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
        View myView = myLayoutInflater.inflate(R.layout.celda_producto, parent,false);
        return new ViewHolderProducto(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProducto.ViewHolderProducto holder, int position) {
        Producto myProducto = myProductoList.get(position);
        holder.cargarProducto(myProducto);
    }

    @Override
    public int getItemCount() {
        return myProductoList.size();
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder{

        private ImageView myImageViewFotoDeProducto;
        private TextView myTextViewTituloDeProducto;
        private TextView myTextViewPrecioDeProducto;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            myImageViewFotoDeProducto = itemView.findViewById(R.id.CeldaProducto_ImageView_ImagenDeProducto);
            myTextViewTituloDeProducto = itemView.findViewById(R.id.CeldaProducto_TextView_TituloDeProducto);
            myTextViewPrecioDeProducto = itemView.findViewById(R.id.CeldaProducto_TextView_PrecioDeProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Producto myProducto = myProductoList.get(getAdapterPosition());
                    Toast.makeText(v.getContext(), "Se a clickeado: " + myProducto.getMyStringTitulo(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void cargarProducto(Producto myProducto) {
            Glide.with(myImageViewFotoDeProducto.getContext())
                    .load(myProducto.getMyStringFotoMiniatura())
                    .error(R.drawable.logo_mercado_esclavo).into(myImageViewFotoDeProducto);
            myTextViewTituloDeProducto.setText(myProducto.getMyStringTitulo());
            myTextViewPrecioDeProducto.setText(myProducto.getMyIntPrecio());
        }
    }

    public interface ListenerDelAdapter{
        public void informarProducto(Producto myProducto);
    }

}
