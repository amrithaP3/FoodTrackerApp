package com.example.recipeapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.recipeapp.HomeFragment;
import com.example.recipeapp.R;
import com.example.recipeapp.ShoppingListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WelcomeActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener{

    HomeFragment homeFragment = new HomeFragment();
    ShoppingListFragment shoppingListFragment = new ShoppingListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navbar = findViewById(R.id.bottomNavigationView);
        navbar.setOnNavigationItemSelectedListener(this);
        navbar.setSelectedItemId(R.id.homeNavbarIcon);

    }

    @Override
    public boolean
    onNavigationItemSelected(MenuItem item)
    {
        int viewId = item.getItemId();
        if (viewId == R.id.homeNavbarIcon){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;

        } else if (viewId == R.id.shopping_listNavbarIcon){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, shoppingListFragment)
                    .commit();
            return true;

        } else {
            return false;
        }
    }
}