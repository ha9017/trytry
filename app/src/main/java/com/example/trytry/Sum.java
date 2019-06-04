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
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Sum extends AppCompatActivity {

//items

    ListView list;
    DatabaseReference ref,ref2;
    List<Item> itemList;
    int i;
    boolean isOwner;
    Button btn;
    Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);
        ref = FirebaseDatabase.getInstance().getReference("items");
        ref2 = FirebaseDatabase.getInstance().getReference("History");
        list = (ListView) findViewById(R.id.list);
        itemList = new ArrayList<>();
        btn= (Button)(findViewById(R.id.btn));
        registerForContextMenu(list);

        //CheckifDelivery

        Intent tt=getIntent() ;
        isOwner= tt.getBooleanExtra("IsOwner",true);
        if (isOwner){
            btn.setText("חזרה לעמוד הראשי");
        }
        else {
            btn.setText("המשך לניווט");
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            //FireBaseSnapshot, itemList;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    Item item = itemSnapshot.getValue(Item.class);
                    itemList.add(item);
                }
                ItemList adapter = new ItemList(Sum.this, itemList);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    //ContextMenuItemList
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            item = (Item) lv.getItemAtPosition(acmi.position);
            menu.add("ערוך");
            menu.add("הסר");
        }
    }

    @Override
    //OnClickLIstView
    public boolean onContextItemSelected(MenuItem item) {
        String title = item.getTitle().toString();

        if (title.equals("ערוך")) {
            Intent t = new Intent(this, Edit.class);
            t.putExtra("id", this.item.getId());
            t.putExtra("City", this.item.getCity());
            t.putExtra("Adress", this.item.getAdress());
            t.putExtra("Num", this.item.getNum());
            t.putExtra("Phone", this.item.getPhone());
            startActivity(t);

        } else
            if (title.equals("הסר")) {
            ref.child(this.item.getId()).removeValue();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    //CreateOptionMenu
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


    public void Back() {
        Intent t= new Intent(this, Main.class);
        startActivity(t);

    }


    //nextActivity
    public void check(View view) {
        if (isOwner) {
            Back();
        }
        else {
            Maps();
        }

    }

    //nevegation
    public void Maps() {
        Intent t= new Intent(this, Map.class);
        startActivity(t);

    }



}