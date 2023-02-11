package com.example.e_shop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import Database.EShopDatabaseHelper;
public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Button resetPassword=findViewById(R.id.forget_password_reset_btn);
        EShopDatabaseHelper databaseHelper=new EShopDatabaseHelper(getApplicationContext());
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView em=findViewById(R.id.forget_password_email);
                String email=em.getText().toString();
                TextView jo=findViewById(R.id.forget_password_job);
                String job=jo.getText().toString();
                boolean validation=databaseHelper.changePassowordValidation(email,job);
                if(validation==true)
                {
                    Intent i=new Intent(ForgetPasswordActivity.this,ChangePasswordActivity.class);
                    i.putExtra("email",email);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Email or job",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}