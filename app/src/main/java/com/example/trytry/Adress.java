package com.example.trytry;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adress extends AppCompatActivity {
    int i;
    Intent tt;
    EditText city, num, adress,phone ;
    DatabaseReference ref, ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        city = (EditText) (findViewById(R.id.city));  //items
        adress=(EditText) (findViewById(R.id.adress));
        num =  (EditText) (findViewById(R.id.num));
        phone= (EditText)(findViewById(R.id.phone));

        ref = FirebaseDatabase.getInstance().getReference("items");
        ref2= FirebaseDatabase.getInstance().getReference("History");

        Intent tt=getIntent();
        city.setText(tt.getStringExtra("City"));
        phone.setText(tt.getStringExtra("Phone"));
        num.setText(tt.getStringExtra("Num"));
        adress.setText(tt.getStringExtra("Adress"));


    }

    @Override
    //OptionMenu

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    //ClickCreditsMenu

    public void Credits(MenuItem item) {
        i = 0;
        Intent t = new Intent(this, Option.class);
        t.putExtra("Activity", i);
        startActivity(t);
    }
    //ClickAnswersMenu

    public void Answers(MenuItem item){
        i = 1;
        Intent t = new Intent(this, Option.class);
        t.putExtra("Activity", i);
        startActivity(t);
    }

    //ClickChangeMenu

    public void change(MenuItem item)  {
        Intent t= new Intent(this, Info.class);
        t.putExtra("wantChange",true);
        startActivity(t);
    }

    //ButtonPlus

    public void add(View view) {

        String cityy = city.getText().toString();
        String numm = num.getText().toString();
        String adresss = adress.getText().toString();
        String phonee= phone.getText().toString();

        if (!cityy.isEmpty() && !adresss.isEmpty() && !numm.isEmpty()&& !phonee.isEmpty()) {
            String id = ref.push().getKey();
            Item item = new Item(cityy, id, adresss, numm,phonee);
            ref.child(id).setValue(item);
            ref2.child(id).setValue(item);

            Toast.makeText(this, "הכתובת נשמרה", Toast.LENGTH_SHORT).show();
            city.setText("");
            phone.setText("");
            adress.setText("");
            num.setText("");
        }


        else {
            Toast.makeText(this, "עליך למלא את כל השדות הריקים", Toast.LENGTH_SHORT).show();
        }
    }


    //ButtonNext
    public void next(View view) {
        String cityy   = city.getText().toString();
        String numm    = num.getText().toString();
        String adresss = adress.getText().toString();
        String phonee  = phone.getText().toString();

        if (!cityy.isEmpty() && !adresss.isEmpty() && !numm.isEmpty()&&!phonee.isEmpty()) {
            String id = ref.push().getKey();
            Item item = new Item(cityy, id, adresss, numm,phonee);
            ref. child(id).setValue(item);
            ref2.child(id).setValue(item);
            Intent t2= new Intent(this, Sum.class);
            startActivity(t2);
        }

        else{
            Toast.makeText(this, "עליך למלא את כל השדות הריקים", Toast.LENGTH_SHORT).show();

        }

    }

    public void History(View view) {
        Intent t= new Intent(this, History.class);
        startActivity(t);
    }
}