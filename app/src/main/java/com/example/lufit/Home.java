package com.example.lufit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.lufit.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    Boolean logou = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);



        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                int itemId = menuItem.getItemId();

                if (itemId == R.id.navhome){
                    homeClicked();
                }else if (itemId == R.id.navconversar){
                    conversaClicked();
                } else if (itemId == R.id.navpreferencias){
                    preferenciasClicked();
                }
                if (itemId == R.id.navhome){
                    return true;
                }else if (itemId == R.id.navconversar){
                    return true;
            } else if (itemId == R.id.navpreferencias){
                    return true;
            }
                return true;
            }

        });

        homeClicked();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void loadFragment(Fragment fragment){
        Bundle b = getIntent().getExtras();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(b);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void homeClicked(){
        loadFragment(new HomeFragment());
    }
    private void conversaClicked(){
        Bundle b = getIntent().getExtras();
        if (b.getString("email")!= null) {
            logou = true;
            b.putBoolean("logou", logou);
            Intent i = new Intent(Home.this, Conversa.class);
            i.putExtras(b);
            startActivity(i);
        }else{
            finish();
        }
    }
    private void preferenciasClicked(){
        loadFragment(new PreferencesFragment());
    }
}