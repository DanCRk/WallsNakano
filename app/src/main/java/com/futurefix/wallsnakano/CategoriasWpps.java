package com.futurefix.wallsnakano;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.futurefix.wallsnakano.adaptadores.WallpaperAdapterWppsCateg;
import com.futurefix.wallsnakano.adaptadores.WallpaperService;
import com.futurefix.wallsnakano.modelos.Wallpaper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CategoriasWpps extends AppCompatActivity {

    RecyclerView rc;
    TextView tv, cuantos;

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

        tv.setText(nombre);

        // Cargar Lista
        cargarLista();
        // Cargar Datos
        cargarDatos(tag, "tag1");
        cargarDatos(tag, "tag2");
        cargarDatos(tag, "tag3");
        cargarDatos(tag, "tag4");
    }

    @Override
    public void onBackPressed() {
        WallpaperService.clearList();
        finish();
        cuantos.setVisibility(View.GONE);
    }

    public void cargarLista() {
        rc.setLayoutManager(new GridLayoutManager(this, 3));
        WallpaperAdapterWppsCateg adapter = new WallpaperAdapterWppsCateg(WallpaperService.WallpaperCat, R.layout.item_wallpaper, this, this);
        rc.setAdapter(adapter);
    }

    public void cargarDatos(String tag, String queTag) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Wallpapers");
        Query query = reference.orderByChild(queTag).equalTo(tag);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                try {
                    Wallpaper wallpaper = snapshot.getValue(Wallpaper.class);
                    assert wallpaper != null;
                    wallpaper.setId(snapshot.getKey());
                    if (!WallpaperService.WallpaperCat.contains(wallpaper)){
                        WallpaperService.addWallpaperWallpaperCat(wallpaper);
                    }
                    String cuantoswpps = String.valueOf(WallpaperService.cuantosList());
                    cuantos.setText(String.format("%s Wallpapers", cuantoswpps));
                    cuantos.setVisibility(View.VISIBLE);
                    Objects.requireNonNull(rc.getAdapter()).notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error, no se pueden cargar los wallpapers", Toast.LENGTH_LONG).show();
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