package com.example.e_shop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import Database.EShopDatabaseHelper;
import Session.Session;

public class ProductViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        TextView productCategory = findViewById(R.id.product_view_category_name);
        TextView productName = findViewById(R.id.product_view_name);
        TextView productPrice = findViewById(R.id.product_view_price);
        TextView productDetails = findViewById(R.id.product_view_specification_details);
        TextView Quantity=findViewById(R.id.product_view_quantity);
        Button addCart=findViewById(R.id.product_view_add_to_cart);
        ImageButton plus =findViewById(R.id.product_view_plus);
        ImageButton minus =findViewById(R.id.product_view_minus);
        ImageButton back=findViewById(R.id.product_view_back_btn);
        ImageButton search=findViewById(R.id.product_view_search_btn);
        ImageButton cart=findViewById(R.id.product_view_cart_btn);
        ImageButton logout=findViewById(R.id.product_view_menu_btn);
        ImageView image=findViewById(R.id.product_view_product_image);
        String pruductId;
        final int[] Count = {1};
        String categoryName;
        EShopDatabaseHelper databaseHelper=new EShopDatabaseHelper(getApplicationContext());
        Session session=Session.getInstance();
        String[] D =databaseHelper.getDeviceDetails(getIntent().getStringExtra("ProdName"));
        Bitmap bmp=databaseHelper.getImage(getIntent().getStringExtra("ProdName"));
        image.setImageBitmap(bmp);
        productName.setText(D[0]);
        productPrice.setText(D[1]);
        productDetails.setText(D[2]);
        pruductId=D[3];
        categoryName=D[4];
        productCategory.setText(categoryName);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductViewActivity.super.onBackPressed();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProductViewActivity.this,SearchActivity.class);
                startActivity(i);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Count[0] +=1;
                Quantity.setText(String.valueOf(Count[0]));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Count[0]!=1)
                {
                    Count[0] -=1;
                    Quantity.setText(String.valueOf(Count[0]));
                }

            }
        });
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int prodId=Integer.parseInt(pruductId);
                String id=databaseHelper.getOrderId(session.getCustomer().getId());
                if(id==null)
                {
                    databaseHelper.addOrderOfUser(session.getCustomer().getId());
                }
                String id2=databaseHelper.getOrderId(session.getCustomer().getId());
                int orderId= Integer.parseInt(id2);
                databaseHelper.addProductToCartInOrderDelails(orderId,prodId,Count[0]);
                Toast.makeText(getApplicationContext(),"Added Successfully!!!!",Toast.LENGTH_LONG).show();
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProductViewActivity.this,CartActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("rememberMe","false");
                editor.apply();
                Session session = Session.getInstance();
                session.setCustomer(null);
                startActivity(new Intent(ProductViewActivity.this, SignInActivity.class));
            }
        });
    }
}