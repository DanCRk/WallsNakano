package com.futurefix.wallsnakano.adaptadores;

import com.futurefix.wallsnakano.modelos.Wallpaper;

import java.util.ArrayList;
import java.util.List;

public class WallpaperService {

    public static List<Wallpaper> todosWallpapers = new ArrayList<>();

    public static List<Wallpaper> wallpaperItsuki = new ArrayList<>();
    public static List<Wallpaper> wallpaperNino= new ArrayList<>();
    public static List<Wallpaper> wallpaperMiku= new ArrayList<>();
    public static List<Wallpaper> wallpaperYotsuba= new ArrayList<>();
    public static List<Wallpaper> wallpaperIchika= new ArrayList<>();

    public static List<Wallpaper> Categorias = new ArrayList<>();
    public static List<Wallpaper> WallpaperCat = new ArrayList<>();

    public static List<Wallpaper> favoritos = new ArrayList<>();

    // Todos los wpps

    public static void addWallpaperTodos(Wallpaper wallpaper){todosWallpapers.add(wallpaper);}

    // Todos los wpps de las quints

    public static void addWallpaperItsuki(Wallpaper wallpaper){ wallpaperItsuki.add(wallpaper); }
    public static void addWallpaperNino(Wallpaper wallpaper){ wallpaperNino.add(wallpaper); }
    public static void addWallpaperMiku(Wallpaper wallpaper){ wallpaperMiku.add(wallpaper); }
    public static void addWallpaperYotsuba(Wallpaper wallpaper){ wallpaperYotsuba.add(wallpaper); }
    public static void addWallpaperIchika(Wallpaper wallpaper){ wallpaperIchika.add(wallpaper); }

    // Metodos para los wpps de Categorias

    public static void addWallpaperCategorias(Wallpaper wallpaper){ Categorias.add(wallpaper);}
    public static void addWallpaperWallpaperCat(Wallpaper wallpaper){ WallpaperCat.add(wallpaper);}
    public static void clearList(){ WallpaperCat.clear(); }

    // Metodo para la lista de favoritos
    public static void addWallpaperFavoritos(Wallpaper wallpaper) {favoritos.add(wallpaper);}
    public static void removeWallpaperFavoritos(Wallpaper wallpaper) {favoritos.remove(wallpaper);}

    // Metodo para retornar el tamaño de la lista de wpps

    public static int cuantosList(){ return WallpaperCat.size(); }

}
