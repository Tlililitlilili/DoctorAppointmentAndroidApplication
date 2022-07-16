package com.example.doctorappointmentapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAppointmentAdapter extends RecyclerView.Adapter<MyAppointmentAdapter.MyAppointmentViewHolder> {

    private Context mCtx;
    private List<Appointment> appointmentList;

    public MyAppointmentAdapter(Context mCtx, List<Appointment> appointmentList) {
        this.mCtx = mCtx;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public MyAppointmentAdapter.MyAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_appointment, parent, false);
        return new MyAppointmentAdapter.MyAppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAppointmentAdapter.MyAppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.textViewDoctorName.setText(appointment.doctorName);
        holder.textViewID.setText("appointmentID: "+ appointment.appointmentID);
        holder.textViewDate.setText("Date: " + appointment.appointmentDate);
        holder.textViewTime.setText("Time: " + appointment.appointmentTime);
        holder.textViewStatus.setText("Status: " + appointment.appointmentStatus);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    class MyAppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDoctorName, textViewID, textViewDate, textViewTime, textViewStatus;

        public MyAppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDoctorName = itemView.findViewById(R.id.text_view_doctorName);
            textViewID = itemView.findViewById(R.id.text_view_id);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
        }
    }
}
