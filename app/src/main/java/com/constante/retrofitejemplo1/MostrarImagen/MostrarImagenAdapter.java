package com.constante.retrofitejemplo1.MostrarImagen;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.constante.retrofitejemplo1.R;
import com.constante.retrofitejemplo1.modelo.Photo;

import java.util.ArrayList;

public class MostrarImagenAdapter extends RecyclerView.Adapter<MostrarImagenAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Photo> fotos;

    public MostrarImagenAdapter(ArrayList<Photo> fotos,Context context, LayoutInflater layoutInflater) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public MostrarImagenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_foto,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MostrarImagenAdapter.ViewHolder holder, int position) {
        Photo photo = fotos.get(position);
        GlideUrl url = new GlideUrl(photo.getUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        Glide.with(context)//contexto
                .load(url)//url de la imagen
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)// guarda en el cache
                .encodeFormat(Bitmap.CompressFormat.JPEG)
                .into(holder.imagen);
        holder.titulo.setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagen;
        private TextView titulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.IVFoto);
            titulo = itemView.findViewById(R.id.TVTituloFoto);
        }
    }
}
