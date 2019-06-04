package com.example.trytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Option extends AppCompatActivity {

TextView tv, tv2;
int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        tv= (TextView)(findViewById(R.id.tv));
        tv2= (TextView)(findViewById(R.id.tv));
        Intent t=getIntent();
        i=t.getIntExtra("rule",0);

        if (i==0){
            tv2.setText("אני תלמידה בכיתה יב במגמת סייבר בתיכון בבית הספר ניומן" +
                    "כל הקרדיטים שמורים להדר אלימלך ולמורה המנחה שלי אלברט לוי");

            tv.setText("מי אנחנו?");
        }


        else {
            tv2.setText("למי מיועדת האפליקציה? לבעלי עסקים קטנים המתמחים בעסקי המזון.");
            tv.setText("שאלות נפצות...");
        }

    }


}
