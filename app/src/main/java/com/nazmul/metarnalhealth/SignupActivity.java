package com.nazmul.metarnalhealth;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
            final String[] typeList={"Mother","Doctor"};
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
