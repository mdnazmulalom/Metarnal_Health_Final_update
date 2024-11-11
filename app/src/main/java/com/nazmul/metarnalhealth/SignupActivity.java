package com.nazmul.metarnalhealth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity {

    private ProgressDialog loading;
    TextView txtSignup;

    String name,cell,accounttype,location,gender,password;

    EditText etxtName,etxtCell,etxtAccountType,etxtLocation,etxtGender,etxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button


        etxtName=findViewById(R.id.etxtfullname);
        etxtCell=findViewById(R.id.etxtcell);
        etxtAccountType=findViewById(R.id.ac_type);
        etxtLocation=findViewById(R.id.location);
        etxtGender=findViewById(R.id.gender);
        etxtPassword=findViewById(R.id.password);
        txtSignup=findViewById(R.id.signup);


        //for dropdown gender.....
        etxtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] genderList={"Male","Female"};
                AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity.this);
                builder.setTitle("SELECT GENDER");
                builder.setCancelable(false);
                builder.setItems(genderList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position){
                            case 0:
                                etxtGender.setText(genderList[position]);
                                break;
                            case 1:
                                etxtGender.setText(genderList[position]);
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
                AlertDialog accountTypeDialog=builder.create();
                accountTypeDialog.show();
            }
        });

    //For dropdown AccountType....
    etxtAccountType.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String[] typeList={"Mothers","Doctors"};
            AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity.this);
            builder.setTitle("SELECT ACCOUNT TYPE");
            builder.setCancelable(false);
            builder.setItems(typeList, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int position) {
                    switch (position) {
                        case 0:
                            etxtAccountType.setText(typeList[position]);
                            break;

                        case 1:
                            etxtAccountType.setText(typeList[position]);
                            break;
                        default:
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
            AlertDialog accountype=builder.create();
            accountype.show();
        }
    });

    //For dropdown Location....

        etxtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] locationList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};
                AlertDialog.Builder builder=new AlertDialog.Builder(SignupActivity.this);
                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);
                builder.setCancelable(false);
                builder.setItems(locationList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                etxtLocation.setText("Dhaka");
                                break;

                            case 1:

                                etxtLocation.setText("Chittagong");
                                break;

                            case 2:

                                etxtLocation.setText("Sylhet");
                                break;

                            case 3:

                                etxtLocation.setText("Rajshahi");
                                break;

                            case 4:

                                etxtLocation.setText("Barishal");
                                break;

                            case 5:

                                etxtLocation.setText("Khulna");
                                break;

                            case 6:

                                etxtLocation.setText("Rangpur");
                                break;

                            case 7:

                                etxtLocation.setText("Mymensingh");
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

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=etxtName.getText().toString().trim();
                cell=etxtCell.getText().toString().trim();
                accounttype=etxtAccountType.getText().toString().trim();
                location=etxtLocation.getText().toString().trim();
                gender=etxtGender.getText().toString().trim();
                password=etxtPassword.getText().toString().trim();

                if (name.isEmpty()){
                    etxtName.setError("Please Enter name");
                    requestfocus(etxtName);
                }
                else if (cell.length()!=11 || cell.contains(" ") || cell.charAt(0)!='0' || cell.charAt(1)!='1')
                {
                    etxtCell.setError("Please enter correct cell");
                    requestfocus(etxtCell);
                }
                else if (accounttype.isEmpty()){
                    etxtAccountType.setError("Please select Account type!");
                    requestfocus(etxtAccountType);
                }

                else if (location.isEmpty()){
                    etxtLocation.setError("Please select location");
                    requestfocus(etxtLocation);
                }
                else if (gender.isEmpty()){
                    etxtGender.setError("Please select gender");
                    requestfocus(etxtGender);
                }
                else if (password.length()<4){
                    etxtPassword.setError("Please at lease 4 character long !");
                    requestfocus(etxtPassword);
                }

                else {
                    signup();
                }
            }
        });
    }

    private void signup(){
        loading = new ProgressDialog(SignupActivity.this);
        loading.setMessage("Please wait....");
        loading.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constant.SIGNUP_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESPONSE", response);

                        if (response.equals("success")) {
                            loading.dismiss();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                            Toast.makeText(SignupActivity.this, "Sign up Sucessfull", Toast.LENGTH_SHORT).show();
                            Toasty.warning(SignupActivity.this,"SignUp success",Toasty.LENGTH_SHORT).show();
                            startActivity(intent);

                        } else if (response.equals("exists")) {
                            Toast.makeText(SignupActivity.this, "User already Exist", Toast.LENGTH_SHORT).show();
                            loading.dismiss();

                        } else if (response.equals("failure")) {
                            Toast.makeText(SignupActivity.this, "Registation Failed", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignupActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                loading.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put(Constant.KEY_NAME, name);
                params.put(Constant.KEY_CELL, cell);
                params.put(Constant.KEY_USER_TYPE, accounttype);
                params.put(Constant.KEY_LOCATION, location);
                params.put(Constant.KEY_GENDER,gender);
                params.put(Constant.KEY_PASSWORD, password);

                Log.d("Signup",name +" "+ cell +" "+accounttype+" "+location+" "+gender+" "+password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



    //FOR REQUEST FOCUS
    private void requestfocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
