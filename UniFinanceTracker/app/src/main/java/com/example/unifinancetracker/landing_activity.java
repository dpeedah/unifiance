package com.example.unifinancetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class landing_activity extends AppCompatActivity {
    private int id;
    private String name;
    TextView namet;
    EditText search;
    Button gosearch;
    ImageButton expenses,goalbtn, addexp, weekexp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_activity);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolnar);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        name = extras.getString("username");
        namet = findViewById(R.id.nameText);
        expenses = findViewById(R.id.viewexpbtn);
        goalbtn = findViewById(R.id.editgoalbtn);
        addexp = findViewById(R.id.addexpbtn);
        weekexp = findViewById(R.id.vieweekbtn);
        namet.setText(name);

        addexp.setOnClickListener(new View.OnClickListener() { //done
            @Override
            public void onClick(View v) {
                Intent i = new Intent(landing_activity.this, addexp.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });

        weekexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchvalue = search.getText().toString();
                Intent i = new Intent(landing_activity.this, addexp.class);
                http_reqesting a = new http_reqesting() ;
                try {
                    String jsonstr = a.get_weekly_expenses(String.valueOf(id));
                    i.putExtra("weeklyexp", jsonstr);
                    startActivity(i);
                } catch (IOException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchvalue = search.getText().toString();
                Intent i = new Intent(landing_activity.this, searchActivity.class);
                http_reqesting a = new http_reqesting() ;
                try {
                    String jsonstr = a.get_expenses(id);
                    i.putExtra("expenses", jsonstr);
                    startActivity(i);
                } catch (IOException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        goalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchvalue = search.getText().toString();
                Intent i = new Intent(landing_activity.this, searchActivity.class);
                http_reqesting a = new http_reqesting() ;
                try {
                    String jsonstr = a.get_goal(String.valueOf(id));
                    i.putExtra("goal", jsonstr);
                    startActivity(i);
                } catch (IOException e) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });


    }
}
