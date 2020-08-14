package com.nazmul.metarnalhealth.doctors;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.R;

public class DoctorProfile extends AppCompatActivity {
    TextView txtFullname,txtCell,txtLocation,txtGender,txtPassword;
    TextView txtUpdateprofile;
    String UserCell;
    private ProgressDialog loading;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");
        UserCell = cell;

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Doctor Profile");

        txtFullname=findViewById(R.id.etxtfullname);
        txtCell=findViewById(R.id.etxtcell);
        txtLocation=findViewById(R.id.location);
        txtGender=findViewById(R.id.gender);
        txtPassword=findViewById(R.id.password);
        txtUpdateprofile=findViewById(R.id.update_profile);



    }
}