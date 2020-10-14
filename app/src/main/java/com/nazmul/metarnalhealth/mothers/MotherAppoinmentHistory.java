package com.nazmul.metarnalhealth.mothers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nazmul.metarnalhealth.Constant;
import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.DoctorAppoinmentHistory;
import com.nazmul.metarnalhealth.doctors.adapter.MyAdapter;
import com.nazmul.metarnalhealth.doctors.model.Doctor_Appoinment_List;
import com.nazmul.metarnalhealth.doctors.model.Doctors;
import com.nazmul.metarnalhealth.mothers.adapter.MotherAdapter;
import com.nazmul.metarnalhealth.mothers.model.Mother;
import com.nazmul.metarnalhealth.remote.ApiClient;
import com.nazmul.metarnalhealth.remote.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotherAppoinmentHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MotherAdapter adapter;
    private List<Mother> motherList;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;


    SharedPreferences sharedPreferences;
    String UserCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_appoinment_history);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("All Appointment");


        recyclerView=findViewById(R.id.mother_appoinment_recycleview);
        progressBar=findViewById(R.id.mother_appoinment_progressbar);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        sharedPreferences=getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell=sharedPreferences.getString(Constant.CELL_SHARED_PREF,"Not Available");

        UserCell = cell;
        Fatchdata(UserCell,"");

    }
    public void Fatchdata(String UserCell,String key){
        Call<List<Mother>>call=apiInterface.getMotherAppoinment(UserCell,key);
        call.enqueue(new Callback<List<Mother>>() {
            @Override
            public void onResponse(Call<List<Mother>> call, Response<List<Mother>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                motherList=response.body();
                adapter=new MotherAdapter(MotherAppoinmentHistory.this,motherList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Mother>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MotherAppoinmentHistory.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

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
