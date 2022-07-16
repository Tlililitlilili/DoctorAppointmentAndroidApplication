package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHomePageActivity extends AppCompatActivity implements View.OnClickListener{

    private Button eManageAppointment, eAddDoctor, eLogout;
    private Button eHome, eMyAppointment, eMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        eManageAppointment = findViewById(R.id.btnManage);
        eAddDoctor = findViewById(R.id.btnAddDoctor);
        eLogout = findViewById(R.id.btnLogout);
//        eHome = findViewById(R.id.btnHome);
//        eMyAppointment = findViewById(R.id.btnMyAppointment);
//        eMe = findViewById(R.id.btnMe);

        /* A listener for click events on the Button */
        eManageAppointment.setOnClickListener(this);
        eAddDoctor.setOnClickListener(this);
        eLogout.setOnClickListener(this);
//        eHome.setOnClickListener(this);
//        eMyAppointment.setOnClickListener(this);
//        eMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Intent i = getIntent();
//        String username = i.getStringExtra(RegistrationActivity.EXTRA_TEXT);
//        String MobileNo = i.getStringExtra(RegistrationActivity.EXTRA_TEXTmobileNo);
//        String Email = i.getStringExtra(RegistrationActivity.EXTRA_TEXTemail);

        switch (v.getId()) {
            case R.id.btnManage:
                /*Intent a = new Intent(AdminHomePageActivity.this, AdminManageAppointmentActivity.class);
//                a.putExtra("pusername",username);
                startActivity(a);*/
                startActivity(new Intent(AdminHomePageActivity.this, AdminManageAppointmentActivity.class));
                break;

            case R.id.btnAddDoctor:
                startActivity(new Intent(AdminHomePageActivity.this, AdminAddDoctorActivity.class));
                break;

            case R.id.btnLogout:
                startActivity(new Intent(AdminHomePageActivity.this, MainActivity.class));
                break;

            /*case R.id.btnHome:
                startActivity(new Intent(AdminHomePageActivity.this, HomePageActivity.class));
                break;

            case R.id.btnMyAppointment:
                Intent b = new Intent(AdminHomePageActivity.this, MyAppointment.class);
//                b.putExtra("pusername",username);
                startActivity(b);
                break;

            case R.id.btnMe:
                Intent c = new Intent(AdminHomePageActivity.this, Profile.class);
//                c.putExtra("pusername",username);
//                c.putExtra("pMobileNo", MobileNo);
//                c.putExtra("pEmail", Email);
                startActivity(c);
                break;*/
        }
    }
}