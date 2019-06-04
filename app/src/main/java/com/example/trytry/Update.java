package com.example.trytry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Update extends AppCompatActivity {
    ListView list;
    DatabaseReference ref;
    List<Item> itemList;
    int i;
    Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ref = FirebaseDatabase.getInstance().getReference("items");
        list = (ListView) findViewById(R.id.list);
        itemList = new ArrayList<>();
        registerForContextMenu(list);


    }


    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Item item = itemSnapshot.getValue(Item.class);
                    itemList.add(item);
                }

                ItemList adapter = new ItemList(Update.this, itemList);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            item = (Item) lv.getItemAtPosition(acmi.position);
            menu.add("המשלוח נמסר");
            menu.add("המשלוח לא נמסר");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("המשלוח לא נמסר")) {
            //sms

        } else if (title.equals("המשלוח נמסר")) {
            String phoneNum=this.item.getPhone();
            ref.child(this.item.getId()).removeValue();
            SharedPreferences sharedpref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            String phone=sharedpref.getString("Phone","");
            String B= sharedpref.getString("Name", "");
            String msg= "נמסר לידך משלוח מאת "+B+" לבירורים ויצירת קשר "+phone;
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum, null,msg,null,null);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    public void change(MenuItem item) {
        Intent t= new Intent(this, Info.class);
        t.putExtra("wantChange",true);
        startActivity(t);
    }
    public void Credits(MenuItem item) {
        i = 0;
        Intent t = new Intent(this, Option.class);
        t.putExtra("Activity", i);
        startActivity(t);
    }

    public void Answers(MenuItem item) {
        i = 1;
        Intent t = new Intent(this, Option.class);
        t.putExtra("Activity", i);
        startActivity(t);
    }

    public void end(View view) {
        Intent t=new Intent(this,Main.class);
        startActivity(t);

    }
}