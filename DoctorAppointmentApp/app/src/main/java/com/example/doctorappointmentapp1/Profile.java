package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Profile extends AppCompatActivity {

    private Button eMyAppointment, eMe, eHome;
    TextView pUsername, pICorPassport, pNationality, pGender, pDOB, pMobileNo, pEmail;
//    private ProfileAdapter adapter;
//    private List<Patient> patientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        eHome = findViewById(R.id.btnHome);
        eMyAppointment = findViewById(R.id.btnMyAppointment);
        eMe = findViewById(R.id.btnMe);

        pUsername = findViewById(R.id.txtUsername);
        pICorPassport = findViewById(R.id.txtICorPassport);
        pNationality = findViewById(R.id.txtNationality);
        pGender = findViewById(R.id.txtGender);
        pDOB = findViewById(R.id.txtDOB);
        pMobileNo = findViewById(R.id.txtMobileNo);
        pEmail = findViewById(R.id.txtEmail);

//        String username = profile1.pusername;
        Intent intent = getIntent();
        String username = intent.getStringExtra("pusername");
        String ICorPassport = " ";
        String Nationality = " ";
        String Gender = " ";
        String DOB = " ";
        String MobileNo = intent.getStringExtra("pMobileNo");
        String Email = intent.getStringExtra("pEmail");
        pUsername.setText("Name     : " + username);
        pICorPassport.setText("ICorPassport: " + ICorPassport);
        pNationality.setText("Nationality: " + Nationality);
        pGender.setText("Gender      : " + Gender);
        pDOB.setText("DOB           : " + DOB);
        pMobileNo.setText("Mobile No.: " + MobileNo);
        pEmail.setText("Email         : " + Email);
        Query query = FirebaseDatabase.getInstance
                ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Patient")
                .orderByChild("patientName")
                .equalTo(username);
//        query.addListenerForSingleValueEvent(valueEventListener);

        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, HomePageActivity.class));
            }
        });

        eMyAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MyAppointment.class));
            }
        });

        eMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this, Profile.class);
                startActivity(intent);
            }
        });

    }


    /*ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            patientList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Patient patient = snapshot.getValue(Patient.class);
                    patientList.add(patient);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };*/
}