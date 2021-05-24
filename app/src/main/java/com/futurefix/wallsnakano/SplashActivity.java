package com.futurefix.wallsnakano;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Validar si tiene conexion para dejarlo entrar en la aplicacion
        if (!EsCon(SplashActivity.this)) builderDialog(SplashActivity.this).show();

    }

    public Boolean EsCon(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){

            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, 2500);

            return mobile != null && mobile.isConnectedOrConnecting() ||
                    (wifi != null && wifi.isConnectedOrConnecting());
        }
        else return false;

    }

    //Alerta si no tiene conexion
    public AlertDialog.Builder builderDialog(Context c){

        AlertDialog.Builder builder = new AlertDialog.Builder(c);

        builder.setTitle("Sin Conexion a Internet");
        builder.setMessage("Esta aplicacion requiere de conexion a internet para cargar el contenido");

        builder.setPositiveButton("ok", (dialog, which) -> finish());
        return builder;
    }
}