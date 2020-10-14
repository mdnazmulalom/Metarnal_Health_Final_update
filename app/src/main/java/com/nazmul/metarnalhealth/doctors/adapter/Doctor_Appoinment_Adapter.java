package com.nazmul.metarnalhealth.doctors.adapter;

import android.content.Context;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nazmul.metarnalhealth.R;
import com.nazmul.metarnalhealth.doctors.Confirm_Appoinment_Activity;
import com.nazmul.metarnalhealth.doctors.DoctorDescriptionActivity;
import com.nazmul.metarnalhealth.doctors.model.Doctor_Appoinment_List;


import java.util.List;

public class Doctor_Appoinment_Adapter extends RecyclerView.Adapter<Doctor_Appoinment_Adapter.MyViewHolder> {
    private List<Doctor_Appoinment_List> doctor_appoinment_lists;
    Context context;

    public Doctor_Appoinment_Adapter(Context context,List<Doctor_Appoinment_List> doctor_appoinment_lists){
        this.context = context;
        this.doctor_appoinment_lists = doctor_appoinment_lists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_appoinment_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Doctor_Appoinment_Adapter.MyViewHolder holder, int position) {
//       holder.doctor_appoinment_staatus.setText(doctor_appoinment_lists.get(position).getStatus());

        //        holder.doctorstatus.setText(mothers.get(position).getStatus());
        String status=doctor_appoinment_lists.get(position).getStatus();
        if (status.equals("0")){
            holder.doctor_appoinment_staatus.setText("Panding");
        }
        else if (status.equals("1")){
            holder.doctor_appoinment_staatus.setText("Confirmed");
        }
        else {
            holder.doctor_appoinment_staatus.setText("Cancel");
        }




       holder.doctor_appoinment_mother_cell.setText(doctor_appoinment_lists.get(position).getMother_number());
       holder.doctor_apponment_date.setText(doctor_appoinment_lists.get(position).getAppoinment_date());
       holder.problem_description.setText(doctor_appoinment_lists.get(position).getProblem_descripion());
    }
    @Override
    public int getItemCount() {
        return doctor_appoinment_lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView doctor_appoinment_staatus,doctor_appoinment_mother_cell,doctor_apponment_date,problem_description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor_appoinment_staatus = itemView.findViewById(R.id.doctor_appoinment_staatus);
            doctor_appoinment_mother_cell = itemView.findViewById(R.id.doctor_appoinment_mother_cell);
            doctor_apponment_date = itemView.findViewById(R.id.doctor_apponment_date);
            problem_description = itemView.findViewById(R.id.problem_description);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, Confirm_Appoinment_Activity.class);
            intent.putExtra("id",doctor_appoinment_lists.get(getAdapterPosition()).getAppoinment_id());
            intent.putExtra("status",doctor_appoinment_lists.get(getAdapterPosition()).getStatus());
            intent.putExtra("mothercell",doctor_appoinment_lists.get(getAdapterPosition()).getMother_number());
            intent.putExtra("appointmentdate",doctor_appoinment_lists.get(getAdapterPosition()).getAppoinment_date());
            intent.putExtra("description",doctor_appoinment_lists.get(getAdapterPosition()).getProblem_descripion());
            intent.putExtra("chamber_type",doctor_appoinment_lists.get(getAdapterPosition()).getChamber_type());
            intent.putExtra("zoom_or_chamber_address",doctor_appoinment_lists.get(getAdapterPosition()).getzoom_or_chamber_address());
            intent.putExtra("bkash_number",doctor_appoinment_lists.get(getAdapterPosition()).getbkash_number());
            intent.putExtra("bkash_trans_id",doctor_appoinment_lists.get(getAdapterPosition()).getbkash_trans_id());
            intent.putExtra("bkash_amount",doctor_appoinment_lists.get(getAdapterPosition()).getbkash_amount());
            context.startActivity(intent);
        }
    }
}
