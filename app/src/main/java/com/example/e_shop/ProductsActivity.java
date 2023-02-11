package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import Database.EShopDatabaseHelper;
import RecyclerView.CategoriesRecyclerViewAdapter;
import Session.Session;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        RecyclerView recyclerView = findViewById(R.id.products_categories);
        EShopDatabaseHelper db = new EShopDatabaseHelper(getApplicationContext());
        CategoriesRecyclerViewAdapter gar = new CategoriesRecyclerViewAdapter(getApplicationContext(), db.getCategories());
        recyclerView.setAdapter(gar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton logout = findViewById(R.id.products_menu_btn);
        ImageButton search = findViewById(R.id.products_search_btn);
        ImageButton cart = findViewById(R.id.products_cart_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("rememberMe","false");
                editor.apply();
                Session session = Session.getInstance();
                session.setCustomer(null);
                startActivity(new Intent(ProductsActivity.this, SignInActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductsActivity.this, SearchActivity.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductsActivity.this, CartActivity.class));
            }
        });
    }
}