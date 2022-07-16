package com.example.doctorappointmentapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminManageAppointmentAdapter extends RecyclerView.Adapter<AdminManageAppointmentAdapter.ManageAppointmentViewHolder>{
    private Context mCtx;
    private List<Appointment> appointmentList;

    public AdminManageAppointmentAdapter(Context mCtx, List<Appointment> appointmentList) {
        this.mCtx = mCtx;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AdminManageAppointmentAdapter.ManageAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_admin_manageappointment, parent, false);
        return new AdminManageAppointmentAdapter.ManageAppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminManageAppointmentAdapter.ManageAppointmentViewHolder holder, int position) {
        final Appointment temp=appointmentList.get(position);
        Appointment appointment = appointmentList.get(position);
        holder.textViewDoctorName.setText(appointment.doctorName);
        holder.textViewID.setText("appointmentID: "+ appointment.appointmentID);
        holder.textViewDate.setText("Date: " + appointment.appointmentDate);
        holder.textViewTime.setText("Time: " + appointment.appointmentTime);
        holder.textViewStatus.setText("Status: " + appointment.appointmentStatus);

        //open NewAppointmentActivity
        holder.textViewDoctorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, AdminAcceptRearrangeAppointmentActivity.class);
                intent.putExtra("doctorName", temp.getDoctorName());
                intent.putExtra("appointmentID", temp.getAppointmentID());
                intent.putExtra("Date", temp.getAppointmentDate());
                intent.putExtra("Time", temp.getAppointmentTime());
                intent.putExtra("Status", temp.getAppointmentStatus());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    class ManageAppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDoctorName, textViewID, textViewDate, textViewTime, textViewStatus;

        public ManageAppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDoctorName = itemView.findViewById(R.id.text_view_doctorName);
            textViewID = itemView.findViewById(R.id.text_view_id);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
        }
    }
}
