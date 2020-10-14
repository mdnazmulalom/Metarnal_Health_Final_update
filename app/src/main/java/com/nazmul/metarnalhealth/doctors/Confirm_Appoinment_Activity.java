package com.nazmul.metarnalhealth.doctors;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

import es.dmoral.toasty.Toasty;

public class Confirm_Appoinment_Activity extends AppCompatActivity {
    TextView txt_id, txt_status,txt_mothercell,txt_appointmentdate,txt_description;
    Button btnmothercall,btnconfirmappointment,btncencelappointment;
    String getUserCell,getstatus,appointmentid,status,mothernumber;
    TextView txt_chamber_type,txt_bkash_number,txt_bkash_amount,txt_bkash_trans_id;
    ProgressDialog loading;
    EditText etxt_zoom_or_chamber_address;

    String UserCell;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__appoinment_);

        txt_id=findViewById(R.id.etxtid);
        txt_status=findViewById(R.id.etxtstatus);
        txt_mothercell=findViewById(R.id.etxtmothercell);
        txt_appointmentdate=findViewById(R.id.etxtdate);
        txt_description=findViewById(R.id.etxtdescription);

        txt_chamber_type = findViewById(R.id.txt_chamber_type);
        txt_bkash_number = findViewById(R.id.txt_bkash_number);
        txt_bkash_amount = findViewById(R.id.txt_bkash_amount);
        txt_bkash_trans_id = findViewById(R.id.txt_bkash_trans_id);
        etxt_zoom_or_chamber_address = findViewById(R.id.zoom_or_chamber_address);



        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Doctor Appoinment Details");

        btnmothercall=findViewById(R.id.btn_mothercall);
        btnconfirmappointment=findViewById(R.id.btn_confirm);
        btncencelappointment=findViewById(R.id.btn_cancel);


        appointmentid=getIntent().getExtras().getString("id");
        status=getIntent().getExtras().getString("status");
        mothernumber=getIntent().getExtras().getString("mothercell");
        String appointmentdate=getIntent().getExtras().getString("appointmentdate");
        String description=getIntent().getExtras().getString("description");
        String chamber_type = getIntent().getExtras().getString("chamber_type");
        final String zoom_or_chamber_address =getIntent().getExtras().getString("zoom_or_chamber_address");
        String bkash_number = getIntent().getExtras().getString("bkash_number");
        String bkash_trans_id = getIntent().getExtras().getString("bkash_trans_id");
        String bkash_amount = getIntent().getExtras().getString("bkash_amount");


//        intent.putExtra("chamber_type",doctor_appoinment_lists.get(getAdapterPosition()).getChamber_type());
//        intent.putExtra("zoom_or_chamber_address",doctor_appoinment_lists.get(getAdapterPosition()).getzoom_or_chamber_address());
//        intent.putExtra("bkash_number",doctor_appoinment_lists.get(getAdapterPosition()).getbkash_number());
//        intent.putExtra("bkash_trans_id",doctor_appoinment_lists.get(getAdapterPosition()).getbkash_trans_id());
//        intent.putExtra("bkash_amount",doctor_appoinment_lists.get(getAdapterPosition()).getbkash_amount());


        txt_id.setText(appointmentid);
        txt_status.setText(status);
        txt_mothercell.setText(mothernumber);
        txt_appointmentdate.setText(appointmentdate);
        txt_description.setText(description);

        txt_chamber_type.setText(chamber_type);
        txt_bkash_number.setText(bkash_number);
        txt_bkash_trans_id.setText(bkash_trans_id);
        txt_bkash_amount.setText(bkash_amount);
        etxt_zoom_or_chamber_address.setText(zoom_or_chamber_address);


        btnmothercall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + mothernumber;
                callIntent.setData(Uri.parse(p));

                startActivity(callIntent);
            }
        });



        if (status.equals("0"))
        {
            txt_status.setText("Pending");

        }

        else if (status.equals("1"))
        {
            txt_status.setText("Confirmed");
//            btncencelappointment.setVisibility(View.GONE);
//            btnconfirmappointment.setVisibility(View.GONE);
        }

        else if (status.equals("2"))
        {
            txt_status.setText("Cancel");
//            btncencelappointment.setVisibility(View.GONE);
//            btnconfirmappointment.setVisibility(View.GONE);
        }


        btnconfirmappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String zoom_or_chamber_address=etxt_zoom_or_chamber_address.getText().toString().trim();

                if (zoom_or_chamber_address.isEmpty()){
                    etxt_zoom_or_chamber_address.setError("Please Input zoom link/Address");
                    requestfocus(etxt_zoom_or_chamber_address);
                }

                else {
                    UpdateConfimation("1");
                }
            }
        });




//        btnconfirmappointment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Confirm_Appoinment_Activity.this);
//                builder.setMessage("Want to confirmed appointment ?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                UpdateConfimation("1");
//                                dialog.cancel();
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//            }
//        });

        btncencelappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Confirm_Appoinment_Activity.this);
                builder.setMessage("Want to cancel appointment ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                UpdateConfimation("2");
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed
                                dialog.cancel();
                            }
                        }).show();
            }
        });
    }
    public void UpdateConfimation(String s){
        getstatus=s;
        final String zoom_or_chamber_address = etxt_zoom_or_chamber_address.getText().toString();
        loading = new ProgressDialog(this);
        loading.setTitle("Confirm");
        loading.setMessage("Please wait....");
        loading.show();


        String URL = Constant.APPOINTMENT_CONFIRMATION_URL;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String getResponse = response.trim();

                        Log.d("RESPONSE", response);
                        if (getResponse.equals("success")) {
                            loading.dismiss();
                            Intent intent = new Intent(Confirm_Appoinment_Activity.this, DoctorAppoinmentHistory.class);
                            if (getstatus.equals("1")) {
                                Toasty.success(Confirm_Appoinment_Activity.this, " Appointment Confirmed!", Toast.LENGTH_SHORT).show();
                            }
                            else if (getstatus.equals("2")) {
                                Toasty.error(Confirm_Appoinment_Activity.this, " Appointment Cancel!", Toast.LENGTH_SHORT).show();
                            }
                            startActivity(intent);
                        } else if (getResponse.equals("failure")) {

                            loading.dismiss();

                            Intent intent = new Intent(Confirm_Appoinment_Activity.this, DoctorAppoinmentHistory.class);
                            Toast.makeText(Confirm_Appoinment_Activity.this, " Update fail!", Toast.LENGTH_SHORT).show();
                        } else {

                            loading.dismiss();
                            Toast.makeText(Confirm_Appoinment_Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Confirm_Appoinment_Activity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                loading.dismiss();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request

                params.put(Constant.KEY_ID, appointmentid);
                params.put(Constant.KEY_STATUS, getstatus);
                params.put(Constant.KEY_ZOOM_OR_CHAMBER_ADDRESS,zoom_or_chamber_address	);

                Log.d("Fulldata",appointmentid+getstatus+zoom_or_chamber_address);

                //returning parameter
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Confirm_Appoinment_Activity.this);
        requestQueue.add(stringRequest);
    }


    //FOR REQUEST FOCUS
    private void requestfocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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


