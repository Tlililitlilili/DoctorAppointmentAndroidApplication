package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminAddDoctorActivity extends AppCompatActivity {

    /*private RecyclerView mAppointmentDetail;

    private AdminManageAppointmentAdapter adapter;
    private List<Appointment> appointmentList;
    DatabaseReference dbAppointment;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_doctor);
        /*mAppointmentDetail = findViewById(R.id.resultSearch);
        mAppointmentDetail.setHasFixedSize(true);
        mAppointmentDetail.setLayoutManager(new LinearLayoutManager(this));
        appointmentList = new ArrayList<>();
        adapter = new AdminManageAppointmentAdapter(this,appointmentList);
        mAppointmentDetail.setAdapter(adapter);

        //1. SELECT * FROM Appointment
        dbAppointment = FirebaseDatabase.getInstance
                ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Appointment");
        dbAppointment.addListenerForSingleValueEvent(valueEventListener);*/
    }

    /*ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
//            Intent intent = getIntent();
//            String username = intent.getStringExtra("pusername");
            appointmentList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    appointmentList.add(appointment);
//                    if (appointment.getPatientName().equals(username)){
//                        appointmentList.add(appointment);
//                    }
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };*/
}