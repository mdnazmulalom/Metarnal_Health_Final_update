package com.nazmul.metarnalhealth.mothers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.nazmul.metarnalhealth.R;

public class EditProfileActivity extends AppCompatActivity {

    EditText etxt_Fullame,etxt_Cell,etxt_Location,etxt_Gender,etxt_Pass;

    TextView txt_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        txt_Name = findViewById(R.id.txt_name);


        etxt_Fullame = findViewById(R.id.etxtfullname);
        etxt_Cell = findViewById(R.id.etxtcell);
        etxt_Location= findViewById(R.id.location);
        etxt_Gender = findViewById(R.id.gender);
        etxt_Pass= findViewById(R.id.password);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Profile");

        String getName=getIntent().getExtras().getString("name");
        String getCell=getIntent().getExtras().getString("cell");
        String getLocation=getIntent().getExtras().getString("location");
        String getGender=getIntent().getExtras().getString("gender");


           txt_Name.setText(getName);
           etxt_Fullame.setText(getName);
           etxt_Cell.setText(getCell);
           etxt_Location.setText(getLocation);
           etxt_Gender.setText(getGender);




    }
}
