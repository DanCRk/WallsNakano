package com.futurefix.wallsnakano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        String url = "https://play.google.com/store/apps/details?id=com.futurefix.wallsnakano";

        TextView califica = findViewById(R.id.Calificar);

        califica.setOnClickListener(v -> {
             {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        TextView aviso = findViewById(R.id.Aviso);

        aviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.aviso_activity);
            }
        });

        TextView notis = findViewById(R.id.Notification);

        notis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS));
            }
        });
        
        TextView app = findViewById(R.id.AppConfig);

        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS));
            }
        });

    }
}