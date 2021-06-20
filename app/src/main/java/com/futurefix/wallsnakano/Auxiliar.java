package com.futurefix.wallsnakano;


public class Auxiliar {

    public static Boolean estadoactualCheckBox;
    public static int estadoSelectorColumnas=3;
    /*
    * 1 x 1 el estado es 0
    * 2 x 2 el estado es 1
    * 3 x 3 el estado es 2
    * 4 x 4 el estado es 3
    * */
    public static int iteradorAnuncios=1;

    public Auxiliar() {
    }

    public static void guardarEstadoCheckBox(boolean bol){
        estadoactualCheckBox = bol;
    }

    public static void guardarEstadoelectorColumnas(int intero){estadoSelectorColumnas = intero;}


    // Intento de no replicar codigo

   /* public static void cargarWallpapers(String nombrePrincipal, RecyclerView rc, Context context){
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){
            if (wallpaper.getNombre() != null){
                if (wallpaper.getNombre().equals(nombrePrincipal)){
                    try {
                        if (){

                        }
                    }catch (Exception e){
                        Toast.makeText(context, "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }

            if (wallpaper.getTag1() != null){
                if (wallpaper.getTag1().equals(nombrePrincipal)){
                    try {
                    }catch (Exception e){
                        Toast.makeText(context, "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }

            if (wallpaper.getTag2() != null){
                if (wallpaper.getTag2().equals(nombrePrincipal)){
                    try {
                    }catch (Exception e){
                        Toast.makeText(context, "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }

            if (wallpaper.getTag3() != null){
                if (wallpaper.getTag3().equals(nombrePrincipal)){
                    try {
                    }catch (Exception e){
                        Toast.makeText(context, "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }

            if (wallpaper.getTag4() != null){
                if (wallpaper.getTag4().equals(nombrePrincipal)){
                    try {
                    }catch (Exception e){
                        Toast.makeText(context, "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }*/

}
