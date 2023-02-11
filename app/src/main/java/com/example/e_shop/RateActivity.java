package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import Database.EShopDatabaseHelper;
import Session.Session;

public class RateActivity extends AppCompatActivity {
    TextView result;
    RatingBar bar;
    Button btn;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        result=findViewById(R.id.Rate_Text_view);
        bar= findViewById(R.id.rate_bar);
        btn =findViewById(R.id.Rate_Submit_btn);
        back =findViewById(R.id.rate_back_btn);
        EditText comment = findViewById(R.id.Rate_Comment);

        final float[] Rating = {0f};
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                result.setText("Rate is "+rating+"");
                Rating[0] = rating;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RateActivity.super.onBackPressed();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EShopDatabaseHelper db = new EShopDatabaseHelper(getApplicationContext());
                db.insertRate(db.getOrderId(Session.getInstance().getCustomer().getId()), String.valueOf(Rating[0]), comment.getText().toString());
                db.updateQuantity(Integer.parseInt(db.getOrderId(Session.getInstance().getCustomer().getId())));
                db.insertNewOrder(String.valueOf(Session.getInstance().getCustomer().getId()));
                Intent i=new Intent(RateActivity.this,ProductsActivity.class);
                startActivity(i);
            }
        });
    }
}