package com.futurefix.wallsnakano.adaptadores;

import com.futurefix.wallsnakano.modelos.Wallpaper;

import java.util.ArrayList;
import java.util.List;

public class WallpaperService {

    public static List<Wallpaper> wallpapers = new ArrayList<>();

    public static void addWallpaper(Wallpaper wallpaper){wallpapers.add(wallpaper);}
}
