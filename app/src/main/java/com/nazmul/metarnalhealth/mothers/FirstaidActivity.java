package com.nazmul.metarnalhealth.mothers;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.mothers.firstaid.FirstAidPoripurokKhabar;
import com.nazmul.metarnalhealth.mothers.firstaid.FirstaidAfterProsobActivity;
import com.nazmul.metarnalhealth.mothers.firstaid.FirstaidBeforeProsobActivity;
import com.nazmul.metarnalhealth.mothers.firstaid.FirstaidFistulaActivity;
import com.nazmul.metarnalhealth.mothers.firstaid.FirstaidShishuRogActivity;
import com.nazmul.metarnalhealth.mothers.firstaid.Firstaid_Dubjaowa_Protirod;

public class FirstaidActivity extends AppCompatActivity {

    CardView before_prosob,after_prosob,prosobfistula,shishuporipurokkhabar,shishurog,shishudobejaowaprotirod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);

        before_prosob=findViewById(R.id.beforeprosob);
        after_prosob=findViewById(R.id.afterprosob);
        prosobfistula=findViewById(R.id.prosob_fistula);
        shishuporipurokkhabar=findViewById(R.id.shishu_poripurok_khabar);
        shishurog=findViewById(R.id.shishu_rog);
        shishudobejaowaprotirod=findViewById(R.id.shishu_dobejaowa_protirod);

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
        prosobfistula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstaidActivity.this, FirstaidFistulaActivity.class);
                startActivity(intent);
            }
        });

        shishuporipurokkhabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstaidActivity.this, FirstAidPoripurokKhabar.class);
                startActivity(intent);
            }
        });

        shishurog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstaidActivity.this, FirstaidShishuRogActivity.class);
                startActivity(intent);
            }
        });
        shishudobejaowaprotirod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstaidActivity.this, Firstaid_Dubjaowa_Protirod.class);
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