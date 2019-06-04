package com.example.trytry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Map extends AppCompatActivity {
    WebView webView;
    DatabaseReference ref;
    Item item;
    String url="https://www.google.com/maps/dir/";
    String end= "%E2%80%AD%E2%80%AD/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        webView= (WebView)(findViewById(R.id.webView));
        webView.getSettings().setJavaScriptEnabled(true);
        ref = FirebaseDatabase.getInstance().getReference("items");
        SharedPreferences sharedpref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String AD=sharedpref.getString("Adress", "");
        String Cit= sharedpref.getString("City", "");
        String Number=sharedpref.getString("Num", "");

        url=url+AD+"+"+Number+"+,"+Cit+end;
}

    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Item item = itemSnapshot.getValue(Item.class);
                    String city= item.getCity();
                    String adress= item.getAdress();
                    String num= item.getNum();
                    url=url+adress+ ",+"+num+ "+"+city+end;

                }
                webView.loadUrl(url);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updating(View view) {
        startActivity(new Intent(this,Update.class));
    }
}
