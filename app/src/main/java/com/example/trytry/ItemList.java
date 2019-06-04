package com.example.trytry;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemList extends ArrayAdapter<Item> {
    private Activity context;
    private List<Item> itemList;

    public ItemList (Activity context, List<Item> itemList){
        super (context, R.layout.list_layout, itemList);
        this.context= context;
        this.itemList= itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.list_layout, null, true);
        TextView cityText= listViewItem.findViewById(R.id.city2);
        TextView adressText= listViewItem.findViewById(R.id.adress2);
        TextView numText= listViewItem.findViewById(R.id.num2);
        TextView phoneText= listViewItem.findViewById(R.id.phone2);

        Item item= itemList.get(position);
        cityText.setText(item.getCity());
        adressText.setText(item.getAdress());
        numText.setText(item.getNum());
        phoneText.setText("פלאפון לבירורים "+item.getPhone());

        return listViewItem;
    }

}
