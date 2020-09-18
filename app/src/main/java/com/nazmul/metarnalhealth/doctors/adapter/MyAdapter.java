package com.nazmul.metarnalhealth.doctors.adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.DoctorDescriptionActivity;
import com.nazmul.metarnalhealth.doctors.model.Doctors;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Doctors> doctors;
    Context context;
    public MyAdapter(Context context,List<Doctors> doctors){
        this.context = context;
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.name.setText(doctors.get(position).getName());
      holder.specialist.setText(doctors.get(position).getSpeciallist());
      holder.designation.setText(doctors.get(position).getDesignation());
      holder.location.setText(doctors.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,designation,specialist,location,appoinment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.doctor_name);
            designation = itemView.findViewById(R.id.Designation);
            specialist = itemView.findViewById(R.id.Specialist);
            location = itemView.findViewById(R.id.location);
            appoinment = itemView.findViewById(R.id.appoinment);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
          Intent intent=new Intent(context, DoctorDescriptionActivity.class);
          intent.putExtra("id",doctors.get(getAdapterPosition()).getDoctorID());
          intent.putExtra("name",doctors.get(getAdapterPosition()).getName());
          intent.putExtra("designaiton",doctors.get(getAdapterPosition()).getDesignation());
          intent.putExtra("speciallist",doctors.get(getAdapterPosition()).getSpeciallist());
          context.startActivity(intent);
        }
    }
}
