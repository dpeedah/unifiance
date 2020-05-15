package com.example.unifinancetracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText passw,emailadd;
    Button login,register;

    SharedPreferences sharedpref;
    public static final String myPref = "myPrefs";
    public static final String idKey = "idKey";
    public static final String loggedInKey = "logKey";
    public static final String nameKey = "userN";
    public static String savedUserName = "";
    public static Boolean loggedIn = false;
    public static Integer id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolnar);

        passw = findViewById(R.id.password);
        emailadd = findViewById(R.id.email);
        login = findViewById(R.id.logbut);
        register = findViewById(R.id.register);

        sharedpref = getSharedPreferences(myPref, Context.MODE_PRIVATE);

        loggedIn = sharedpref.getBoolean(loggedInKey,false);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean signed_up = extras.getBoolean("signed_up");
            Context context = getApplicationContext();
            CharSequence text = "Registration sucessful";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //The key argument here must match that used in the other activity
        }

        if (loggedIn){
            savedUserName = sharedpref.getString(nameKey,"");
            id = sharedpref.getInt(idKey,0);

            Intent i = new Intent(MainActivity.this, landing_activity.class);
            i.putExtra("id", id);
            i.putExtra("username",savedUserName);
            startActivity(i);
            // call main activity including search, view popular, view favourites
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    http_reqesting a = new http_reqesting() ;
                    String emails = emailadd.getText().toString();
                    String password = passw.getText().toString();
                    String [] details = new String[2];
                    try {
                        details = a.logIn(emails,password);
                        if (details.length == 2){
                            SharedPreferences.Editor editor = sharedpref.edit();
                            editor.putString(nameKey,details[1]);
                            int newid = Integer.parseInt(details[0]);
                            editor.putInt(idKey,newid);
                            editor.putBoolean(loggedInKey, true);
                            editor.apply();
                            savedUserName = details[1];
                            id = newid;
                            Intent i = new Intent(MainActivity.this, landing_activity.class);
                            i.putExtra("id", id);
                            i.putExtra("username",savedUserName);
                            startActivity(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        //
                    }

                    //take to display page
                }
            }
        });

        // opens register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, registerActivity.class);
                startActivity(i);
            }
        });


    }

    boolean isEmpty(EditText a){
        CharSequence str = a.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkData(){
        boolean valuesEntered = true;
        if (isEmpty(emailadd)){
            Toast t = Toast.makeText(this,"Enter email",Toast.LENGTH_SHORT);
            t.show();
            valuesEntered = false;
        }
        if (isEmpty(passw)){
            Toast t = Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT);
            t.show();
            valuesEntered = false;
        }
        return valuesEntered;

    }
}
