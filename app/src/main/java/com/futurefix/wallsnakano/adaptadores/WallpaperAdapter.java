package com.futurefix.wallsnakano.adaptadores;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.futurefix.wallsnakano.R;
import com.futurefix.wallsnakano.modelos.Wallpaper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperHolder>{
    List<Wallpaper> lista;
    int layout;
    Fragment fragment;
    Context Context;

    public WallpaperAdapter(List<Wallpaper> lista, int layout, Fragment fragment, android.content.Context context) {
        this.lista = lista;
        this.layout = layout;
        this.fragment = fragment;
        Context = context;
    }

    @NonNull
    @NotNull
    @Override
    public WallpaperHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new WallpaperHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WallpaperHolder holder, int position) {
        Wallpaper wallpaper = lista.get(position);
        Glide.with(fragment).load(wallpaper.getUrl()).into(holder.wallpview);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public static class WallpaperHolder extends RecyclerView.ViewHolder {
        ImageView wallpview;

        public WallpaperHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            wallpview = itemView.findViewById(R.id.item_url);

        }
    }
}
