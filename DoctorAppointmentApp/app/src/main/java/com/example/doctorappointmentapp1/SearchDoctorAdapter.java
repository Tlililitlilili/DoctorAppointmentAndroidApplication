package com.example.doctorappointmentapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SearchDoctorAdapter extends RecyclerView.Adapter<SearchDoctorAdapter.DoctorViewHolder> {

    private Context mCtx;
    private List<Doctor> doctorList;
    private List<String> userList;

    public SearchDoctorAdapter(Context mCtx, List<Doctor> doctorList, List<String> userList) {
        this.mCtx = mCtx;
        this.doctorList = doctorList;
        this.userList = userList;
    }

    @NonNull
    @Override
    public SearchDoctorAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycleview_searchdoctor, parent, false);
        return new SearchDoctorAdapter.DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDoctorAdapter.DoctorViewHolder holder, int position) {
        final Doctor temp=doctorList.get(position);

        Doctor doctor = doctorList.get(position);
        Glide.with(holder.eDoctorPicture.getContext()).load(doctor.image).into(holder.eDoctorPicture);
        holder.eDoctorName.setText(doctor.doctorName);
        holder.eDoctorSpeciality.setText(doctor.doctorSpeciality);
        holder.eEducationBackground.setText(doctor.educationBackground);

        //open NewAppointmentActivity
        holder.eDoctorPicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(mCtx, NewAppointmentActivity.class);
                intent.putExtra("image name", temp.getImage());
                intent.putExtra("doctorName",temp.getDoctorName());
                intent.putExtra("doctorSpeciality",temp.getDoctorSpeciality());
                intent.putExtra("pusername",userList.get(0));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder {

        ImageView eDoctorPicture;
        TextView eDoctorID, eDoctorName, eDoctorSpeciality, eEducationBackground;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            eDoctorPicture = itemView.findViewById(R.id.imgview_doctor);
//            eDoctorID = itemView.findViewById(R.id.txtview_doctorID);
            eDoctorName = itemView.findViewById(R.id.txtview_doctorName);
            eDoctorSpeciality = itemView.findViewById(R.id.txtview_doctorSpeciality);
            eEducationBackground = itemView.findViewById(R.id.txtview_educationBackground);
        }
    }
}
