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
import Session.ProductElement;

public class ModifyProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product);

        Spinner categories = findViewById(R.id.modify_product_droplist_category);
        Spinner products = findViewById(R.id.modify_product_droplist_product);
        EditText ProductName = findViewById(R.id.modify_product_product_name);
        EditText ProductPrice = findViewById(R.id.modify_product_product_price);
        EditText ProductDescription = findViewById(R.id.modify_product_product_description);
        TextView ProductQuantity = findViewById(R.id.modify_product_quantity);
        ImageButton plus = findViewById(R.id.modify_product_plus);
        ImageButton minus = findViewById(R.id.modify_product_minus);
        Button add = findViewById(R.id.modify_product_add_btn);
        Button update = findViewById(R.id.modify_product_update_btn);
        Button delete = findViewById(R.id.modify_product_delete_btn);

        ImageButton back = findViewById(R.id.modify_product_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyProductActivity.super.onBackPressed();
            }
        });

        EShopDatabaseHelper db = new EShopDatabaseHelper(getApplicationContext());
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, db.showCategories());
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapterCategory);
        final String[] SelectedCategory = {""};
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedCategory[0] = ((TextView)view).getText().toString();

                ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(db.showProductsByCatName(SelectedCategory[0])));
                productsList.add("Add new product...");
                ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productsList);
                adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                products.setAdapter(adapterProduct);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final String[] SelectedProduct = {""};
        products.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choice = ((TextView)view).getText().toString();
                SelectedProduct[0] = choice;
                ProductName.setText("");
                ProductPrice.setText("");
                ProductDescription.setText("");
                ProductQuantity.setText("1");
                if (!choice.equals("Add new product...")) {
                    ProductElement p = db.getProductInfo(SelectedProduct[0]);
                    ProductName.setText(p.getProdName());
                    ProductPrice.setText(p.getProdPrice());
                    ProductDescription.setText(p.getProdDescription());
                    ProductQuantity.setText(p.getProdQuantity());
                } else {
                    SelectedProduct[0] = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductQuantity.setText(""+(Integer.parseInt(ProductQuantity.getText().toString())+1));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(ProductQuantity.getText().toString())-1 >= 1) {
                    ProductQuantity.setText("" + (Integer.parseInt(ProductQuantity.getText().toString()) - 1));
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectedProduct[0].equals("")) {
                    db.addProduct(
                            ProductName.getText().toString(),
                            ProductQuantity.getText().toString(),
                            ProductPrice.getText().toString(),
                            ProductDescription.getText().toString(),
                            db.getCatIDViaCatName(SelectedCategory[0])
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                    ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(db.showProductsByCatName(SelectedCategory[0])));
                    productsList.add("Add new product...");
                    ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productsList);
                    adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    products.setAdapter(adapterProduct);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SelectedProduct[0].equals("")) {
                    db.updateProduct(
                            ProductName.getText().toString(),
                            ProductQuantity.getText().toString(),
                            ProductPrice.getText().toString(),
                            ProductDescription.getText().toString(),
                            SelectedProduct[0]
                    );
                    Toast.makeText(getApplicationContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();

                    ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(db.showProductsByCatName(SelectedCategory[0])));
                    productsList.add("Add new product...");
                    ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productsList);
                    adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    products.setAdapter(adapterProduct);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SelectedProduct[0].equals("")) {
                    db.deleteProduct(SelectedProduct[0]);
                    Toast.makeText(getApplicationContext(), "Deleted successfully!", Toast.LENGTH_SHORT).show();

                    ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(db.showProductsByCatName(SelectedCategory[0])));
                    productsList.add("Add new product...");
                    ArrayAdapter<String> adapterProduct = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productsList);
                    adapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    products.setAdapter(adapterProduct);
                }
            }
        });

    }
}