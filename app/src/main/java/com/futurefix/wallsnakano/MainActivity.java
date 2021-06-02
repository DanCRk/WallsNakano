package com.futurefix.wallsnakano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.futurefix.wallsnakano.fragments.ConfiguracionFragment;
import com.futurefix.wallsnakano.fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment newFragment = null;
    private String url;
    TextView textoToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url= "https://play.google.com/store/apps/details?id=com.futurefix.wallsnakano";

        // Referenciar las weas locas
        textoToolbar = findViewById(R.id.texto_toolbar);
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

/*
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();
*/

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

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
        /*
        if (menuItem.getItemId() == R.id.upgrade_menu) {
        }
        */
        /*
        if (menuItem.getItemId() == R.id.favoritos_menu) {
        }
        if (menuItem.getItemId() == R.id.perfil_menu) {
        }
        */
        if (menuItem.getItemId() == R.id.calificanos_menu) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            finish();
        }
        if (menuItem.getItemId() == R.id.configuracion_menu) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new ConfiguracionFragment());
            fragmentTransaction.commit();
            textoToolbar.setText(R.string.configuracion);
        }
//        if (menuItem.getItemId()==R.id.modo_oscuro_menu){
//        }
        
        return false;
    }



}
