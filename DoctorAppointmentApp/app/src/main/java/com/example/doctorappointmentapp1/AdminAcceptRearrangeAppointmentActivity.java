package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAcceptRearrangeAppointmentActivity extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference databaseAppointment;

    // recycleview
    TextView eAppointmentID, eDoctorName, eDate, eTime, eStatus;
    Button ebtnAccept, ebtnRearrange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accept_rearrange_appointment);
        eAppointmentID = findViewById(R.id.text_view_id);
        eDoctorName = findViewById(R.id.text_view_doctorName);
        eDate = findViewById(R.id.text_view_date);
        eTime = findViewById(R.id.text_view_time);
        eStatus = findViewById(R.id.text_view_status);
        ebtnAccept = findViewById(R.id.btnAccept);
        ebtnRearrange = findViewById(R.id.btnRearrange);

        //Display value
        eAppointmentID.setText(getIntent().getStringExtra("appointmentID"));
        eDoctorName.setText(getIntent().getStringExtra("doctorName"));
        eDate.setText(getIntent().getStringExtra("Date"));
        eTime.setText(getIntent().getStringExtra("Time"));
        eStatus.setText(getIntent().getStringExtra("Status"));

        ebtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*databaseAppointment = FirebaseDatabase
                        .getInstance("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("Appointment");*/
                user= FirebaseAuth.getInstance().getCurrentUser();
                String useruid=getIntent().getStringExtra("appointmentID");
                databaseAppointment=FirebaseDatabase
                        .getInstance("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference().child("Appointment").child(useruid);
                databaseAppointment.child("appointmentStatus").setValue("Accept");
                startActivity(new Intent(AdminAcceptRearrangeAppointmentActivity.this,
                        AdminManageAppointmentActivity.class));
            }
        });

        ebtnRearrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user= FirebaseAuth.getInstance().getCurrentUser();
                String useruid=getIntent().getStringExtra("appointmentID");
                databaseAppointment=FirebaseDatabase
                        .getInstance("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference().child("Appointment").child(useruid);
                databaseAppointment.child("appointmentStatus").setValue("Rearrange");
                startActivity(new Intent(AdminAcceptRearrangeAppointmentActivity.this,
                        AdminManageAppointmentActivity.class));
            }
        });
    }

}