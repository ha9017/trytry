package com.example.trytry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Info extends AppCompatActivity {
    EditText phoneB, cityB, numB, adressB, Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        phoneB = (EditText) (findViewById(R.id.phoneB));
        Name =   (EditText) (findViewById(R.id.Name));
        cityB =  (EditText) (findViewById(R.id.cityB));
        adressB =(EditText) (findViewById(R.id.AdressB));
        numB =   (EditText) (findViewById(R.id.NumB));


        //CheckIfNotFirstTime
        Intent t1 = getIntent();
        Intent t = new Intent(this, Main.class);
        SharedPreferences sharedpref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        boolean wantChange = t1.getBooleanExtra("wantChange", false);


        //ifNotFirstTime
        if (wantChange) {

            Name.setText(sharedpref.getString("Name", ""));
            adressB.setText(sharedpref.getString("Adress", ""));
            numB.setText(sharedpref.getString("Num", ""));
            cityB.setText(sharedpref.getString("City", ""));
            phoneB.setText(sharedpref.getString("Phone", ""));

        }

        else {

            if (!sharedpref.getString("Name", "").equals(""))
                startActivity(t);
            if (!sharedpref.getString("Adress", "").equals(""))
                startActivity(t);
            if (!sharedpref.getString("Num", "").equals(""))
                startActivity(t);
            if (!sharedpref.getString("City", "").equals(""))
                startActivity(t);
            if (!sharedpref.getString("Phone", "").equals(""))
                startActivity(t);

        }

    }


        public void saveInfo (View view){
            SharedPreferences sharedpref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpref.edit();
            editor.putString("Name", Name.getText().toString());
            editor.putString("City", cityB.getText().toString());
            editor.putString("Adress", adressB.getText().toString());
            editor.putString("Num", numB.getText().toString());
            editor.putString("Phone", phoneB.getText().toString());
            editor.apply();
            Intent t = new Intent(this, Main.class);
            startActivity(t);


        }
}