package com.nazmul.metarnalhealth.doctors;

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
import com.nazmul.metarnalhealth.mothers.HospitalInfoActivity;

import es.dmoral.toasty.Toasty;

public class DoctorHomeActivity extends AppCompatActivity {
    CardView CardDoctor,CardLogout,all_appoinment,cardhospitalinfo;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        sp = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

        CardDoctor=findViewById(R.id.card_doctor_profile);
        CardLogout=findViewById(R.id.card_doctor_logout);
        all_appoinment = findViewById(R.id.all_appoinment);
        cardhospitalinfo=findViewById(R.id.card_hospital_info);

        getSupportActionBar().setHomeButtonEnabled(false); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//for back button
        getSupportActionBar().setTitle("Doctor Home");

        CardDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorHomeActivity.this,DoctorProfileActivity.class);
                startActivity(intent);
            }
        });
        CardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                finishAffinity();
            }
        });
        all_appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this,DoctorAppoinmentHistory.class);
                startActivity(intent);
            }
        });
//        cardhospitalinfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//            }
//        });

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }

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
            Toasty.warning(DoctorHomeActivity.this,"Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

}
