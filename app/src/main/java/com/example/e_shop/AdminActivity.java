package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button genReports = findViewById(R.id.admin_generate_report_btn);
        Button updateCategories = findViewById(R.id.admin_update_categories_btn);
        Button updateProducts = findViewById(R.id.admin_update_products_btn);
        Button visualizationCharts = findViewById(R.id.admin_visualization_charts_btn);
        Button feedback = findViewById(R.id.admin_feedback_btn);
        Button logout = findViewById(R.id.admin_log_out_btn);

        genReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ReportActivity.class));
            }
        });
        updateCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ModifyCategoryActivity.class));
            }
        });
        updateProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ModifyProductActivity.class));
            }
        });
        visualizationCharts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, VisualizationActivity.class));
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, FeedbackActivity.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, SignInActivity.class));
            }
        });
    }

}