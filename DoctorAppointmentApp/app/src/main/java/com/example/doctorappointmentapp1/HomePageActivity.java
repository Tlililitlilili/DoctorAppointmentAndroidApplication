package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{

    private Button eSearch, eContactUs, eMyAppointment, eMe, eHome, eLogout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        eSearch = findViewById(R.id.btnSearch);
        eContactUs = findViewById(R.id.btnContactUs);
        eHome = findViewById(R.id.btnHome);
        eMyAppointment = findViewById(R.id.btnMyAppointment);
        eMe = findViewById(R.id.btnMe);
        eLogout = findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();

        /* A listener for click events on the Button */
        eSearch.setOnClickListener(this);
        eContactUs.setOnClickListener(this);
        eMyAppointment.setOnClickListener(this);
        eHome.setOnClickListener(this);
        eMe.setOnClickListener(this);
        eLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = getIntent();
        String username = i.getStringExtra(RegistrationActivity.EXTRA_TEXT);
        String MobileNo = i.getStringExtra(RegistrationActivity.EXTRA_TEXTmobileNo);
        String Email = i.getStringExtra(RegistrationActivity.EXTRA_TEXTemail);

        switch (v.getId()) {
            case R.id.btnSearch:
                Intent a =new Intent(HomePageActivity.this, SearchDoctorActivity.class);
                a.putExtra("pusername",username);
                startActivity(a);
                break;

            case R.id.btnContactUs:
                startActivity(new Intent(HomePageActivity.this, NewAppointmentActivity.class));
                break;

            case R.id.btnHome:
                startActivity(new Intent(HomePageActivity.this, HomePageActivity.class));
                break;

            case R.id.btnMyAppointment:
                Intent b =new Intent(HomePageActivity.this, MyAppointment.class);
                b.putExtra("pusername",username);
                startActivity(b);
                break;

            case R.id.btnMe:
                Intent c=new Intent(HomePageActivity.this, Profile.class);
                c.putExtra("pusername",username);
                c.putExtra("pMobileNo", MobileNo);
                c.putExtra("pEmail", Email);
                startActivity(c);
                break;

            case R.id.btnLogout:
                firebaseAuth.signOut();
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
                break;
        }

        /*eSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(HomePageActivity.this, SearchDoctorActivity.class));
                Intent intent=new Intent(HomePageActivity.this, SearchDoctorActivity.class);
                intent.putExtra("pusername",username);
                startActivity(intent);
            }
        });

        *//* A listener for click events on the Register Button *//*
        eNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, NewAppointmentActivity.class));
            }
        });

        eMyAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(HomePageActivity.this, MyAppointment.class));
                Intent intent=new Intent(HomePageActivity.this, MyAppointment.class);
                intent.putExtra("pusername",username);
                startActivity(intent);
            }
        });

        eHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, HomePageActivity.class));
            }
        });

        eMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(HomePageActivity.this, Profile.class));
                Intent intent=new Intent(HomePageActivity.this, Profile.class);
                intent.putExtra("pusername",username);
                intent.putExtra("pMobileNo", MobileNo);
                intent.putExtra("pEmail", Email);
                startActivity(intent);
            }
        });*/
    }
}