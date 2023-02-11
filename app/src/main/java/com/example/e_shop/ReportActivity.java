package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import Database.EShopDatabaseHelper;
import RecyclerView.ReportsRecyclerViewAdapter;
import Session.Report;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        EShopDatabaseHelper databaseHelper=new EShopDatabaseHelper(getApplicationContext());
        EditText name=findViewById(R.id.reports_username);
        EditText date=findViewById(R.id.reports_date);
        Button generate=findViewById(R.id.reports_generate);
        ImageButton back = findViewById(R.id.report_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportActivity.super.onBackPressed();
            }
        });

        ImageButton calenderBtn = findViewById(R.id.reports_calender_btn);
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ReportActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;

                        String date1 = month + "/" + day + "/" + year;
                        date.setText(date1);
                    }
                },
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.reports_recycler_view);

        Report[] report =databaseHelper.showAllDataInReport();
        ReportsRecyclerViewAdapter reportsRecyclerViewAdapter = new ReportsRecyclerViewAdapter(getApplicationContext(), report);
        recyclerView.setAdapter(reportsRecyclerViewAdapter);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String custName=name.getText().toString();
                String custDate=date.getText().toString();
                if(!custName.equals("") && !custDate.equals(""))
                {
                    Report[] report =databaseHelper.specificUserAndDateReport(custName,custDate);
                    ReportsRecyclerViewAdapter reportsRecyclerViewAdapter = new ReportsRecyclerViewAdapter(getApplicationContext(), report);
                    recyclerView.setAdapter(reportsRecyclerViewAdapter);

                }
                else if(custName.equals("") && !custDate.equals(""))
                {
                    Report[] report =databaseHelper.specificOrderDateReport(custDate);
                    ReportsRecyclerViewAdapter reportsRecyclerViewAdapter = new ReportsRecyclerViewAdapter(getApplicationContext(), report);
                    recyclerView.setAdapter(reportsRecyclerViewAdapter);
                }
                else if(!custName.equals("") && custDate.equals(""))
                {
                    Report[] report =databaseHelper.specificUserReport(custName);
                    ReportsRecyclerViewAdapter reportsRecyclerViewAdapter = new ReportsRecyclerViewAdapter(getApplicationContext(), report);
                    recyclerView.setAdapter(reportsRecyclerViewAdapter);
                }
                else
                {
                    Report[] report =databaseHelper.showAllDataInReport();
                    ReportsRecyclerViewAdapter reportsRecyclerViewAdapter = new ReportsRecyclerViewAdapter(getApplicationContext(), report);
                    recyclerView.setAdapter(reportsRecyclerViewAdapter);
                }


            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}