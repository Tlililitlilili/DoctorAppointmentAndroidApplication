package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Month;
import java.util.ArrayList;

public class NewAppointmentActivity extends AppCompatActivity {
    //we will use these constants later to pass the artist name and id to another activity
    public static final String APPOINTMENT_DATE = "com.example.doctorappointmentapp1.appointmentDate";
    public static final String APPOINTMENT__ID = "com.example.doctorappointmentapp1.appointmentID";

    private TextView eSelectADate, eSelectATime;
    private CalendarView edateCalendarView;
    private TextView etimeTextClock1,etimeTextClock2,etimeTextClock3,etimeTextClock4,etimeTextClock5,
    etimeTextClock6,etimeTextClock7,etimeTextClock8,etimeTextClock9,etimeTextClock10,
    etimeTextClock11,etimeTextClock12,etimeTextClock13,etimeTextClock14,etimeTextClock15;
    private Button ebtnConfirm;

    DatabaseReference databaseAppointment;
    long eid = 0;

    // recycleview
    ImageView eimageView;
    TextView eDoctorName, eDoctorSpeciality;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        eimageView = findViewById(R.id.imgview_doctor);
        eDoctorName = findViewById(R.id.txtview_doctorName);
        eDoctorSpeciality = findViewById(R.id.txtview_doctorSpeciality);
        /*doctorList = new ArrayList<>();
        adapter = new SearchDoctorAdapter(this,doctorList);
        mResultList.setAdapter(adapter);*/
//        eimageView.setImageResource(getIntent().getIntExtra("image name",  0));
        Glide.with(eimageView.getContext()).load(getIntent().getStringExtra("image name")).into(eimageView);
        eDoctorName.setText(getIntent().getStringExtra("doctorName"));
        eDoctorSpeciality.setText(getIntent().getStringExtra("doctorSpeciality"));
        username = getIntent().getStringExtra("pusername");

        databaseAppointment = FirebaseDatabase
                .getInstance("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Appointment");

        eSelectADate = findViewById(R.id.txtSelectADate);
        eSelectATime = findViewById(R.id.txtSelectATime);
        edateCalendarView = findViewById(R.id.dateCalendarView);
        etimeTextClock1 = findViewById(R.id.timeTextClock1);
        etimeTextClock2 = findViewById(R.id.timeTextClock2);
        etimeTextClock3 = findViewById(R.id.timeTextClock3);
        etimeTextClock4 = findViewById(R.id.timeTextClock4);
        etimeTextClock5 = findViewById(R.id.timeTextClock5);
        etimeTextClock6 = findViewById(R.id.timeTextClock6);
        etimeTextClock7 = findViewById(R.id.timeTextClock7);
        etimeTextClock8 = findViewById(R.id.timeTextClock8);
        etimeTextClock9 = findViewById(R.id.timeTextClock9);
        etimeTextClock10 = findViewById(R.id.timeTextClock10);
        etimeTextClock11 = findViewById(R.id.timeTextClock11);
        etimeTextClock12 = findViewById(R.id.timeTextClock12);
        etimeTextClock13 = findViewById(R.id.timeTextClock13);
        etimeTextClock14 = findViewById(R.id.timeTextClock14);
        etimeTextClock15 = findViewById(R.id.timeTextClock15);
        ebtnConfirm = findViewById(R.id.btnConfirm);

        //get Date
        edateCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                eSelectADate.setText(date);
            }
        });

        //get Time
        etimeTextClock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock1 = etimeTextClock1.getText();
                String time = txtTextClock1.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock2 = etimeTextClock2.getText();
                String time = txtTextClock2.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock3 = etimeTextClock3.getText();
                String time = txtTextClock3.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock4 = etimeTextClock4.getText();
                String time = txtTextClock4.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock5 = etimeTextClock5.getText();
                String time = txtTextClock5.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock6 = etimeTextClock6.getText();
                String time = txtTextClock6.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock7 = etimeTextClock7.getText();
                String time = txtTextClock7.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock8 = etimeTextClock8.getText();
                String time = txtTextClock8.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock9 = etimeTextClock9.getText();
                String time = txtTextClock9.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock10 = etimeTextClock10.getText();
                String time = txtTextClock10.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock11 = etimeTextClock11.getText();
                String time = txtTextClock11.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock12 = etimeTextClock12.getText();
                String time = txtTextClock12.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock13 = etimeTextClock13.getText();
                String time = txtTextClock13.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock14 = etimeTextClock14.getText();
                String time = txtTextClock14.toString();
                eSelectATime.setText(time);
            }
        });
        etimeTextClock15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence txtTextClock15 = etimeTextClock15.getText();
                String time = txtTextClock15.toString();
                eSelectATime.setText(time);
            }
        });

        //Connect appointment database
        databaseAppointment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    eid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Button event-Add Apppointment
        ebtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAppointment();
            }
        });
    }

    private void addAppointment() {
        //getting the values to save
        String doctorName = eDoctorName.getText().toString().trim();
        String date = eSelectADate.getText().toString().trim();
        String time = eSelectATime.getText().toString().trim();
        String pusername = username.toString().trim();



        //checking if the value is provided
        if (!TextUtils.isEmpty(doctorName) &&
                !date.equals("Select a date") && !TextUtils.isEmpty(date)
                && !time.equals("Select a time") && !TextUtils.isEmpty(time)){

            //it will create a unique id and we will use it as the Primary Key for Appointment
            String id = String.valueOf(eid+1);
            String appointmentStatus = "Pending";

            //creating an Artist Object
            Appointment appointment = new Appointment(id, pusername, date, time, doctorName, appointmentStatus);

            //Saving the Apointment
            databaseAppointment.child(id).setValue(appointment);

            //setting edittext to blank again
            eSelectADate.setText("");
            eSelectATime.setText("");

            //displaying a success toast
            Toast.makeText(this, "Appointment added", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(doctorName)){
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please select a doctor", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(date)){
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please select a date", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(time)){
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please select a time", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please select details", Toast.LENGTH_LONG).show();
        }
    }
}