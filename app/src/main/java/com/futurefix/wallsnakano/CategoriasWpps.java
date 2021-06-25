package com.futurefix.wallsnakano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.futurefix.wallsnakano.adaptadores.WallpaperAdapterWppsCateg;
import com.futurefix.wallsnakano.adaptadores.WallpaperService;
import com.futurefix.wallsnakano.modelos.Wallpaper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;

public class CategoriasWpps extends AppCompatActivity {

    RecyclerView rc;
    TextView tv, cuantos;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpapers_categorias);

        final Intent intent = getIntent();
        String nombre = intent.getStringExtra("ItemNombre");
        String tag = intent.getStringExtra("ItemTag");

        rc = findViewById(R.id.recyclerViewWallpaperCategorias);
        tv = findViewById(R.id.texto_toolbar);
        cuantos = findViewById(R.id.cuantoswpps);
        adView = findViewById(R.id.adViewBannerCategorias);

        // Anuncios
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Cambiar el nombre al toolbar por la categoria actual
        tv.setText(nombre);

        // Cargar Lista
        cargarLista();
        // Cargar Datos
        if (nombre.equals("Todos")){
            cargarTodosDatos();
        }
        else if (nombre.equals("Quints")){
           cargarDatosPrincipales("quints");
        }else {
            cargarDatosPrincipales1(tag);
            cargarDatosPrincipales2(tag);
            cargarDatosPrincipales3(tag);
            cargarDatosPrincipales4(tag);
        }


    }

    @Override
    public void onBackPressed() {
        WallpaperService.clearList();
        cuantos.setVisibility(View.GONE);
        Auxiliar.iteradorAnuncios ++;
        finish();
    }

    public void cargarLista() {
        WallpaperAdapterWppsCateg adapter = null;
        switch (Auxiliar.estadoSelectorColumnas){
            case 0:
                rc.setLayoutManager(new LinearLayoutManager(this));
                adapter = new WallpaperAdapterWppsCateg(WallpaperService.WallpaperCat, R.layout.item_wallpaper_1, this, this);
                break;
            case 1:
                rc.setLayoutManager(new GridLayoutManager(this, 2));
                adapter = new WallpaperAdapterWppsCateg(WallpaperService.WallpaperCat, R.layout.item_wallpaper_2, this, this);
                break;
            case 2:
                rc.setLayoutManager(new GridLayoutManager(this, 3));
                adapter = new WallpaperAdapterWppsCateg(WallpaperService.WallpaperCat, R.layout.item_wallpaper_3, this, this);
                break;
            case 3:
                rc.setLayoutManager(new GridLayoutManager(this, 4));
                adapter = new WallpaperAdapterWppsCateg(WallpaperService.WallpaperCat, R.layout.item_wallpaper_4, this, this);
                break;
            default:
                break;
        }


        rc.setAdapter(adapter);
    }

    public void cargarDatosPrincipales (String tag){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getNombre() != null) {
                if (wallpaper.getNombre().equals(tag)) {
                    // Se comprueba si el wallpaper existe ya en la lista, si no este es agregado
                    try {
                        if (!WallpaperService.WallpaperCat.contains(wallpaper)) {
                            WallpaperService.addWallpaperWallpaperCat(wallpaper);
                        }
                        String cuantoswpps = String.valueOf(WallpaperService.cuantosList());
                        int cuanto = WallpaperService.cuantosList();
                        if (cuanto == 1){
                            cuantos.setText(String.format("%s Wallpaper", cuantoswpps));
                        }else {
                            cuantos.setText(String.format("%s Wallpapers", cuantoswpps));
                        }
                        cuantos.setVisibility(View.VISIBLE);
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosPrincipales1 (String tag){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag1() != null) {
                if (wallpaper.getTag1().equals(tag)) {
                    // Se comprueba si el wallpaper existe ya en la lista, si no este es agregado
                    try {
                        if (!WallpaperService.WallpaperCat.contains(wallpaper)) {
                            WallpaperService.addWallpaperWallpaperCat(wallpaper);
                        }
                        String cuantoswpps = String.valueOf(WallpaperService.cuantosList());
                        int cuanto = WallpaperService.cuantosList();
                        if (cuanto == 1){
                            cuantos.setText(String.format("%s Wallpaper", cuantoswpps));
                        }else {
                            cuantos.setText(String.format("%s Wallpapers", cuantoswpps));
                        }
                        cuantos.setVisibility(View.VISIBLE);
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosPrincipales2 (String tag){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag2() != null) {
                if (wallpaper.getTag2().equals(tag)) {
                    // Se comprueba si el wallpaper existe ya en la lista, si no este es agregado
                    try {
                        if (!WallpaperService.WallpaperCat.contains(wallpaper)) {
                            WallpaperService.addWallpaperWallpaperCat(wallpaper);
                        }
                        String cuantoswpps = String.valueOf(WallpaperService.cuantosList());
                        int cuanto = WallpaperService.cuantosList();
                        if (cuanto == 1){
                            cuantos.setText(String.format("%s Wallpaper", cuantoswpps));
                        }else {
                            cuantos.setText(String.format("%s Wallpapers", cuantoswpps));
                        }
                        cuantos.setVisibility(View.VISIBLE);
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosPrincipales3 (String tag){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag3() != null) {
                if (wallpaper.getTag3().equals(tag)) {
                    // Se comprueba si el wallpaper existe ya en la lista, si no este es agregado
                    try {
                        if (!WallpaperService.WallpaperCat.contains(wallpaper)) {
                            WallpaperService.addWallpaperWallpaperCat(wallpaper);
                        }
                        String cuantoswpps = String.valueOf(WallpaperService.cuantosList());
                        int cuanto = WallpaperService.cuantosList();
                        if (cuanto == 1){
                            cuantos.setText(String.format("%s Wallpaper", cuantoswpps));
                        }else {
                            cuantos.setText(String.format("%s Wallpapers", cuantoswpps));
                        }
                        cuantos.setVisibility(View.VISIBLE);
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosPrincipales4 (String tag){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag4() != null) {
                if (wallpaper.getTag4().equals(tag)) {
                    // Se comprueba si el wallpaper existe ya en la lista, si no este es agregado
                    try {
                        if (!WallpaperService.WallpaperCat.contains(wallpaper)) {
                            WallpaperService.addWallpaperWallpaperCat(wallpaper);
                        }
                        String cuantoswpps = String.valueOf(WallpaperService.cuantosList());
                        int cuanto = WallpaperService.cuantosList();
                        if (cuanto == 1){
                            cuantos.setText(String.format("%s Wallpaper", cuantoswpps));
                        }else {
                            cuantos.setText(String.format("%s Wallpapers", cuantoswpps));
                        }
                        cuantos.setVisibility(View.VISIBLE);
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarTodosDatos (){
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){
            try {
                assert wallpaper != null;
                if (!WallpaperService.WallpaperCat.contains(wallpaper)) {
                    WallpaperService.addWallpaperWallpaperCat(wallpaper);
                }
                String cuantoswpps = String.valueOf(WallpaperService.cuantosList());
                int cuanto = WallpaperService.cuantosList();
                if (cuanto == 1){
                    cuantos.setText(String.format("%s Wallpaper", cuantoswpps));
                }else {
                    cuantos.setText(String.format("%s Wallpapers", cuantoswpps));
                }
                cuantos.setVisibility(View.VISIBLE);
                Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
            }
        }
    }
}