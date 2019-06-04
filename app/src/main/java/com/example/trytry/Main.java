package com.example.trytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends AppCompatActivity {

    boolean IsOwner;
    int i;
    Intent t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void delivery(View view) {
        IsOwner = false;
        t = new Intent(this, Sum.class);
        t.putExtra("IsOwner", IsOwner);
        startActivity(t);
    }

    public void owner(View view) {
        t = new Intent(this, Adress.class);
        t.putExtra("IsOwner", IsOwner);
        startActivity(t);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    public void Credits(MenuItem item) {
        i = 0;
        Intent t = new Intent(this, Option.class);
        t.putExtra("Activity", i);
        startActivity(t);
    }

    public void change(MenuItem item) {
        Intent t = new Intent(this, Info.class);
        t.putExtra("wantChange", true);
        startActivity(t);
    }

    public void Answers(MenuItem item) {
        i = 1;
        Intent t = new Intent(this, Option.class);
        t.putExtra("Activity", i);
        startActivity(t);
    }
}