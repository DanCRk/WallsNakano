package com.futurefix.wallsnakano;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.futurefix.wallsnakano.adaptadores.WallpaperService;
import com.futurefix.wallsnakano.fragments.MainFragment;
import com.futurefix.wallsnakano.modelos.Wallpaper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    Fragment newFragment = null;
    private String url;
    TextView textoToolbar;
    private InterstitialAd mInterstitial;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        // Inicializar los anuncios
        MobileAds.initialize(this, initializationStatus -> {
        });

        // Add intestical

        cargarAdd();

        url= "https://play.google.com/store/apps/details?id=com.futurefix.wallsnakano";

        // Referenciar las weas locas
        textoToolbar = findViewById(R.id.texto_toolbar);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        // Solicitar los datos de todos los wallpapers

        cargarTodosDatos();

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

        Auxiliar.guardarEstadoCheckBox(load());
        Auxiliar.guardarEstadoelectorColumnas(load2());
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
//        if (menuItem.getItemId() == R.id.favoritos_menu) {
//        }
//        if (menuItem.getItemId() == R.id.perfil_menu) {
//        }
        if (menuItem.getItemId()==R.id.comparte_menu){
            Intent compartir = new Intent(Intent.ACTION_SEND);
            compartir.setType("text/plain");
            String mensaje = "Mira esta app de Wallpapers de las Quintillizas: "+url;
            compartir.putExtra(Intent.EXTRA_TEXT, mensaje);
            startActivity(Intent.createChooser(compartir,"Compartir via"));
        }
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
        if (menuItem.getItemId()==R.id.info_menu){
            Intent intent = new Intent(this, AcercaActivity.class);
            startActivity(intent);
        }
        return false;
    }

    public void cargarTodosDatos() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Wallpapers");
        reference.getRef();
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                Wallpaper wallpaper = snapshot.getValue(Wallpaper.class);
                assert wallpaper != null;
                wallpaper.setId(snapshot.getKey());
                if (!WallpaperService.todosWallpapers.contains(wallpaper)){
                    WallpaperService.addWallpaperTodos(wallpaper);
                }
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public void cargarAdd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-2030839089746380/7557193024", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitial = interstitialAd;
                Log.i("nocarga", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("algo", loadAdError.getMessage());
                mInterstitial = null;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        save(Auxiliar.estadoactualCheckBox);
        saveColumnas(Auxiliar.estadoSelectorColumnas);
        if (Auxiliar.iteradorAnuncios==4){
            if (mInterstitial!= null) {
                mInterstitial.show(this);
                cargarAdd();
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
            Auxiliar.iteradorAnuncios=1;
        }
        Auxiliar.guardarEstadoCheckBox(load());
        Auxiliar.guardarEstadoelectorColumnas(load2());
    }

    private void save(boolean isChecked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check", isChecked);
        editor.apply();
    }

    private void saveColumnas(int columnas){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("columnas", columnas);
        editor.apply();
    }

    private boolean load() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("check", false);
    }

    private int load2() {
        SharedPreferences sharedPreferences2 = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences2.getInt("columnas", 2);
    }
}
