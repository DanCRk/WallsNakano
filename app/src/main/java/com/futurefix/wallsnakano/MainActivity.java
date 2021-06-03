package com.futurefix.wallsnakano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.futurefix.wallsnakano.fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    Fragment newFragment = null;
    private String url;
    TextView textoToolbar;
    SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url= "https://play.google.com/store/apps/details?id=com.futurefix.wallsnakano";

        // Referenciar las weas locas
        textoToolbar = findViewById(R.id.texto_toolbar);
        switchCompat = findViewById(R.id.switch_modooscuro);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        // Icono para el menu lateral

        Drawable icono = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_barrasmenu, this.getTheme());

        // Establecer evento onclick al navigationView

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(icono);
        actionBarDrawerToggle.setToolbarNavigationClickListener(v -> {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        actionBarDrawerToggle.syncState();

        // Cargar fragment principal
        newFragment = new MainFragment();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        // Para que la mmda no se cargue dos veces :)
        comprobar(currentFragment);
    }

    private void comprobar(Fragment currentFragment){
        if (currentFragment == null) {
            //carga del primer fragment justo en la carga inicial de la app
            loadFragment(newFragment);
        } else if (!currentFragment.getClass().getName().equalsIgnoreCase(newFragment.getClass().getName())) {
            //currentFragment no concide con newFragment
            loadFragment(newFragment);
        }
    }

    private void loadFragment(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, newFragment, newFragment.getClass().getName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
//        if (menuItem.getItemId() == R.id.upgrade_menu) {
//        }
//        if (menuItem.getItemId() == R.id.favoritos_menu) {
//        }
//        if (menuItem.getItemId() == R.id.perfil_menu) {
//        }
        if (menuItem.getItemId() == R.id.calificanos_menu) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            finish();
        }
        if (menuItem.getItemId() == R.id.configuracion_menu) {
            Intent intent = new Intent(this, ConfigActivity.class);
            startActivity(intent);
        }
        return false;
    }



}
