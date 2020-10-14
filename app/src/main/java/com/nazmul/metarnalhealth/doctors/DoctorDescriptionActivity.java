package com.nazmul.metarnalhealth.doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.nazmul.metarnalhealth.SignupActivity;
import com.nazmul.metarnalhealth.mothers.MotherHomeActivity;
import com.nazmul.metarnalhealth.remote.ApiInterface;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class DoctorDescriptionActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    String name,designation,speciallist,id,doctor_cell,get_doctor_fee;
    TextView DescriptionName,DescriptionDesignation,DescriptionSpeciallist, TV_date,doctor_fee,payment_amount,doctor_bkash_cell;
    EditText Problem_description,Et_date,bkash_trans_id,from_bkash_number,appoinment_chamber_type;
    Button btnAppoinment;

    DatePickerDialog.OnDateSetListener setListener;


    SharedPreferences sharedPreferences;
//    String user_cell,UserPassword;
      ProgressDialog loading;
//dsdfs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_description);
        DescriptionName=findViewById(R.id.Descripton_Name);
        DescriptionDesignation=findViewById(R.id.Descricption_designation);
        DescriptionSpeciallist=findViewById(R.id.Description_speciallist);
        Problem_description = findViewById(R.id.problem_description);
        doctor_bkash_cell = findViewById(R.id.doctor_bkash_number);
        doctor_fee = findViewById(R.id.doctor_fee);
        appoinment_chamber_type = findViewById(R.id.appoinment_chamber_type);

        payment_amount = findViewById(R.id.payment_amount);
        bkash_trans_id = findViewById(R.id.bkash_trans_id);
        from_bkash_number = findViewById(R.id.from_bkash_number);



        btnAppoinment = findViewById(R.id.btn_appoinment);
//        Et_date = findViewById(R.id.et_date);
        TV_date = findViewById(R.id.tv_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        TV_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DoctorDescriptionActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                TV_date.setText(date);
            }
        };






        id=getIntent().getExtras().getString("id");
        name=getIntent().getExtras().getString("name");
        doctor_cell = getIntent().getExtras().getString("cell");
        designation=getIntent().getExtras().getString("designaiton");
        speciallist=getIntent().getExtras().getString("speciallist");
        get_doctor_fee = getIntent().getExtras().getString("doctor_fee");




        DescriptionName.setText(name);
//        Problem_description.setText(cell);
        DescriptionDesignation.setText(designation);
        DescriptionSpeciallist.setText(speciallist);
        doctor_fee.setText(get_doctor_fee);
        payment_amount.setText(get_doctor_fee);
        doctor_bkash_cell.setText(doctor_cell);


        appoinment_chamber_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] chamberlist={"Online Video Consultation Chamber","Physical Chamber"};
                AlertDialog.Builder builder=new AlertDialog.Builder(DoctorDescriptionActivity.this);
                builder.setTitle("Please Select Chamber");
//                builder.setIcon(R.drawable.ic_location);
                builder.setCancelable(false);
                builder.setItems(chamberlist, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                appoinment_chamber_type.setText("Online Video Consultation Chamber");
                                break;

                            case 1:

                                appoinment_chamber_type.setText("Physical Chamber");
                                break;

                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog locationTypeDialog = builder.create();
                locationTypeDialog.show();
            }
        });


        btnAppoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String problem_description =Problem_description.getText().toString();
                String appoinment_date = TV_date.getText().toString();
                String bkash_trans = bkash_trans_id.getText().toString();
                String fr_bkash_number = from_bkash_number.getText().toString();
                String chamber_type = appoinment_chamber_type.getText().toString();


                if (appoinment_date.isEmpty()){
                    TV_date.setError("Date can't be empty");
                    TV_date.requestFocus();
                }
                if (problem_description.isEmpty()){
                    Problem_description.setError("Problem Description can't be empty");
                    Problem_description.requestFocus();
                }
                if (bkash_trans.isEmpty()){
                    bkash_trans_id.setError("Please Enter bKash Trans ID");
                    bkash_trans_id.requestFocus();
                }
                if (fr_bkash_number.length()!=11 || fr_bkash_number.contains(" ") || fr_bkash_number.charAt(0)!='0' || fr_bkash_number.charAt(1)!='1')
                {
                    from_bkash_number.setError("Please enter correct cell");
                    from_bkash_number.requestFocus();
                }
                if (chamber_type.isEmpty()){
                    appoinment_chamber_type.setError("Please Select Chamber");
                    appoinment_chamber_type.requestFocus();
                }
                else {
                    appoinment();
                }
            }
        });

    }
    private void appoinment() {
        sharedPreferences = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");

//        final String user_cell = cell;
        final String name = getIntent().getExtras().getString("name");
        final String doctors_cell = getIntent().getExtras().getString("cell");
        final String designation = getIntent().getExtras().getString("designaiton");
        final String problem_descripion = Problem_description.getText().toString();
        final String appoinment_date = TV_date.getText().toString();

        final String bk_payment_amount = payment_amount.getText().toString();
        final String bk_trans_id = bkash_trans_id.getText().toString();
        final String from_bkash_num = from_bkash_number.getText().toString();

        final String Appointment_Chamber_type = appoinment_chamber_type.getText().toString();


        loading = new ProgressDialog(DoctorDescriptionActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.DOCTOR_APPOINMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            loading.dismiss();
                            Intent intent = new Intent(DoctorDescriptionActivity.this, MotherHomeActivity.class);
                            Toasty.success(DoctorDescriptionActivity.this, "Appoinment success", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        if (response.equals("failure")) {
                            loading.dismiss();
                            Toasty.success(DoctorDescriptionActivity.this, "Appoinment failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toasty.error(DoctorDescriptionActivity.this, "Error in connection!", Toast.LENGTH_LONG).show();
                    }
                }){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put(Constant.KEY_NAME,name);
                params.put(Constant.KEY_USER_CELL,cell);
                params.put(Constant.KEY_DOCTOR_CELL,doctors_cell);
                params.put(Constant.KEY_DESIGNATION,designation);
                params.put(Constant.KEY_PROBLEM_DESCRIPTON,problem_descripion);
                params.put(Constant.KEY_APPOINMENT_DATE,appoinment_date);
                params.put(Constant.KEY_BK_PAYMENT_AMOUNT,bk_payment_amount);
                params.put(Constant.KEY_BK_TRANS_ID,bk_trans_id);
                params.put(Constant.KEY_FROM_BKASH_NUMBER,from_bkash_num);
                params.put(Constant.KEY_APPOINTMENT_CHAMBER_TYPE,Appointment_Chamber_type);


                Log.d("NEW",name+appoinment_date+" "+cell+" "+doctors_cell+designation+problem_descripion+" amount"+ bk_payment_amount+bk_trans_id+from_bkash_num+Appointment_Chamber_type);


                //returning parameter
                return params;
            }
        };
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }



//
//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<Doctors> call = apiInterface.doctorsAppoinment(user_cell,name,designation,doctors_cell,problem_descripion);
//        call.enqueue(new Callback<Doctors>() {
//            @Override
//            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
//                String value = response.body().getValue();
//                String message = response.body().getMassage();
//                Log.d("message",message);
//                if (value.equals("1")) {
//                    Toasty.success(DoctorDescriptionActivity.this, message, Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toasty.success(DoctorDescriptionActivity.this, message, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Doctors> call, Throwable t) {
//                Toasty.error(DoctorDescriptionActivity.this, "Error! "+ t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//



//    }
}
