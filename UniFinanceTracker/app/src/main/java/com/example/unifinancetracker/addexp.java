package com.example.unifinancetracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;

public class addexp extends AppCompatActivity {
    private int id;
    Spinner spin;
    ImageButton addexbtn;
    EditText number;
    Button backbtn;
    String [] items = new String[]{"Transport","Housing","FoodDr","ResHot","RecrCul","Uti","Clothing","Insurance","Communication"};
    String [] items2 = new String[]{"Transport","Housing","Food and Drinks","Restaurants and hotels","Recreational","Utilities","Clothing","Insurance","Communication"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexp);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolnar);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        addexbtn = findViewById(R.id.addexpnow);
        number = findViewById(R.id.amountedit);
        spin = findViewById(R.id.catspinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items2);
        spin.setAdapter(adapter);
        backbtn = findViewById(R.id.backbtnn);

        backbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        addexbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = spin.getSelectedItem().toString();
                int valLoc = Arrays.asList(items2).indexOf(val);
                String cat = items[valLoc];
                String value = number.getText().toString();
                http_reqesting a = new http_reqesting() ;
                try {
                    Boolean c = a.createExpense(String.valueOf(id),cat, Integer.parseInt(value));
                    if (c){
                        finish();
                    }else{
                        Context context = getApplicationContext();
                        CharSequence text = "Could not complete addition";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } catch (IOException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
