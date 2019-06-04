package com.example.trytry;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {//There is something wrong on this Activity.

    ListView list2;
    Item item;
    DatabaseReference ref2;
    List<Item> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ref2 = FirebaseDatabase.getInstance().getReference("History");
        list2 = (ListView) findViewById(R.id.list2);
        itemList = new ArrayList<>();
        registerForContextMenu(list2);
        Toast.makeText(this, "לבחירה מההיסטוריה לחץ לחיצה ארוכה על הכתובת הרצויה", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onStart() {
        super.onStart();
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Item item = itemSnapshot.getValue(Item.class);
                    itemList.add(item);
                }

                ItemList adapter = new ItemList(History.this, itemList);
                list2.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    //ContextMenuItemList
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list2) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            item = (Item) lv.getItemAtPosition(acmi.position);
            menu.add("בחר");
        }
    }

    @Override
    //OnClickLIstView
    public boolean onContextItemSelected(MenuItem item) {

        String title = item.getTitle().toString();
        if (title.equals("בחר")) {
            Intent t = new Intent(this, Adress.class);
            t.putExtra("id", this.item.getId());
            t.putExtra("City", this.item.getCity());
            t.putExtra("Adress", this.item.getAdress());
            t.putExtra("Num", this.item.getNum());
            t.putExtra("Phone", this.item.getPhone());
            startActivity(t);

        }
        return super.onContextItemSelected(item);
    }
}
