package com.futurefix.wallsnakano;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ConfigActivity extends AppCompatActivity{

    CheckBox mCheckBox;
    boolean estadoAviso = false;
    TextView aviso, notis, app, toolbar, cuantasColumnas;
    int cual = Auxiliar.estadoSelectorColumnas;
    String [] lista = new String[]{"1 x 1", "2 x 2", "3 x 3", "4 x 4"};
    SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        // Url para compartir la app
        String url = "https://play.google.com/store/apps/details?id=com.futurefix.wallsnakano";

        /*
         * Referenciar las variables con su respectivo item en el activity_config.xml
         * {@link R.layout.activity_config}.
         */
        TextView califica = findViewById(R.id.Calificar);
        mCheckBox = findViewById(R.id.checkBox);
        app = findViewById(R.id.AppConfig);
        notis = findViewById(R.id.Notification);
        toolbar = findViewById(R.id.texto_toolbar);
        cuantasColumnas = findViewById(R.id.cuantas_columnas);
        aviso = findViewById(R.id.Aviso);

        // Setear el texto de configuraciones al toolbar
        toolbar.setText(R.string.configuraciones);
        cuantasColumnas.setText(String.format("Ahora: %s", lista[Auxiliar.estadoSelectorColumnas]));

        // Evento Onclick que te manda a la url de la app en la PlayStore
        califica.setOnClickListener(v -> {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        /*
         * Evento Onclick que abre un layout con las politicas
         * {@link R.layout.aviso_activity}.
         */
        aviso.setOnClickListener(v -> {
            setContentView(R.layout.aviso_activity);
            estadoAviso = true;
        });

        // Evento Onclick que te manda a las configuraciones de las notificaicones de la app
        notis.setOnClickListener(v -> {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            } else {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", getPackageName());
                intent.putExtra("app_uid", getApplicationInfo().uid);
            }
            startActivity(intent);
        });

        // Evento Onclick que te manda a las configuraciones de la app
        app.setOnClickListener(v -> {
            Intent intente = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intente.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intente);
        });
    }

    // Cuando se pulsa el boton "atras" del telefono
    @Override
    public void onBackPressed() {
        // comprueba si el layout esta activo
        if (estadoAviso){
            // Carga el layout de configuraciones
            setContentView(R.layout.activity_config);
            estadoAviso = !estadoAviso;
        }else {
            // Sale de la actividad y guarda el estado del checkBox
            Auxiliar.guardarEstadoCheckBox(mCheckBox.isChecked());
            Auxiliar.guardarEstadoelectorColumnas(cual);
            finish();
        }
    }

    // Cuando la app se suspende
    @Override
    public void onPause() {
        super.onPause();
        // Guardar el estado del checkBox
        save(mCheckBox.isChecked());
        saveColumnas(cual);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCheckBox.setChecked(load());
        cual = loadColumnas();
        Auxiliar.guardarEstadoCheckBox(mCheckBox.isChecked());
        Auxiliar.guardarEstadoelectorColumnas(cual);
    }

    private void save(final boolean isChecked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked); editor.apply();
    }

    private void saveColumnas(int intero){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("columnas", intero);
        editor.apply();
    }

    private int loadColumnas(){
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getInt("columnas", 3);
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }

    public void elegirColumnas(View view) {
        int auxCual;
        int aux2 = Auxiliar.estadoSelectorColumnas;
        auxCual = cual;
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Selecciona tamaño de las columnas");
        mBuilder.setCancelable(false);
        mBuilder.setSingleChoiceItems(lista, Auxiliar.estadoSelectorColumnas, (dialog, which) -> {
            cual = which;
            Auxiliar.guardarEstadoelectorColumnas(which);
        });

        mBuilder.setPositiveButton(R.string.set, (dialog, which) -> {
            cuantasColumnas.setText(String.format("Ahora: %s", lista[cual]));
            Auxiliar.cambiaronColumnas=true;
            Auxiliar.cambiaronColumnasNino=true;
            Auxiliar.cambiaronColumnasItsuki=true;
            Auxiliar.cambiaronColumnasMiku=true;
            Auxiliar.cambiaronColumnasYotsuba=true;
            Auxiliar.cambiaronColumnasIchika=true;
            dialog.dismiss();
        });

        mBuilder.setNegativeButton(R.string.cancelar, (dialog, which) -> {
                cual = auxCual;
                Auxiliar.estadoSelectorColumnas=aux2;
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
}