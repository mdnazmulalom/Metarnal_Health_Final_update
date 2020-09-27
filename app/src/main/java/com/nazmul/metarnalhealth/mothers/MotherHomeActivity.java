package com.nazmul.metarnalhealth.mothers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.DoctorsInfoViewActivity;


import es.dmoral.toasty.Toasty;

public class MotherHomeActivity extends AppCompatActivity {

    CardView card_mother,cardlogout,first_aid,card_doctorlist,card_doctor_appoinment,card_hospital_info;

    //for double back press to exit
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_home);

        sp = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        card_mother = findViewById(R.id.card_mother);
        cardlogout = findViewById(R.id.card_logout);
        first_aid=findViewById(R.id.FirstAid);
        card_doctorlist = findViewById(R.id.card_doctor_list);
        card_doctor_appoinment=findViewById(R.id.card_doctor_appoinment);
        card_hospital_info=findViewById(R.id.hospitalinfo);



        getSupportActionBar().setHomeButtonEnabled(false); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//for back button
        getSupportActionBar().setTitle("Mother Panel");


        card_mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MotherHomeActivity.this,MotherProfile.class);
                startActivity(i);
            }
        });

        first_aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MotherHomeActivity.this,FirstaidActivity.class);
                startActivity(intent);
            }
        });

        card_doctorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotherHomeActivity.this,DoctorsInfoViewActivity.class);
                startActivity(intent);
            }
        });
        card_doctor_appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotherHomeActivity.this,MotherAppoinmentHistory.class);
                startActivity(intent);
            }
        });
        card_hospital_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MotherHomeActivity.this,HospitalInfoActivity.class);
                startActivity(intent);
            }
        });


        cardlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                finishAffinity();
            }
        });





    }


    //double backpress to exit
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent=new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            finish();

        } else {
            Toasty.warning(MotherHomeActivity.this,"Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

}
