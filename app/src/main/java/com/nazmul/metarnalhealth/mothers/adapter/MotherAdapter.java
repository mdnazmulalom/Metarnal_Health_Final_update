package com.nazmul.metarnalhealth.mothers.adapter;

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
import com.nazmul.metarnalhealth.doctors.adapter.MyAdapter;
import com.nazmul.metarnalhealth.mothers.Mother_Appointment_Details_ctivity;
import com.nazmul.metarnalhealth.mothers.model.Mother;

import java.util.List;

public class MotherAdapter extends RecyclerView.Adapter<MotherAdapter.MyViewHolder> {
    private List<Mother> mothers;
    Context context;
    public MotherAdapter(Context context,List<Mother>mothers){
        this.context=context;
        this.mothers=mothers;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mother_appoinment_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        holder.doctorstatus.setText(mothers.get(position).getStatus());
        String status=mothers.get(position).getStatus();
        if (status.equals("0")){
            holder.doctorstatus.setText("Panding");
        }
        else if (status.equals("1")){
            holder.doctorstatus.setText("Confirm");
        }
        else {
            holder.doctorstatus.setText("Cancel");
        }

        holder.doctorname.setText(mothers.get(position).getDoctor_name());
        holder.appoinmentdate.setText(mothers.get(position).getAppoinment_date());
        holder.desctiption.setText(mothers.get(position).getProblem_descripion());
    }

    @Override
    public int getItemCount() {
        return mothers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView doctorstatus,doctorname,appoinmentdate,desctiption;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorstatus= itemView.findViewById(R.id.doctor_appoinment_status);
            doctorname=itemView.findViewById(R.id.doctor_appoinment_name);
            appoinmentdate=itemView.findViewById(R.id.doctor_apponment_date);
            desctiption=itemView.findViewById(R.id.doctor_problem_description);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Mother_Appointment_Details_ctivity.class);
//            intent.putExtra("status",mothers.get(getAdapterPosition()).getStatus());
//            intent.putExtra("doctor_name",mothers.get(getAdapterPosition()).getDoctor_name());
//            intent.putExtra("Date",mothers.get(getAdapterPosition()).getAppoinment_date());
//            intent.putExtra("descripton",mothers.get(getAdapterPosition()).getProblem_descripion());
////            intent.putExtra("speciallist",doctors.get(getAdapterPosition()).getSpeciallist());
////            intent.putExtra("doctor_fee",doctors.get(getAdapterPosition()).getDoctor_fee());
            context.startActivity(intent);
        }
    }
}
