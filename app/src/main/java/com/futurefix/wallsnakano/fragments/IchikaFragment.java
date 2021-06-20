package com.futurefix.wallsnakano.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurefix.wallsnakano.R;
import com.futurefix.wallsnakano.adaptadores.WallpaperAdapter;
import com.futurefix.wallsnakano.adaptadores.WallpaperService;
import com.futurefix.wallsnakano.modelos.Wallpaper;

import java.util.Objects;

public class IchikaFragment extends Fragment {

    RecyclerView rc;

    public IchikaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallpapers_fragment, container, false);
        // Referenciar
        rc = view.findViewById(R.id.recyclerViewWallpaper);
        // Cargar Lista
        cargarLista();
        // Cargar Datos
        cargarDatosPrincipales("ichika");
        cargarDatosSecundarios1("ichika");
        cargarDatosSecundarios2("ichika");
        cargarDatosSecundarios3("ichika");
        cargarDatosSecundarios4("ichika");
        return view;
    }

    public void cargarLista() {
        rc.setLayoutManager(new GridLayoutManager(getContext(), 3));
        WallpaperAdapter adapter = new WallpaperAdapter(WallpaperService.wallpaperIchika, R.layout.item_wallpaper, getParentFragment(), getContext(),21);
        rc.setAdapter(adapter);
    }

    public void cargarDatosPrincipales(String nombrePrincipal) {

        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getNombre() != null) {
                if (wallpaper.getNombre().equals(nombrePrincipal)) {
                    // Se comprueba si el wallpaper existe ya en la lista, si no este es agregado
                    try {
                        if (!WallpaperService.wallpaperIchika.contains(wallpaper)) {
                            WallpaperService.addWallpaperIchika(wallpaper);
                        }
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosSecundarios1(String nombrePrincipal){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag1() != null){
                if (wallpaper.getTag1().equals(nombrePrincipal)){
                    try {
                        if (!WallpaperService.wallpaperIchika.contains(wallpaper)) {
                            WallpaperService.addWallpaperIchika(wallpaper);
                        }
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosSecundarios2 (String nombrePrincipal){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag2()!= null){
                if (wallpaper.getTag2().equals(nombrePrincipal)){
                    try {
                        if (!WallpaperService.wallpaperIchika.contains(wallpaper)) {
                            WallpaperService.addWallpaperIchika(wallpaper);
                        }
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosSecundarios3 (String nombrePrincipal){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag3()!= null){
                if (wallpaper.getTag3().equals(nombrePrincipal)){
                    try {
                        if (!WallpaperService.wallpaperIchika.contains(wallpaper)) {
                            WallpaperService.addWallpaperIchika(wallpaper);
                        }
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void cargarDatosSecundarios4 (String nombrePrincipal){
        // Recorremos la lista de wallpapers
        for (Wallpaper wallpaper : WallpaperService.todosWallpapers){

            // Comprobamos si el wallpaper tiene en alguna propiedad el nombre que se requiere
            if (wallpaper.getTag4()!= null){
                if (wallpaper.getTag4().equals(nombrePrincipal)){
                    try {
                        if (!WallpaperService.wallpaperIchika.contains(wallpaper)) {
                            WallpaperService.addWallpaperIchika(wallpaper);
                        }
                        Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
