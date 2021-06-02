package com.futurefix.wallsnakano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.blurry.Blurry;

public class VistaWallpaper extends AppCompatActivity {

    ImageView img_fondo, img;
    ImageButton cerrar;
    Uri url;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vista_wallpaper);

        cerrar = findViewById(R.id.boton_cerrar_vista);
        img = findViewById(R.id.imagen_vista);
        img_fondo = findViewById(R.id.imagen_vista_fondo);
        rootView = (View) findViewById(R.id.content);

        final Intent intent = getIntent();
        url = Uri.parse(intent.getStringExtra("ItemUrl"));

        Glide.with(this).load(url).into(img);
        Glide.with(this).load(url).into(img_fondo);

        rootView.post(new Runnable() {
            @Override
            public void run() {
                Blurry.with(getApplicationContext()).capture(rootView).into(img_fondo);
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}