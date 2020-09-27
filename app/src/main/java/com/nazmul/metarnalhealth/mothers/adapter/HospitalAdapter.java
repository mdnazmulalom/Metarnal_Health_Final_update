package com.nazmul.metarnalhealth.mothers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.mothers.model.Hospital;
import com.nazmul.metarnalhealth.mothers.model.Mother;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> {
    private List<Hospital> hospitals;
    Context context;
    public HospitalAdapter(Context context,List<Hospital>hospitals){
        this.context=context;
        this.hospitals=hospitals;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hospitalinfo_list_item,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txthospitalname.setText(hospitals.get(position).getHospitalname());
        holder.txtcategory.setText(hospitals.get(position).getCategory());
        holder.txtaddress.setText(hospitals.get(position).getAddress());
        holder.txtwebsite.setText(hospitals.get(position).getWebsite());
        holder.txthospitalnumber.setText(hospitals.get(position).gethospitalnumber());
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txthospitalname,txtcategory,txtaddress,txtwebsite,txthospitalnumber;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txthospitalname=itemView.findViewById(R.id.hospital_name);
            txtcategory=itemView.findViewById(R.id.category);
            txtaddress=itemView.findViewById(R.id.address);
            txtwebsite=itemView.findViewById(R.id.website);
            txthospitalnumber=itemView.findViewById(R.id.hospitalnumber);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
