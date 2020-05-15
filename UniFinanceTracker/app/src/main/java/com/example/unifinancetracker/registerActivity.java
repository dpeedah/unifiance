package com.example.unifinancetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class registerActivity extends AppCompatActivity {
    EditText emailadd, passw, username;
    Button regb, logb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailadd = findViewById(R.id.emailReg);
        passw = findViewById(R.id.passReg);
        username = findViewById(R.id.userReg);

        regb = findViewById(R.id.regBut);
        logb = findViewById(R.id.backlogBut);

        regb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    http_reqesting z = new http_reqesting();
                    String a = emailadd.getText().toString();
                    String b = passw.getText().toString();
                    String c = username.getText().toString();
                    try {
                        if (z.sign_up(a,b,c)){
                            Intent i = new Intent(registerActivity.this, MainActivity.class);
                            i.putExtra("signed_up", true);
                            startActivity(i);

                        }else{//if sign up request fails
                            Context context = getApplicationContext();
                            CharSequence text = "Provide valid information!";
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    } catch (IOException e) {
                        Context context = getApplicationContext();
                        CharSequence text = "Provide valid information!";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                }
            }
        });

        logb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registerActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    boolean valid(EditText a){
        CharSequence str = a.getText().toString();
        if (TextUtils.isEmpty(str) || str.length() < 6){
            return false;
        }
        return true;
    }

    boolean checkEmail(EditText a){ //ensure it is an email address
        CharSequence str = a.getText().toString();
        return (!TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches());
    }

    boolean checkData(){ //checks for valid inputs
        boolean valuesEntered = true;
        if (!checkEmail(emailadd)){
            Toast t = Toast.makeText(this,"Enter a valid email",Toast.LENGTH_SHORT);
            t.show();
            valuesEntered = false;
        }
        if (!valid(passw)){
            Toast t = Toast.makeText(this,"Must be 6 characters long",Toast.LENGTH_SHORT);
            t.show();
            valuesEntered = false;
        }
        if (!valid(username)){
            Toast t = Toast.makeText(this,"Must be 6 characters long",Toast.LENGTH_SHORT);
            t.show();
            valuesEntered = false;
        }
        return valuesEntered;

    }
}
