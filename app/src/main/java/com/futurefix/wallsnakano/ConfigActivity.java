package com.futurefix.wallsnakano;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class ConfigActivity extends AppCompatActivity {

    CheckBox mCheckBox;
    boolean estadoAviso = false;
    public TextView aviso, notis, app;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        String url = "https://play.google.com/store/apps/details?id=com.futurefix.wallsnakano";

        TextView califica = findViewById(R.id.Calificar);
        mCheckBox = findViewById(R.id.checkBox);
        app = findViewById(R.id.AppConfig);
        notis = findViewById(R.id.Notification);

        califica.setOnClickListener(v -> {
             {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        aviso = findViewById(R.id.Aviso);

        aviso.setOnClickListener(v -> {
            setContentView(R.layout.aviso_activity);
            estadoAviso = true;
        });

        notis.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)));

        app.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)));

        // Apartado de Columnas

        Spinner spinner = findViewById(R.id.spinner_columnas);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.columnas_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (estadoAviso){
            setContentView(R.layout.activity_config);
            estadoAviso = !estadoAviso;
        }else {
            Estado.guardarEstado(mCheckBox.isChecked());
            finish();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        save(mCheckBox.isChecked());
    }

    @Override
    public void onResume() {
        super.onResume();
        mCheckBox.setChecked(load());
        Estado.guardarEstado(mCheckBox.isChecked());
    }

    private void save(final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked); editor.apply();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }


}