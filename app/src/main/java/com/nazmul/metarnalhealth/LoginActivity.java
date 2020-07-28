package com.nazmul.metarnalhealth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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


        }
    }
}

