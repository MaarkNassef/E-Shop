package com.example.e_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import Database.EShopDatabaseHelper;
import Session.Session;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EShopDatabaseHelper db = new EShopDatabaseHelper(getApplicationContext());

        //Form Texts
        EditText username = findViewById(R.id.sign_up_username);
        EditText email = findViewById(R.id.sign_up_email);
        EditText job = findViewById(R.id.sign_up_job);
        Spinner gender = findViewById(R.id.sign_up_gender);
        EditText birthdate = findViewById(R.id.sign_up_birthdate);
        EditText password = findViewById(R.id.sign_up_password);

        //Get Gender Value
        final String[] Gender = {"Male"};
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Gender[0] = ((TextView)view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Calender
        ImageButton calenderBtn = findViewById(R.id.sign_up_calender_btn);
        calenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;

                                String date = month + "/" + day + "/" + year;
                                birthdate.setText(date);
                            }
                        },
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        //Remember Me
        Switch rememberMe = findViewById(R.id.sign_up_remember_me);
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
        //Buttons
        Button SignUpButton = findViewById(R.id.sign_up_sign_up_btn);
        Button toSignIn = findViewById(R.id.sign_up_sign_in_btn);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String Job = job.getText().toString();
                String Birthdate = birthdate.getText().toString();
                String Password = password.getText().toString();

                if (db.isExist(Email)) {
                    Toast.makeText(getApplicationContext(), "This customer already exists.", Toast.LENGTH_SHORT).show();
                } else if (Username.equals("") || Email.equals("") || Job.equals("") || Birthdate.equals("") || Password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Missing fields.", Toast.LENGTH_SHORT).show();
                } else {
                    db.addNewCustomer(Username,Email,Password,Gender[0],Birthdate,Job);
                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("rememberMe",isCheckedRm[0]);
                    editor.putString("email",Email);
                    editor.apply();
                    Session session = Session.getInstance();
                    session.setCustomer(db.getCustomerInfo(preferences.getString("email","")));
                    db.insertNewOrder(String.valueOf(session.getCustomer().getId()));
                    startActivity(new Intent(SignUpActivity.this, ProductsActivity.class));
                }
            }
        });

        toSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }

}