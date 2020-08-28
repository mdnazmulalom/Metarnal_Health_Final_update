package com.nazmul.metarnalhealth.mothers;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.mothers.firstaid.FirstaidAfterProsobActivity;
import com.nazmul.metarnalhealth.mothers.firstaid.FirstaidBeforeProsobActivity;

public class FirstaidActivity extends AppCompatActivity {

    CardView before_prosob,after_prosob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);

        before_prosob=findViewById(R.id.beforeprosob);
        after_prosob=findViewById(R.id.afterprosob);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("নীড় পাতা ");

        before_prosob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstaidActivity.this, FirstaidBeforeProsobActivity.class);
                startActivity(intent);
            }
        });

        after_prosob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstaidActivity.this, FirstaidAfterProsobActivity.class);
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