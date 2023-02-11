package com.example.e_shop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import Database.EShopDatabaseHelper;
import Session.Session;
public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        TextView pass=findViewById(R.id.change_password_new_password);
        TextView confirmPass=findViewById(R.id.change_password_confirm_password);
        Button changePassword=findViewById(R.id.change_password_confirm_btn);
        EShopDatabaseHelper databaseHelper=new EShopDatabaseHelper(getApplicationContext());
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = pass.getText().toString();
                String confirmpassword = confirmPass.getText().toString();
                String email= getIntent().getStringExtra("email");
                if(newPassword.equals(confirmpassword))
                {
                    databaseHelper.updatePassword(newPassword,email);
                    Toast.makeText(getApplicationContext(),"Updated Successfully!!!!",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(ChangePasswordActivity.this,SignInActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}