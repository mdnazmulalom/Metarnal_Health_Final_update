package com.nazmul.metarnalhealth.doctors;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.R;

import java.util.HashMap;
import java.util.Map;

public class Confirm_Appoinment_Activity extends AppCompatActivity {
    TextView txt_id, txt_status,txt_mothercell,txt_appointmentdate,txt_description;
    Button btnmothercall,btnconfirmappointment,btncencelappointment;
    String getUserCell;

    String UserCell;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__appoinment_);

        sharedPreferences=getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell=sharedPreferences.getString(Constant.CELL_SHARED_PREF,"Not Available");
        UserCell = cell;

        getUserCell = getIntent().getExtras().getString("usercell");



        txt_id=findViewById(R.id.etxtid);
        txt_status=findViewById(R.id.etxtstatus);
        txt_mothercell=findViewById(R.id.etxtmothercell);
        txt_appointmentdate=findViewById(R.id.etxtdate);
        txt_description=findViewById(R.id.etxtdescription);


        btnmothercall=findViewById(R.id.btn_mothercall);
        btnconfirmappointment=findViewById(R.id.btn_confirm);
        btncencelappointment=findViewById(R.id.btn_cancel);


        String appointmentid=getIntent().getExtras().getString("id");
        String mothernumber=getIntent().getExtras().getString("mothercell");
        String appointmentdate=getIntent().getExtras().getString("appointmentdate");
        String description=getIntent().getExtras().getString("description");

        txt_id.setText(appointmentid);
        txt_mothercell.setText(mothernumber);
        txt_appointmentdate.setText(appointmentdate);
        txt_description.setText(description);

//        btnmothercall.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view) {
//                Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                String p = "tel:" + getUserCell;
//                callIntent.setData(Uri.parse(p));
//                startActivity(callIntent);
//            }
//        });



    }

}


