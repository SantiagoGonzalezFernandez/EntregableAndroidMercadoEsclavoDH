package com.santiagogonzalez.mercadoesclavodh.view.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.santiagogonzalez.mercadoesclavodh.R;
import com.santiagogonzalez.mercadoesclavodh.model.data.pojo.Producto;

import java.util.ArrayList;
import java.util.List;


import static android.view.View.GONE;

public class FavoritoAdapter extends RecyclerView.Adapter<FavoritoAdapter.ViewHolderPeliculas> {

    private List<Producto> myFavoritoList;
    private ListenerDelAdapter myListenerDelAdapter;
    private View myView;

    public FavoritoAdapter(List<Producto> favoritoList) {
        this.myFavoritoList = favoritoList;
    }

    public FavoritoAdapter(ListenerDelAdapter listenerDelAdapter) {
        myFavoritoList = new ArrayList<>();
        this.myListenerDelAdapter = listenerDelAdapter;
    }

    @NonNull
    @Override
    public FavoritoAdapter.ViewHolderPeliculas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        myView = inflater.inflate(R.layout.celda_producto, parent, false);
        return new ViewHolderPeliculas(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPeliculas holder, int position) {
        Producto myProducto = myFavoritoList.get(position);
        holder.bindProducto(myProducto);
    }

    @Override
    public int getItemCount() {
        return myFavoritoList.size(); }

    public void setFavoritoList(List<Producto> favoritoList) {
        this.myFavoritoList = favoritoList;
        notifyDataSetChanged();
    }

    public List<Producto> getFavoritoList() {
        return myFavoritoList;
    }

    public class ViewHolderPeliculas extends RecyclerView.ViewHolder {

        private ImageView myImageViewImagen;
        private TextView myTextViewTitulo;
        private TextView myTextViewPrecio;

        public ViewHolderPeliculas(@NonNull View itemView) {
            super(itemView);
            myImageViewImagen = itemView.findViewById(R.id.CeldaProducto_ImageView_ImagenDeProducto);
            myTextViewTitulo = itemView.findViewById(R.id.CeldaProducto_TextView_TituloDeProducto);
            myTextViewPrecio = itemView.findViewById(R.id.CeldaProducto_TextView_PrecioDeProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Producto myProducto = myFavoritoList.get(getAdapterPosition());
                    myListenerDelAdapter.informarProducto(myProducto);
                }
            });
        }

        public void bindProducto(Producto myProducto) {
            Glide
                    .with(itemView)
                    .load(myProducto.getMyStringImagen())
                    .into(myImageViewImagen);

            myTextViewTitulo.setText(myProducto.getMyStringTitulo());

            myTextViewPrecio.setText("$ " + myProducto.getMyStringPrecio());
        }
    }

    public interface ListenerDelAdapter {
        public void informarProducto(Producto myProducto);
    }
}
