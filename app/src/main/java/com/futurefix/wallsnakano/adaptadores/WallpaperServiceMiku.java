package com.futurefix.wallsnakano.adaptadores;

import com.futurefix.wallsnakano.modelos.Wallpaper;

import java.util.ArrayList;
import java.util.List;

public class WallpaperServiceMiku {

    public static List<Wallpaper> wallpaperMiku = new ArrayList<>();

    public static void addWallpaper(Wallpaper wallpaper){
        wallpaperMiku.add(wallpaper);}
}
