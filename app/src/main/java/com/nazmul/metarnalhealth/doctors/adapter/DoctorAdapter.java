package com.nazmul.metarnalhealth.doctors.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.model.Doctors;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private List<Doctors> doctors;
    Context context;
    public DoctorAdapter(Context context, List<Doctors>doctors){
        this.context = context;
        this.doctors = doctors;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.doctorname.setText(doctors.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView doctorname,designation,speacialist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorname = itemView.findViewById(R.id.txt_name);
            designation = itemView.findViewById(R.id.Designation);
            speacialist = itemView.findViewById(R.id.Specialist);

        }
    }
}
