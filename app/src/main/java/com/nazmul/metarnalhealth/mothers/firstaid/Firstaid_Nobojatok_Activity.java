package com.nazmul.metarnalhealth.mothers.firstaid;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nazmul.metarnalhealth.R;

public class Firstaid_Nobojatok_Activity extends AppCompatActivity {

    CardView nobojatokottabossokio,nobojatokmodano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid__nobojatok_);
    nobojatokottabossokio=findViewById(R.id.nobojatok_ottabosikio);
    nobojatokmodano=findViewById(R.id.nobojatok_modano);

        nobojatokottabossokio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Firstaid_Nobojatok_Activity.this, Nobojatok_Activity_1.class);
                startActivity(intent);
            }
        });

        nobojatokmodano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Firstaid_Nobojatok_Activity.this, Nobojatok_Activity_2.class);
                startActivity(intent);
            }
        });


    }
}