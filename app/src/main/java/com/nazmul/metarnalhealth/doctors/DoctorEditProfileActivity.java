package com.nazmul.metarnalhealth.doctors;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.mothers.EditProfileActivity;
import com.nazmul.metarnalhealth.mothers.MotherHomeActivity;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class DoctorEditProfileActivity extends AppCompatActivity {

    EditText etxt_Fullame,etxt_Cell,txtLocation,etxt_Gender,etxt_Pass,etxt_designation,etxt_speciallist;

    TextView txt_Name,txtUpdate;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_edit_profile);


        txt_Name = findViewById(R.id.txt_name);
        etxt_Fullame = findViewById(R.id.etxtfullname);
        //extra add designation
        etxt_designation = findViewById(R.id.etxtdesignation);
//        extra add speciallist
        etxt_speciallist=findViewById(R.id.etxtspeciallist);
        etxt_Cell = findViewById(R.id.etxtcell);
        txtLocation= findViewById(R.id.location);
        etxt_Gender = findViewById(R.id.gender);
        etxt_Pass= findViewById(R.id.password);
        txtUpdate = findViewById(R.id.doctor_update_profile);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Profile");


        String getName=getIntent().getExtras().getString("Name");
        String getDesignation = getIntent().getExtras().getString("Designation");
        String getSpeciallist = getIntent().getExtras().getString("speciallist");
        String getCell=getIntent().getExtras().getString("cell");
        String getLocation=getIntent().getExtras().getString("location");
        String getGender=getIntent().getExtras().getString("gender");

        txt_Name.setText(getName);
        etxt_Fullame.setText(getName);
        etxt_designation.setText(getDesignation);
        etxt_speciallist.setText(getSpeciallist);
        etxt_Cell.setText(getCell);
        etxt_Cell.setEnabled(false);
        txtLocation.setText(getLocation);
        etxt_Gender.setText(getGender);


        etxt_speciallist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] speciallist={"Medicine","Gynecologist"};
                AlertDialog.Builder builder=new AlertDialog.Builder(DoctorEditProfileActivity.this);
                builder.setTitle("SELECT SPECIALLIST");

                builder.setCancelable(false);
                builder.setItems(speciallist, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position){
                            case 0:
                                etxt_speciallist.setText("Medicine");
                                break;
                            case 1:
                                etxt_speciallist.setText("Gynecologist");
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog locationTypeDialog = builder.create();

                locationTypeDialog.show();
            }
        });




        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorEditProfileActivity.this);

                builder.setTitle("SELECT DIVISION");
//                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                txtLocation.setText("Dhaka");
                                break;

                            case 1:

                                txtLocation.setText("Chittagong");
                                break;

                            case 2:

                                txtLocation.setText("Sylhet");
                                break;

                            case 3:

                                txtLocation.setText("Rajshahi");
                                break;

                            case 4:

                                txtLocation.setText("Barishal");
                                break;

                            case 5:

                                txtLocation.setText("Khulna");
                                break;

                            case 6:

                                txtLocation.setText("Rangpur");
                                break;

                            case 7:

                                txtLocation.setText("Mymensingh");
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog locationTypeDialog = builder.create();

                locationTypeDialog.show();
            }
        });



        etxt_Gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] genderList = {"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorEditProfileActivity.this);
                builder.setTitle("SELECT GENDER");


                builder.setCancelable(false);
                builder.setItems(genderList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                etxt_Gender.setText(genderList[position]);
                                break;

                            case 1:
                                etxt_Gender.setText(genderList[position]);
                                break;
                            default:
                                break;


                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog accountTypeDialog = builder.create();

                accountTypeDialog.show();
            }
        });



        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorEditProfileActivity.this);
                builder.setMessage("Want to Update Profile?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                // Perform Your Task Here--When Yes Is Pressed.
                                UpdateProfile();
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

    public void UpdateProfile() {
        final String name = etxt_Fullame.getText().toString();
        final String designation = etxt_designation.getText().toString();
        final String specialist = etxt_speciallist.getText().toString();
        final String cell = etxt_Cell.getText().toString();
        final String location = txtLocation.getText().toString();
        final String g = etxt_Gender.getText().toString();
        final String password = etxt_Pass.getText().toString();



        if (name.isEmpty()) {
            etxt_Fullame.setError("Name Can't Empty");
            etxt_Fullame.requestFocus();
        } else if (cell.length()!=11) {
            etxt_Cell.setError("Invalid Cell");
            etxt_Cell.requestFocus();

        } else if (password.length()<4) {
            etxt_Pass.setError("Password too short! or Invalid Password");
            etxt_Pass.requestFocus();
        } else {
            loading = new ProgressDialog(this);
            // loading.setIcon(R.drawable.wait_icon);
            // loading.setTitle("Update");
            loading.setMessage("Update...Please wait...");
            loading.show();


            String URL = Constant.DOCTOR_PROFILE_UPDATE_URL;


//Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for track response in logcat
                            Log.d("RESPONSE", response);
                            Log.d("Server_Response",response);
                            // Log.d("RESPONSE", userCell);


                            //If we are getting success from server
                            if (response.equals("success")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(DoctorEditProfileActivity.this, DoctorHomeActivity.class);
                                Toasty.success(DoctorEditProfileActivity.this, " Profile Successfully Updated!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(DoctorEditProfileActivity.this,DoctorHomeActivity.class);
                                Toasty.error(DoctorEditProfileActivity.this, " Profile Update fail!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent);

                            } else {

                                loading.dismiss();
                                Toasty.error(DoctorEditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(DoctorEditProfileActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    // params.put(Constant.KEY_ID, getID);
                    params.put(Constant.KEY_NAME, name);
                    params.put(Constant.KEY_DESIGNATION,designation);
                    params.put(Constant.KEY_SPECIALLIST,specialist);
                    params.put(Constant.KEY_CELL, cell);
                    params.put(Constant.KEY_GENDER, g);
                    params.put(Constant.KEY_LOCATION, location);
                    params.put(Constant.KEY_PASSWORD, password);


                    Log.d("INFO", name+designation+specialist+cell+g+location+password);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(DoctorEditProfileActivity.this);
            requestQueue.add(stringRequest);
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
