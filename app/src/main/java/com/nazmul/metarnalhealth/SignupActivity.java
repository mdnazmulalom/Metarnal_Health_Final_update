package com.nazmul.metarnalhealth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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


        etxtName=findViewById(R.id.fullname);
        etxtCell=findViewById(R.id.cell);
        etxtAccountType=findViewById(R.id.ac_type);
        etxtLocation=findViewById(R.id.location);
        etxtGender=findViewById(R.id.gender);
        etxtPassword=findViewById(R.id.password);
        txtSignup=findViewById(R.id.signup);








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
