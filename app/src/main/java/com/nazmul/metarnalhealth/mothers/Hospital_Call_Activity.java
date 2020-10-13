package com.nazmul.metarnalhealth.mothers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nazmul.metarnalhealth.R;

public class Hospital_Call_Activity extends AppCompatActivity {
    TextView txt_hopitalname,txt_hospitalcategory,txt_hospitaladdress,txt_hospitalnumber,txt_hospitalwebsite;
    Button btn_hospitalcall;
    String hospitalnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__call_);

        txt_hopitalname=findViewById(R.id.txthospitalname);
        txt_hospitalcategory=findViewById(R.id.txthoptalcategory);
        txt_hospitaladdress=findViewById(R.id.txtaddress);
        txt_hospitalnumber=findViewById(R.id.txthospitalnumber);
        txt_hospitalwebsite=findViewById(R.id.txtwebsite);
        btn_hospitalcall=findViewById(R.id.btn_hospitalcall);

        String hospitalname=getIntent().getExtras().getString("hospital_name");
        String hospitalcategory=getIntent().getExtras().getString("category");
        String hopitaladdress=getIntent().getExtras().getString("address");
        hospitalnumber=getIntent().getExtras().getString("hospital_number");
        String hospitalwebsite=getIntent().getExtras().getString("website");

        txt_hopitalname.setText(hospitalname);
        txt_hospitalcategory.setText(hospitalcategory);
        txt_hospitaladdress.setText(hopitaladdress);
        txt_hospitalnumber.setText(hospitalnumber);
        txt_hospitalwebsite.setText(hospitalwebsite);


        btn_hospitalcall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + hospitalnumber;
                callIntent.setData(Uri.parse(p));

                startActivity(callIntent);
            }
        });

    }
}