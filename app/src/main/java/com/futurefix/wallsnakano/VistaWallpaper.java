package com.futurefix.wallsnakano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class VistaWallpaper extends AppCompatActivity {

    ImageView img_fondo, img_ampliada,img;
    ImageButton cerrar;
    Uri url;
    private View rootView;
    boolean ampliado=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_wallpaper);

        cerrar = findViewById(R.id.boton_cerrar_vista);
        img = findViewById(R.id.imagen_vista);
        img_ampliada = findViewById(R.id.imagen_vista_ampliada);
        img_fondo = findViewById(R.id.imagen_vista_fondo);
        rootView = (View) findViewById(R.id.content);

        final Intent intent = getIntent();
        url = Uri.parse(intent.getStringExtra("ItemUrl"));

        Glide.with(this).load(url).into(img);
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

        cerrar.setOnClickListener(v -> finish());
    }
}