package com.example.trytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit extends AppCompatActivity {

    EditText AdressText, CityText, NumText, PhoneText;
    String id;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        AdressText = (EditText) findViewById(R.id.adressText);
        CityText = (EditText) findViewById(R.id.CityText);
        NumText= (EditText)(findViewById(R.id.NumText)) ;
        PhoneText= (EditText)(findViewById(R.id.PhoneText));
        Intent gt = getIntent();
        AdressText.setText(""+gt.getStringExtra("Adress"));
        CityText.setText(""+gt.getStringExtra("City"));
        PhoneText.setText(""+gt.getStringExtra("Phone"));
        NumText.setText(""+gt.getStringExtra("Num"));
        id = gt.getStringExtra("id");
        ref = FirebaseDatabase.getInstance().getReference("items");

    }


    public void cancel(View view) {
        finish();
    }


    public void save(View view) {
        Item item = new Item(CityText.getText().toString(),id, AdressText.getText().toString(), NumText.getText().toString(), PhoneText.getText().toString());
        ref.child(id).setValue(item);
        finish();
    }
}
