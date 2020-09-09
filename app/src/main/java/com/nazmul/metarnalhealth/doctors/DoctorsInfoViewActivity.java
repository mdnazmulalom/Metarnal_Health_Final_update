package com.nazmul.metarnalhealth.doctors;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.adapter.MyAdapter;
import com.nazmul.metarnalhealth.doctors.model.Doctors;
import com.nazmul.metarnalhealth.remote.ApiClient;
import com.nazmul.metarnalhealth.remote.ApiInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorsInfoViewActivity extends AppCompatActivity{

   private RecyclerView recyclerView;
   private RecyclerView.LayoutManager layoutManager;
   private MyAdapter adapter;
   private List<Doctors> doctorsList;
   private ApiInterface apiInterface;
   private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_info_view);


        getSupportActionBar().setHomeButtonEnabled(false); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//for back button
        getSupportActionBar().setTitle("Doctor Information");

        recyclerView = findViewById(R.id.doctor_recyclerview);
        progressBar = findViewById(R.id.doctor_progressbar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        FetchData("Doctors","");


    }
    public void FetchData(String type,String key){
        Call<List<Doctors>> call = apiInterface.getDoctors(type,key);
        call.enqueue(new Callback<List<Doctors>>() {
            @Override
            public void onResponse(Call<List<Doctors>> call, Response<List<Doctors>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                doctorsList = response.body();
                adapter = new MyAdapter(DoctorsInfoViewActivity.this,doctorsList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Doctors>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toasty.error(DoctorsInfoViewActivity.this,"Error"+t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }


    //when activity is resumed this method is called
    @Override
    protected void onResume() {
        super.onResume();
        FetchData("Doctors", "");

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
