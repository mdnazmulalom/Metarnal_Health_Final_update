package com.nazmul.metarnalhealth.mothers.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nazmul.metarnalhealth.R;

public class FirstaidBeforeProsobActivity extends AppCompatActivity {
    CardView mayerjotno,aftercheckup,babysystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid_before_prosob);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("প্রসব পূর্ব সেবা");

        mayerjotno=findViewById(R.id.mayer_jotno);
        aftercheckup=findViewById(R.id.after_checkup);
        babysystem=findViewById(R.id.baby_system);

        mayerjotno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstaidBeforeProsobActivity.this, Firstaid_after_1.class);
                startActivity(i);
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