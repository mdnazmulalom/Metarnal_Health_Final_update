package com.nazmul.metarnalhealth.doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.nazmul.metarnalhealth.mothers.MotherHomeActivity;
import com.nazmul.metarnalhealth.remote.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class DoctorDescriptionActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    String name,designation,speciallist,id,doctor_cell;
    TextView DescriptionName,DescriptionDesignation,DescriptionSpeciallist;
    EditText Problem_description;
    Button btnAppoinment;

    SharedPreferences sharedPreferences;
//    String user_cell,UserPassword;
      ProgressDialog loading;
dsdfs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_description);
        DescriptionName=findViewById(R.id.Descripton_Name);
        DescriptionDesignation=findViewById(R.id.Descricption_designation);
        DescriptionSpeciallist=findViewById(R.id.Description_speciallist);
        Problem_description = findViewById(R.id.problem_description);
        btnAppoinment = findViewById(R.id.btn_appoinment);


        id=getIntent().getExtras().getString("id");
        name=getIntent().getExtras().getString("name");
        doctor_cell = getIntent().getExtras().getString("cell");
        designation=getIntent().getExtras().getString("designaiton");
        speciallist=getIntent().getExtras().getString("speciallist");




        DescriptionName.setText(name);
//        Problem_description.setText(cell);
        DescriptionDesignation.setText(designation);
        DescriptionSpeciallist.setText(speciallist);

        btnAppoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String problem_description =Problem_description.getText().toString();
                if (problem_description.isEmpty()){
                    Problem_description.setError("Problem Description can't be empty");
                    Problem_description.requestFocus();
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

                Log.d("Fulldata",name+cell+doctors_cell+designation+problem_descripion);


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
