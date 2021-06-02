package com.futurefix.wallsnakano.listas;

import com.futurefix.wallsnakano.modelos.Wallpaper;

import java.util.ArrayList;
import java.util.List;

public class WallpaperServiceWallpapersCategorias {

    public static List<Wallpaper> WallpaperCat = new ArrayList<>();

    public static void addWallpaper(Wallpaper wallpaper){
        WallpaperCat.add(wallpaper);}

    public static void clearList(){
        WallpaperCat.clear();
    }

    public static int cuantosList(){
        return WallpaperCat.size();
    }
}
