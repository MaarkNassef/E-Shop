package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import Database.EShopDatabaseHelper;

public class ModifyCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_category);
        ImageButton back = findViewById(R.id.modify_category_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyCategoryActivity.super.onBackPressed();
            }
        });

        EShopDatabaseHelper db = new EShopDatabaseHelper(getApplicationContext());
        Spinner categories = findViewById(R.id.modify_category_droplist);
        EditText catName = findViewById(R.id.modify_category_category_name);
        Button add = findViewById(R.id.modify_category_add_btn);
        Button update = findViewById(R.id.modify_category_update_btn);
        Button delete = findViewById(R.id.modify_category_delete_btn);

        ArrayList<String> categoriesList = new ArrayList<String>(Arrays.asList(db.showCategories()));
        categoriesList.add("Add new category...");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);

        final String[] SelectedChoice = {""};

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choice = ((TextView)view).getText().toString();
                SelectedChoice[0] = choice;
                catName.setText("");
                if (!choice.equals("Add new category...")) {
                    catName.setText(choice);
                } else {
                    SelectedChoice[0] = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedChoice[0].equals("")) {
                    db.addCategory(catName.getText().toString());
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                    ArrayList<String> categoriesList = new ArrayList<String>(Arrays.asList(db.showCategories()));
                    categoriesList.add("Add new category...");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categoriesList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categories.setAdapter(adapter);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SelectedChoice[0].equals("")) {
                    db.updateCategory(SelectedChoice[0], catName.getText().toString());
                    Toast.makeText(getApplicationContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();

                    ArrayList<String> categoriesList = new ArrayList<String>(Arrays.asList(db.showCategories()));
                    categoriesList.add("Add new category...");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categoriesList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categories.setAdapter(adapter);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SelectedChoice[0].equals("")) {
                    db.deleteCategory(SelectedChoice[0]);
                    Toast.makeText(getApplicationContext(), "Deleted successfully!", Toast.LENGTH_SHORT).show();

                    ArrayList<String> categoriesList = new ArrayList<String>(Arrays.asList(db.showCategories()));
                    categoriesList.add("Add new category...");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categoriesList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categories.setAdapter(adapter);
                }
            }
        });
    }
}