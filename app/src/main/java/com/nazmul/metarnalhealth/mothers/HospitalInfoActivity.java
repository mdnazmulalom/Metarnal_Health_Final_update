package com.nazmul.metarnalhealth.mothers;

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
import com.nazmul.metarnalhealth.mothers.adapter.HospitalAdapter;
import com.nazmul.metarnalhealth.mothers.model.Hospital;
import com.nazmul.metarnalhealth.remote.ApiClient;
import com.nazmul.metarnalhealth.remote.ApiInterface;

import java.util.List;

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

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Hospital info");

        recyclerView=findViewById(R.id.hospitalinfo_recycleview);
        progressBar=findViewById(R.id.hospitalinfo_progressbar);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        Fatchdata("hospital","");
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            MenuInflater inflater=getMenuInflater();
            inflater.inflate(R.menu.menu,menu);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d("query",query);
                    Fatchdata("hospital",query);
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d("submit",newText);
                    Fatchdata("hospital",newText);
                    return false;
                }
            });


            return true;
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

