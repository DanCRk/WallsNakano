package com.futurefix.wallsnakano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class VistaWallpaper extends AppCompatActivity {

    ImageView img;
    Uri url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vista_wallpaper);

        img = findViewById(R.id.imagen_vista);

        final Intent intent = getIntent();
        url = Uri.parse(intent.getStringExtra("ItemUrl"));

        Glide.with(this).load(url).into(img);

    }
}