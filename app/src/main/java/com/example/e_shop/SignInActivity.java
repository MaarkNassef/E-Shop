package com.example.e_shop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import Database.EShopDatabaseHelper;
import Session.Session;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button login=findViewById(R.id.sign_in_login_btn);
        Button signUp=findViewById(R.id.sign_in_sign_up_btn);
        Button forgetPassword=findViewById(R.id.sign_in_forget_password);
        Switch rememberMe=findViewById(R.id.sign_in_remember_me);
        EShopDatabaseHelper databaseHelper=new EShopDatabaseHelper(getApplicationContext());
        SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox= preferences.getString("rememberMe","");
        if(checkbox.equals("true"))
        {
            Session session = Session.getInstance();
            session.setCustomer(databaseHelper.getCustomerInfo(preferences.getString("email","")));
            Intent i=new Intent(SignInActivity.this,ProductsActivity.class);
            startActivity(i);
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignInActivity.this,ForgetPasswordActivity.class);
                startActivity(i);
            }
        });

        final String[] isCheckedRm = {"false"};
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    isCheckedRm[0] = "true";
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView em=findViewById(R.id.sign_in_email);
                String email=em.getText().toString();
                TextView pass=findViewById(R.id.sign_in_password);
                String password=pass.getText().toString();
                boolean validation=databaseHelper.signInValidation(email,password);
                if (email.equals("admin") && password.equals("admin")) {
                    startActivity(new Intent(SignInActivity.this, AdminActivity.class));
                } else if(validation==true) {
                    Session session = Session.getInstance();
                    session.setCustomer(databaseHelper.getCustomerInfo(email));
                    Intent i=new Intent(SignInActivity.this,ProductsActivity.class);
                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("rememberMe",isCheckedRm[0]);
                    editor.putString("email",email);
                    editor.apply();
                    startActivity(i);
                } else {
                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("rememberMe",isCheckedRm[0]);
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Invalid Email or Password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}