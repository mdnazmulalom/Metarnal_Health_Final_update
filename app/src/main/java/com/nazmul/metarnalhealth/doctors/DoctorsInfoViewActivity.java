package com.nazmul.metarnalhealth.doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.model.Doctors;
import com.nazmul.metarnalhealth.doctors.remote.ApiClient;
import com.nazmul.metarnalhealth.doctors.remote.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsInfoViewActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Doctors> doctorsList;
    private ApiInterface apiInterface;


    //initialize
    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_info_view);

        recyclerView = findViewById(R.id.doctor_recyclerview);
        progressBar = findViewById(R.id.doctor_progressbar);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        fetchData("doctor","");


    }


    public void  fetchData(String type, String key){
        Call<List<Doctors>> call = apiInterface.getDoctors(type,key);
//        Call<List<Doctors>> call = apiInterface.getDoctors(type,key);
//        call.enqueue(new Callback<List<Doctors>>() {
//            @Override
//            public void onResponse(Call<List<Doctors>> call, Response<List<Doctors>> response) {
//                progressBar.setVisibility(View.INVISIBLE);
//                doctorsList = response.body();
//                adapter = new Adapter(DoctorsInfoViewActivity.this,doctorsList);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Doctors>> call, Throwable t) {
//                progressBar.setVisibility(View.INVISIBLE);
//                Toast.makeText(DoctorsInfoViewActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
//
//            }

//            @Override
//            public void onFailure(Call<List<Contacts>> call, Throwable t) {
//                progressBar.setVisibility(View.INVISIBLE);
//                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }
}
