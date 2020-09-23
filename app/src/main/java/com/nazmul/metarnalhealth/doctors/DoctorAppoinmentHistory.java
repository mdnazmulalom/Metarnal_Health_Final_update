package com.nazmul.metarnalhealth.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.adapter.Doctor_Appoinment_Adapter;
import com.nazmul.metarnalhealth.doctors.adapter.MyAdapter;
import com.nazmul.metarnalhealth.doctors.model.Doctor_Appoinment_List;
import com.nazmul.metarnalhealth.doctors.model.Doctors;
import com.nazmul.metarnalhealth.remote.ApiClient;
import com.nazmul.metarnalhealth.remote.ApiInterface;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorAppoinmentHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Doctor_Appoinment_Adapter doctor_appoinment_adapter;
    private List<Doctor_Appoinment_List> doctor_appoinment_lists;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;


    private ProgressDialog loading;
    SharedPreferences sharedPreferences;
    String UserCell,UserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appoinment_history);


        recyclerView = findViewById(R.id.doctor_appoinment_recycleview);
        progressBar = findViewById(R.id.appoinment_progressbar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        getSupportActionBar().setHomeButtonEnabled(false); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//for back button
        getSupportActionBar().setTitle("Doctor Appoinment List");

        sharedPreferences=getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell=sharedPreferences.getString(Constant.CELL_SHARED_PREF,"Not Available");

        UserCell = cell;


        FetchData(UserCell,"");

    }

    public void FetchData(String userCell,String key){
        Call<List<Doctor_Appoinment_List>> call = apiInterface.getDoctorAppoinment(userCell,key);
        call.enqueue(new Callback<List<Doctor_Appoinment_List>>() {
            @Override
            public void onResponse(Call<List<Doctor_Appoinment_List>> call, Response<List<Doctor_Appoinment_List>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                doctor_appoinment_lists = response.body();
                doctor_appoinment_adapter = new Doctor_Appoinment_Adapter(DoctorAppoinmentHistory.this, doctor_appoinment_lists);
                recyclerView.setAdapter(doctor_appoinment_adapter);
                doctor_appoinment_adapter.notifyDataSetChanged();//for search
            }

            @Override
            public void onFailure(Call<List<Doctor_Appoinment_List>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(DoctorAppoinmentHistory.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
