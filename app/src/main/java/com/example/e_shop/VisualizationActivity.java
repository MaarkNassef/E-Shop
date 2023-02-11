package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

import Database.EShopDatabaseHelper;
import Session.Product;

public class VisualizationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualization);
        EShopDatabaseHelper db=new EShopDatabaseHelper(getApplicationContext());
        ImageButton back=findViewById(R.id.visualization_back_btn);
        Product product=db.prcentageofsell();
        AnyChartView anyChartView=findViewById(R.id.chartview);
        int[] sum = new int[product.getQuantity().length];
        for (int i=0;i<product.getQuantity().length;i++)
        {
            sum[i]=Integer.parseInt(product.getQuantity()[i]);
        }
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries=new ArrayList<>();
        for (int i =0;i<product.getProdName().length;i++)
        {
            dataEntries.add(new ValueDataEntry(product.getProdName()[i],sum[i]));
        }
        pie.data(dataEntries);
        anyChartView.setChart(pie);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualizationActivity.this, AdminActivity.class));
            }
        });
    }
}