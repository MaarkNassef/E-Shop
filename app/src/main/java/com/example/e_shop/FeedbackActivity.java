package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Database.EShopDatabaseHelper;
import RecyclerView.FeedbackRecyclerViewAdapter;
import Session.Rating;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ImageButton back = findViewById(R.id.feedback_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackActivity.super.onBackPressed();
            }
        });

        EShopDatabaseHelper db = new EShopDatabaseHelper(getApplicationContext());
        Rating[] ratings = db.getFeedbacks();

        RecyclerView recyclerView = findViewById(R.id.feedback_recycler_view);
        FeedbackRecyclerViewAdapter feedbackRecyclerViewAdapter = new FeedbackRecyclerViewAdapter(getApplicationContext(), ratings);
        recyclerView.setAdapter(feedbackRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}