package com.nazmul.metarnalhealth.doctors;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DoctorProfileActivity extends AppCompatActivity {

    TextView txtFullname, txtName,txtdesignation,txtspeciallist,txtCell,txtLocation,txtGender,txtPassword,txtUpdateprofile;
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
        txtdesignation = findViewById(R.id.txt_designation);
        txtspeciallist=findViewById(R.id.txt_speciallist);
        txtName=findViewById(R.id.doctor_name);
        txtCell=findViewById(R.id.etxtcell);
        txtLocation=findViewById(R.id.location);
        txtGender=findViewById(R.id.gender);
        txtPassword=findViewById(R.id.password);
        txtUpdateprofile=findViewById(R.id.doctor_update_profile);

        txtUpdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s= txtFullname.getText().toString();
                String designation = txtdesignation.getText().toString();
                String speciallist=txtspeciallist.getText().toString();
                String cell = txtCell.getText().toString();
                String location = txtLocation.getText().toString();
                String gender = txtGender.getText().toString();

                Intent intent=new Intent(DoctorProfileActivity.this,DoctorEditProfileActivity.class);
                intent.putExtra("Name",s);
                intent.putExtra("Designation",designation);
                intent.putExtra("speciallist",speciallist);
                intent.putExtra("cell",cell);
                intent.putExtra("location",location);
                intent.putExtra("gender",gender);
                startActivity(intent);

            }
        });
        getData();
    }
    private void getData(){
        loading = new ProgressDialog(DoctorProfileActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        String url= Constant.DOCTOR_PROFILE_URL+UserCell;
        Log.d("URL",url);
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoctorProfileActivity.this,"No Internet connection",Toast.LENGTH_SHORT)
                        .show();
                loading.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(DoctorProfileActivity.this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response){

        String name ="";
        String designation ="";
        String speciallist="";
        String cell ="";
        String location ="";
        String gender ="";

//        String password="";

        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray result=jsonObject.getJSONArray(Constant.JSON_ARRAY);
            JSONObject ProfileData = result.getJSONObject(0);

            Log.d("RESPONSE",response);



            name = ProfileData.getString(Constant.KEY_NAME);
            designation = ProfileData.getString(Constant.KEY_DESIGNATION);
            speciallist=ProfileData.getString(Constant.KEY_SPECIALLIST);
            cell = ProfileData.getString(Constant.KEY_CELL);
            location = ProfileData.getString(Constant.KEY_LOCATION);
            gender = ProfileData.getString(Constant.KEY_GENDER);
//            password=ProfileData.getString(Constant.KEY_PASSWORD);

            Log.d("Name",name+ designation +" "+speciallist+""+cell+" "+location+" "+gender);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        txtFullname.setText(name);
        txtdesignation.setText(designation);
        txtspeciallist.setText(speciallist);
        txtName.setText(name);
        txtCell.setText(cell);
        txtLocation.setText(location);
        txtGender.setText(gender);
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
