package com.nazmul.metarnalhealth.mothers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.DoctorsInfoViewActivity;
import com.nazmul.metarnalhealth.doctors.model.Doctors;
import com.nazmul.metarnalhealth.mothers.adapter.HospitalAdapter;
import com.nazmul.metarnalhealth.mothers.model.Hospital;
import com.nazmul.metarnalhealth.remote.ApiClient;
import com.nazmul.metarnalhealth.remote.ApiInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalInfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HospitalAdapter adapter;
    private List<Hospital>hospitalList;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);

        recyclerView=findViewById(R.id.hospitalinfo_recycleview);
        progressBar=findViewById(R.id.hospitalinfo_progressbar);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        Fatchdata("hospital","");
    }

    public void Fatchdata(String type,String key) {
        Call<List<Hospital>> call=apiInterface.gethospitalinfo(type, key);
        call.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                hospitalList=response.body();
                adapter= new HospitalAdapter(HospitalInfoActivity.this,hospitalList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(HospitalInfoActivity.this, "Error : "+ t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fatchdata("Hospital", "");

    }
}

