package com.futurefix.wallsnakano.listas;

import com.futurefix.wallsnakano.modelos.Wallpaper;

import java.util.ArrayList;
import java.util.List;

public class WallpaperServiceCategorias {

    public static List<Wallpaper> Categorias = new ArrayList<>();

    public static void addWallpaper(Wallpaper wallpaper){
        Categorias.add(wallpaper);}
}
