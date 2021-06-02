package com.example.apptienda;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.apptienda.databinding.ActivityHomeDrawerSideCabeceraBinding;
import com.example.apptienda.databinding.ActivityHomeBinding;

import com.example.apptienda.helpers.App;
import com.example.apptienda.homefragments.tienda.PaqueteAdapter;
import com.example.apptienda.viewmodels.HomeViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private HomeViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm=new ViewModelProvider(this).get(HomeViewModel.class);
        ActivityHomeBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_home);
        ActivityHomeDrawerSideCabeceraBinding headerBinding = ActivityHomeDrawerSideCabeceraBinding.bind(binding.navView.getHeaderView(0));
        headerBinding.setViewModel(vm);
        binding.executePendingBindings();

        Picasso.get().setIndicatorsEnabled(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_tienda,R.id.nav_perfil,R.id.nav_pedidos).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);
        //añadimos el listener para el nav view
        navigationView.setNavigationItemSelectedListener(this);

        //añadimos el listener a la imagen del perfil
        View headerView = navigationView.getHeaderView(0);
        ImageButton accountButton = headerView.findViewById(R.id.profileButton);
        accountButton.setOnClickListener(v -> {
            navController.navigate(R.id.nav_perfil);
            drawer.closeDrawer(Gravity.LEFT,true);
        });

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
    //metodo para evitar salirse
    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 || navController.getBackStack().size() > 2) {
            super.onBackPressed();
        } else {

        }
    }
    // AIzaSyDdPlj7eqpV18XYF8yEF9O-KGV4xv36YDA
    //metodo para implementar acciones especificas diferentes de una navegacion
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        boolean arrived = NavigationUI.onNavDestinationSelected(item, navController);

        if (!arrived) {
           switch (item.getItemId()) {
               case R.id.action_close_sesion:
                   SharedPreferences sharedPref =App.getContext().getSharedPreferences("session",Context.MODE_PRIVATE);
                   sharedPref.edit().clear().commit();
                   startActivity(new Intent(this,LoginActivity.class));
                   break;
           }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(Gravity.LEFT,true);
        return arrived;
    }
    //metodo para desplegar el fragment detail de los paquetes

}