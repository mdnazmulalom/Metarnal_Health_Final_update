package com.nazmul.metarnalhealth.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.nazmul.metarnalhealth.R;

public class DoctorDescriptionActivity extends AppCompatActivity {
    String name,designation,speciallist,id;
    TextView DescriptionName,DescriptionDesignation,DescriptionSpeciallist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_description);
        DescriptionName=findViewById(R.id.Descripton_Name);
        DescriptionDesignation=findViewById(R.id.Descricption_designation);
        DescriptionSpeciallist=findViewById(R.id.Description_speciallist);

        name=getIntent().getExtras().getString("name");
        id=getIntent().getExtras().getString("id");
        designation=getIntent().getExtras().getString("designaiton");
        speciallist=getIntent().getExtras().getString("speciallist");

        DescriptionName.setText(name);
        DescriptionDesignation.setText(designation);
        DescriptionSpeciallist.setText(speciallist);

    }
}