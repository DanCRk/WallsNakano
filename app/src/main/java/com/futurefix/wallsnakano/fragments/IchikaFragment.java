package com.futurefix.wallsnakano.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurefix.wallsnakano.R;
import com.futurefix.wallsnakano.adaptadores.WallpaperAdapter;
import com.futurefix.wallsnakano.listas.WallpaperServiceIchika;
import com.futurefix.wallsnakano.modelos.Wallpaper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

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
        cargarDatos();
        return view;
    }

    public void cargarLista() {
        rc.setLayoutManager(new GridLayoutManager(getContext(), 3));
        WallpaperAdapter adapter = new WallpaperAdapter(WallpaperServiceIchika.wallpaperIchika, R.layout.item_wallpaper, getParentFragment(), getContext(),21);
        rc.setAdapter(adapter);
    }

    public void cargarDatos() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Wallpapers");
        Query query = reference.orderByChild("nombre").equalTo("ichika");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                try {
                    Wallpaper wallpaper = snapshot.getValue(Wallpaper.class);
                    assert wallpaper != null;
                    wallpaper.setId(snapshot.getKey());
                    if (!WallpaperServiceIchika.wallpaperIchika.contains(wallpaper)){
                        WallpaperServiceIchika.addWallpaper(wallpaper);
                    }
                    Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }
            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) { }
            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) { }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) { }
        });
    }
}