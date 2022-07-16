package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyAppointment extends AppCompatActivity {

    private RecyclerView mAppointmentDetail;

    private MyAppointmentAdapter adapter;
    private List<Appointment> appointmentList;
    private Button ebtnUpcoming, ebtnHistory;
    DatabaseReference dbAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointment);

        mAppointmentDetail = findViewById(R.id.resultSearch);
        mAppointmentDetail.setHasFixedSize(true);
        mAppointmentDetail.setLayoutManager(new LinearLayoutManager(this));
        appointmentList = new ArrayList<>();
        adapter = new MyAppointmentAdapter(this,appointmentList);
        mAppointmentDetail.setAdapter(adapter);

        ebtnUpcoming = findViewById(R.id.btnUpcoming);
        ebtnHistory = findViewById(R.id.btnHistory);

        Intent intent = getIntent();
        String username = intent.getStringExtra("pusername");

        /*//1. SELECT * FROM Appointment
        dbAppointment = FirebaseDatabase.getInstance
                ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Appointment");
        dbAppointment.addListenerForSingleValueEvent(valueEventListener);*/
        Query query = FirebaseDatabase.getInstance
                ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Appointment")
                .orderByChild("patientName")
                .equalTo(username);
        /*Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String currentDate = sdf.format(calendar.getTime());
        Query query = FirebaseDatabase.getInstance
                ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Appointment")
                .orderByChild("appointmentDate")
                .startAt(currentDate)
                .endAt("\uf8ff");*/
        query.addListenerForSingleValueEvent(valueEventListener);

        ebtnUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String currentDate = sdf.format(calendar.getTime());
                Query query = FirebaseDatabase.getInstance
                        ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("Appointment")
                        .orderByChild("appointmentDate")
                        .startAt(currentDate)
                        .endAt("31/7/2022");
//                .orderByChild("patientName")
//                        .equalTo(username);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });
        ebtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String currentDate = sdf.format(calendar.getTime());
                Query query = FirebaseDatabase.getInstance
                        ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("Appointment")
                        .orderByChild("appointmentDate")
                        .startAt("1/7/2022")
                        .endAt("10/7/2022");
//                .orderByChild("patientName")
//                        .equalTo(username);
                query.addListenerForSingleValueEvent(valueEventListener);
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Intent intent = getIntent();
            String username = intent.getStringExtra("pusername");
            appointmentList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Appointment appointment = snapshot.getValue(Appointment.class);
                    if (appointment.getPatientName().equals(username)){
                        appointmentList.add(appointment);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}