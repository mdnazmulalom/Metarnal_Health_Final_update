package com.nazmul.metarnalhealth.mothers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nazmul.metarnalhealth.R;

public class Mother_Appointment_Details_ctivity extends AppCompatActivity {

    TextView txt_id, txt_status,txt_mothercell,txt_appointmentdate,txt_description;
    Button btn_zoom_call,btnconfirmappointment,btncencelappointment;
//    String getUserCell,getstatus,appointmentid,status,mothernumber;
    TextView txt_chamber_type,txt_bkash_number,txt_bkash_amount,txt_bkash_trans_id;
    ProgressDialog loading;
    EditText etxt_zoom_or_chamber_address;

    String UserCell;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother__appointment__details_ctivity);


//        txt_id=findViewById(R.id.etxtid);

        txt_status = findViewById(R.id.etxtstatus);
        txt_appointmentdate = findViewById(R.id.etxt_mother_date);
        txt_description = findViewById(R.id.etxt_mother_description);
        txt_id = findViewById(R.id.etxt_id);
        txt_chamber_type = findViewById(R.id.txt_chamber_type);
        txt_bkash_number = findViewById(R.id.txt_bkash_number);
        txt_bkash_amount = findViewById(R.id.txt_bkash_amount);
        txt_bkash_trans_id = findViewById(R.id.txt_bkash_trans_id);
        etxt_zoom_or_chamber_address = findViewById(R.id.zoom_or_chamber_address);

        btn_zoom_call = findViewById(R.id.btn_zoom_call);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Appointment Details");


        String appointmentid = getIntent().getExtras().getString("id");
        String status = getIntent().getExtras().getString("status");
        String appointmentdate = getIntent().getExtras().getString("Date");
        String description = getIntent().getExtras().getString("descripton");
        String chamber_type = getIntent().getExtras().getString("chamber_type");
        String zoom_or_chamber_address = getIntent().getExtras().getString("zoom_or_chamber_address");
        String bkash_number = getIntent().getExtras().getString("bkash_number");
        String bkash_trans_id = getIntent().getExtras().getString("bkash_trans_id");
        String bkash_amount = getIntent().getExtras().getString("bkash_amount");


        txt_id.setText(appointmentid);
        txt_status.setText(status);
        txt_appointmentdate.setText(appointmentdate);
        txt_description.setText(description);
        txt_chamber_type.setText(chamber_type);
        txt_bkash_number.setText(bkash_number);
        txt_bkash_trans_id.setText(bkash_trans_id);
        txt_bkash_amount.setText(bkash_amount);
        etxt_zoom_or_chamber_address.setText(zoom_or_chamber_address);

        if (status.equals("0")) {
            txt_status.setText("Pending");
        } else if (status.equals("1")) {
            txt_status.setText("Confirmed");
        } else if (status.equals("2")) {
            txt_status.setText("Cancel");
        }


//        Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);

        btn_zoom_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = getIntent().getExtras().getString("zoom_or_chamber_address");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(uri));
                startActivity(i);
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
