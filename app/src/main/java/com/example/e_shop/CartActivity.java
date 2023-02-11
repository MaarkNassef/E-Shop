package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Database.EShopDatabaseHelper;
import RecyclerView.CartRecyclerViewAdapter;
import Session.Product;
import Session.Session;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ImageButton back = findViewById(R.id.cart_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartActivity.super.onBackPressed();
            }
        });
        int sum=0;
        TextView totalPrice=findViewById(R.id.cart_total_price);
        EShopDatabaseHelper databaseHelper=new EShopDatabaseHelper(getApplicationContext());
        Session session=Session.getInstance();
        Product product=databaseHelper.showCartDetails(Integer.parseInt(databaseHelper.getOrderId((session.getCustomer().getId()))));
        String [] total=new String[product.getProdPrice().length];
        total= product.getProdPrice();
        RecyclerView recyclerView = findViewById(R.id.cart_recycler_view);
        CartRecyclerViewAdapter recyclerViewAdapter = new CartRecyclerViewAdapter(this,product.getProdName(),product.getProdPrice(),product.getBmp(),product.getQuantity(), totalPrice);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0;i<product.getProdPrice().length;i++)
        {
            sum+=Integer.parseInt(total[i])*Integer.parseInt(product.getQuantity()[i]);
        }
        totalPrice.setText(String.valueOf(sum));

        Button checkout = findViewById(R.id.cart_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, AddressActivity.class));
            }
        });
    }

}