package com.nazmul.metarnalhealth.mothers.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nazmul.metarnalhealth.R;

public class FirstaidBeforeProsobActivity extends AppCompatActivity {
    CardView beforeprosob1,beforeprosob2,beforeprosob3,beforeprosob4,beforeprosob5,beforeprosob6,beforeprosob7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid_before_prosob);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("প্রসব পূর্ব সেবা");

        beforeprosob1=findViewById(R.id.before_prosob_1);
        beforeprosob2=findViewById(R.id.before_prosob_2);
        beforeprosob3=findViewById(R.id.before_prosob_3);
        beforeprosob4=findViewById(R.id.before_prosob_4);
        beforeprosob5=findViewById(R.id.before_prosob_5);
        beforeprosob6=findViewById(R.id.before_prosob_6);
        beforeprosob7=findViewById(R.id.before_prosob_7);

        beforeprosob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstaidBeforeProsobActivity.this, FirstAid_Before_1.class);
                startActivity(intent);
            }
        });

        beforeprosob2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstaidBeforeProsobActivity.this, FirstAid_Before_2.class);
                startActivity(intent);
            }
        });
        beforeprosob3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstaidBeforeProsobActivity.this, FirstAid_Before_3.class);
                startActivity(intent);
            }
        });

        beforeprosob4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstaidBeforeProsobActivity.this, FirstAid_Before_4.class);
                startActivity(intent);
            }
        });
        beforeprosob5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstaidBeforeProsobActivity.this, FirstAid_Before_5.class);
                startActivity(intent);
            }
        });
        beforeprosob6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstaidBeforeProsobActivity.this, FirstAid_Before_6.class);
                startActivity(intent);
            }
        });
        beforeprosob7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstaidBeforeProsobActivity.this, FirstAid_Before_7.class);
                startActivity(intent);
            }
        });
    }

    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}