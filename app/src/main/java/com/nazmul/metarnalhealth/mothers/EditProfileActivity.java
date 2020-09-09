package com.nazmul.metarnalhealth.mothers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

public class EditProfileActivity extends AppCompatActivity {

    EditText etxtFullame,etxtCell,txtLocation,txtGender,etxt_Pass;

    TextView txtName,txtUpdate;

    ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        

        txtName = findViewById(R.id.doctor_name);
        etxtFullame = findViewById(R.id.etxtfullname);
        etxtCell = findViewById(R.id.etxtcell);
        txtLocation= findViewById(R.id.location);
        txtGender = findViewById(R.id.gender);
        etxt_Pass= findViewById(R.id.password);
        txtUpdate = findViewById(R.id.mother_update);

//        etxt_Cell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toasty.warning(EditProfileActivity.this,"You Can't Change your number", Toast.LENGTH_SHORT).show();
//
//            }
//        });



        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Profile");

        String getName=getIntent().getExtras().getString("Name");
        String getCell=getIntent().getExtras().getString("cell");
        String getLocation=getIntent().getExtras().getString("location");
        String getGender=getIntent().getExtras().getString("gender");
////        String getName=getIntent().getExtras().getString("name");



           txtName.setText(getName);
           etxtFullame.setText(getName);
           etxtCell.setText(getCell);
           etxtCell.setEnabled(false);
           txtLocation.setText(getLocation);
           txtGender.setText(getGender);

        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);

                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);


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



        txtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] genderList = {"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder.setTitle("SELECT GENDER");


                builder.setCancelable(false);
                builder.setItems(genderList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                txtGender.setText(genderList[position]);
                                break;

                            case 1:
                                txtGender.setText(genderList[position]);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
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


    //update contact method
    public void UpdateProfile() {

        final String name = etxtFullame.getText().toString();
        final String cell = etxtCell.getText().toString();
        final String location = txtLocation.getText().toString();
        final String gender = txtGender.getText().toString();
        final String password = etxt_Pass.getText().toString();



        if (name.isEmpty()) {
            txtName.setError("Name Can't Empty");
            txtName.requestFocus();
        } else if (cell.length()!=11) {
            etxtCell.setError("Invalid Cell");
            etxtCell.requestFocus();

        } else if (password.length()<4) {
            etxt_Pass.setError("Password too short! or Invalid Password");
            etxt_Pass.requestFocus();
        } else {
            loading = new ProgressDialog(this);
            // loading.setIcon(R.drawable.wait_icon);
            // loading.setTitle("Update");
            loading.setMessage("Update...Please wait...");
            loading.show();

            String URL = Constant.MOTHER_PROFILE_UPDATE_URL;




            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for track response in logcat
                            Log.d("RESPONSE", response);
                            // Log.d("RESPONSE", userCell);


                            //If we are getting success from server
                            if (response.equals("success")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(EditProfileActivity.this, MotherHomeActivity.class);
                                Toasty.success(EditProfileActivity.this, " Profile Successfully Updated!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(EditProfileActivity.this,MotherHomeActivity.class);
                                Toasty.error(EditProfileActivity.this, " Profile Update fail!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent);

                            } else {

                                loading.dismiss();
                                Toasty.error(EditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(EditProfileActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    // params.put(Constant.KEY_ID, getID);
                    params.put(Constant.KEY_NAME, name);
                    params.put(Constant.KEY_CELL, cell);
                    params.put(Constant.KEY_GENDER, gender);
                    params.put(Constant.KEY_LOCATION, location);
                    params.put(Constant.KEY_PASSWORD, password);


                   Log.d("INFO", name+cell+gender+location+password);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(EditProfileActivity.this);
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
