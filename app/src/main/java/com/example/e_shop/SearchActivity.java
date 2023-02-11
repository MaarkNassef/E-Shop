package com.example.e_shop;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import Database.EShopDatabaseHelper;
public class SearchActivity extends AppCompatActivity {

    int voice_code = 1;
    EditText search;
    ListView list;
    FloatingActionButton voice_button;
    EShopDatabaseHelper db;
    ImageButton Back;
    FloatingActionButton search_scan_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        voice_button = findViewById(R.id.search_mic_btn);
        search = findViewById(R.id.search_edit_text);
        list = findViewById(R.id.search_list_view);
        Back = findViewById(R.id.search_back_btn);
        db = new EShopDatabaseHelper(getApplicationContext());
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayAdapter<String> adapter = db.getProducts(charSequence.toString());
                if (charSequence.toString().equals("")) {
                    adapter.clear();
                } else {
                    if (adapter.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "No Products found ", Toast.LENGTH_SHORT).show();
                    }
                }
                list.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String choice = ((TextView)view).getText().toString();
                Intent intent = new Intent(SearchActivity.this, ProductViewActivity.class);
                intent.putExtra("ProdName", choice);
                startActivity(intent);
            }
        });
        voice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, voice_code);
            }
        });

        ImageButton cart = findViewById(R.id.search_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, CartActivity.class));
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, ProductsActivity.class));
            }
        });

        search_scan_button = findViewById(R.id.search_scan_btn);
        search_scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scancode();
            }
        });

    }

    private void scancode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barlauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barlauncher = registerForActivityResult(new ScanContract(), result ->
    {
        if(result.getContents()!=null)
        {
            AlertDialog.Builder Bulider= new AlertDialog.Builder(SearchActivity.this);
            Bulider.setMessage(result.getContents());
            Bulider.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
            search.setText(result.getContents());
        }

    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == voice_code && resultCode == RESULT_OK) {
            ArrayList<String> arr_list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String voice = arr_list.get(0);
            search.setText(voice);
            Toast.makeText(this, voice, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No matched Products ", Toast.LENGTH_SHORT).show();
        }


    }

}
