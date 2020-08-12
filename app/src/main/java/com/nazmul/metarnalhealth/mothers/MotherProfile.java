package com.nazmul.metarnalhealth.mothers;

import androidx.appcompat.app.AppCompatActivity;

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

public class MotherProfile extends AppCompatActivity {

    TextView txtUpdate_profile,txtname,etxtFulname,etxtCell,etxtLocation,etxtGender;
    String Usercell;
    private ProgressDialog loading;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_profile);

        sharedPreferences=getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell=sharedPreferences.getString(Constant.CELL_SHARED_PREF,"not available");
        Usercell=cell;
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Mother Profile");

        txtname = findViewById(R.id.txt_name);
        etxtFulname=findViewById(R.id.etxtfullname);
        etxtCell=findViewById(R.id.etxtcell);
        etxtLocation=findViewById(R.id.location);
        etxtGender=findViewById(R.id.gender);

        txtUpdate_profile=findViewById(R.id.update_profile);



        txtUpdate_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotherProfile.this,EditProfileActivity.class);
                //for testing
//                String user_name = "Jhon Doe";
//                intent.putExtra("Name",user_name);
                intent.putExtra("name",etxtFulname.getText());
                intent.putExtra("cell",etxtCell.getText());
                intent.putExtra("location",etxtLocation.getText());
                intent.putExtra("gender",etxtGender.getText());
                startActivity(intent);
            }
        });

        getdata();

    }

    private void getdata(){

        loading = new ProgressDialog(MotherProfile.this);
        loading.setMessage("Please wait....");
        loading.show();

        String url=Constant.PROFILE_URL+Usercell;
        Log.d("URL",url);
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MotherProfile.this,"No Internet connection",Toast.LENGTH_SHORT)
                        .show();
                loading.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MotherProfile.this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){

        String name ="";
        String cell ="";
        String location ="";
        String gender ="";

//        String password="";

        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray result=jsonObject.getJSONArray(Constant.JSON_ARRAY);
            JSONObject ProfileData = result.getJSONObject(0);



            name = ProfileData.getString(Constant.KEY_NAME);
            cell = ProfileData.getString(Constant.KEY_CELL);
            location = ProfileData.getString(Constant.KEY_LOCATION);
            gender = ProfileData.getString(Constant.KEY_GENDER);
//            password=ProfileData.getString(Constant.KEY_PASSWORD);

            Log.d("Name",name+" "+cell+" "+location+" "+gender);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        txtname.setText(name);
        etxtFulname.setText(name);
        etxtCell.setText(cell);
        etxtLocation.setText(location);
        etxtGender.setText(gender);
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
