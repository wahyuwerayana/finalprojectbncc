package com.myproject.bimbelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.myproject.bimbelapp.ui.counter.CounterFragment;
import com.myproject.bimbelapp.ui.login.LoginActivity;
import com.myproject.bimbelapp.ui.luas.LuasFragment;
import com.myproject.bimbelapp.ui.volume.VolumeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CounterFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_counter);
        }
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_counter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CounterFragment()).commit();
                break;
            case R.id.nav_luas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LuasFragment()).commit();
                break;
            case R.id.nav_volume:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VolumeFragment()).commit();
                break;
            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Log out!")
                        .setMessage("Are you sure want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            mAuth.signOut();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        })
                        .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                        .show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}