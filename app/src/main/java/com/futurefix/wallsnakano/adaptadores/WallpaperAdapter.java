package com.futurefix.wallsnakano.adaptadores;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


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

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WallpaperHolder holder, int position) {
        Wallpaper Wallpaper = lista.get(position);
        Glide
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class WallpaperHolder extends RecyclerView.ViewHolder {
        ImageView wallpview;

        public WallpaperHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            wallpview = itemView.findViewById(R.id.item_url);

        }
    }
}
