package com.futurefix.wallsnakano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class VistaWallpaper extends AppCompatActivity {

    ImageView img_fondo, img_ampliada,img,imgcargar;
    ImageButton cerrar;
    Uri url;
    ImageButton setwpp, descarga;
    boolean ampliado=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_vista_wallpaper);

        cerrar = findViewById(R.id.boton_cerrar_vista);
        img = findViewById(R.id.imagen_vista);
        imgcargar = findViewById(R.id.imagen_CARGAR);
        img_ampliada = findViewById(R.id.imagen_vista_ampliada);
        img_fondo = findViewById(R.id.imagen_vista_fondo);
        setwpp = findViewById(R.id.buttonsetwpp);
        descarga = findViewById(R.id.buttondescarga);

        final Intent intent = getIntent();
        url = Uri.parse(intent.getStringExtra("ItemUrl"));

        Glide.with(this).load(url).into(img);
        Glide.with(this).load(url).into(imgcargar);
        Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3))).into(img_fondo);
        Glide.with(this).load(url).into(img_ampliada);

        img_ampliada.setOnClickListener(v -> {
            if (ampliado){
                img_ampliada.setVisibility(View.GONE);
                ampliado = !ampliado;
            }
        });

        img.setOnClickListener(v -> {
            if (!ampliado){
                img_ampliada.setVisibility(View.VISIBLE);
                ampliado = !ampliado;
            }
        });

        setwpp.setOnClickListener(v -> {
            try {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                Bitmap bit = drawable.getBitmap();  // error java.lang.NullPointerException
                wallpaperManager.setBitmap(bit);
                Toast.makeText(VistaWallpaper.this, "Listo!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(VistaWallpaper.this, "No se establecio el wallpaper", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });

        descarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                    BitmapDrawable drawable = (BitmapDrawable) imgcargar.getDrawable();
                    Bitmap bit = drawable.getBitmap();  // error java.lang.NullPointerException
                    wallpaperManager.setBitmap(bit);
                    Toast.makeText(VistaWallpaper.this, "Listo!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(VistaWallpaper.this, "No se establecio el wallpaper", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        cerrar.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
       if (ampliado){
           img_ampliada.setVisibility(View.GONE);
           ampliado = !ampliado;
       }else{
           finish();
       }
    }

    private  void  solicitarpermisos() {
        int permisoguardar = ActivityCompat.checkSelfPermission(VistaWallpaper.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permisoguardar != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
            }
        }
    }
}