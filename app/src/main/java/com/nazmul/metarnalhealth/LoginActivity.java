package com.nazmul.metarnalhealth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.nazmul.metarnalhealth.doctors.DoctorHomeActivity;
import com.nazmul.metarnalhealth.mothers.MotherHomeActivity;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    EditText etxtCell,etxtPassword,etxtAccountType;
    TextView signin,signup;

    private ProgressDialog loading;
    SharedPreferences sharedPreferences;
    String UserCell,UserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        etxtCell=findViewById(R.id.etxt_cell);
        etxtPassword=findViewById(R.id.password);
        etxtAccountType=findViewById(R.id.ac_type);

        sharedPreferences=getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell=sharedPreferences.getString(Constant.CELL_SHARED_PREF,"Not Available");
        String get_password=sharedPreferences.getString(Constant.PASSWORD_SHARED_PREF,"0");
        String get_ac_type=sharedPreferences.getString(Constant.AC_TYPE_SHARED_PREF, "Not Available");

        UserCell=cell;
        UserPassword=get_password;

        if (!UserCell.equals("Not available") && !UserPassword.equals("0")){
            etxtCell.setText(UserCell);
            etxtPassword.setText(UserPassword);
            etxtAccountType.setText(get_ac_type);
            login();
        }


        etxtAccountType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] typeList={"Mothers","Doctors"};
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("SELECT TYPE");

                builder.setCancelable(false);
                builder.setItems(typeList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position){
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
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog accountTypeDialog = builder.create();
                accountTypeDialog.show();
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
        final String userCell=etxtCell.getText().toString().trim();
        final String userPassword=etxtPassword.getText().toString().trim();
        final String account_type=etxtAccountType.getText().toString().trim();



        if ( userCell.length() != 11 || userCell.contains(" ") || userCell.charAt(0) != '0' || userCell.charAt(1) != '1') {

            etxtCell.setError("Please enter cell !");
            etxtCell.requestFocus();
        }

        else if (userPassword.length() < 4)
        {
            etxtPassword.setError("Password at least 4 character long !");
            etxtPassword.requestFocus();
        }

        else if (account_type.isEmpty())
        {
            etxtAccountType.setError("Please Select Type !");
            etxtAccountType.requestFocus();
        }
        else {
            loading = new ProgressDialog(this);
            loading.setMessage("Please wait....");
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String myResponse = response.trim();
                            Log.d("Response", response);

                            if (myResponse.equals("mothers")) {
                                sharedPreferences = LoginActivity.this.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(Constant.AC_TYPE_SHARED_PREF, account_type);
                                editor.putString(Constant.CELL_SHARED_PREF, userCell);
                                editor.putString(Constant.PASSWORD_SHARED_PREF, userPassword);

                                editor.apply();

                                loading.dismiss();
                                Toasty.success(LoginActivity.this, "Login Success", Toasty.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MotherHomeActivity.class);
                                startActivity(intent);


                            }
                            else if (myResponse.equals("doctors")) {

                                sharedPreferences = LoginActivity.this.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString(Constant.AC_TYPE_SHARED_PREF, account_type);
                                editor.putString(Constant.CELL_SHARED_PREF, userCell);
                                editor.putString(Constant.PASSWORD_SHARED_PREF, userPassword);

                                editor.apply();

                                loading.dismiss();

                                Toasty.warning(LoginActivity.this, "Login Success", Toasty.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, DoctorHomeActivity.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid Cell or password", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }

                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this,"Error in Connection!!",Toast.LENGTH_LONG).show();
                            loading.dismiss();



                        }
                    }){
                @Override
                protected Map<String,String>getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put(Constant.KEY_USER_TYPE,account_type);
                    params.put(Constant.KEY_CELL,userCell);
                    params.put(Constant.KEY_PASSWORD,userPassword);
                    Log.d("Values",account_type+" "+ userCell+" "+userPassword);
                    return params;

                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
    }
}

