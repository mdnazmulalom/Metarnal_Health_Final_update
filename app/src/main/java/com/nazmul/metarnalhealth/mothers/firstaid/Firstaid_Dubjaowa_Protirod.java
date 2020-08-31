package com.nazmul.metarnalhealth.mothers.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nazmul.metarnalhealth.R;

public class Firstaid_Dubjaowa_Protirod extends AppCompatActivity {
    CardView dubejaowaprotirod1,dubejaowaprotirod2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid__dubjaowa__protirod);
        dubejaowaprotirod1=findViewById(R.id.dobejaowa_protirod_1);
        dubejaowaprotirod2=findViewById(R.id.dobejaowa_protirod_2);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("শিশুর ডুবে যাওয়া প্রতিরোধ");

        dubejaowaprotirod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Firstaid_Dubjaowa_Protirod.this, Firstaid_Dubejaowa_Protirod_1.class);
                startActivity(intent);
            }
        });

        dubejaowaprotirod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Firstaid_Dubjaowa_Protirod.this, Firstaid_Dubejaowa_Protirod_2.class);
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