package com.nazmul.metarnalhealth.doctors;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nazmul.metarnalhealth.R;

public class DoctorHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        getSupportActionBar().setHomeButtonEnabled(false); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//for back button
        getSupportActionBar().setTitle("Doctor Home");
    }

}
