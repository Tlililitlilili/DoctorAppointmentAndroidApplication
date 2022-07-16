package com.example.doctorappointmentapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegistrationActivity extends AppCompatActivity {

    /* Define the UI elements */
    private EditText eRegName, eRegPassword, eRegMobileNo, eRegEmail;
    private Button eRegister;
    private TextView eLogin;
    private FirebaseAuth firebaseAuth;
    String email, name, mobileNo, password;
    DatabaseReference databaseUser;
    long uid = 0;
    long pid = 0;
    public String pusername, pMobileNo, pEmail;
    public static final String EXTRA_TEXT = "com.example.doctorappointmentapp1.EXTRA_TEXT";
    public static final String EXTRA_TEXTmobileNo = "com.example.doctorappointmentapp1.EXTRA_TEXTmobileNo";
    public static final String EXTRA_TEXTemail = "com.example.doctorappointmentapp1.EXTRA_TEXTemail";

    /* Create an object of the class Credentials */
    Credentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        //Connect user database
        databaseUser = FirebaseDatabase
                .getInstance("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("User");
        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    uid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Firebase Authencation
        firebaseAuth = FirebaseAuth.getInstance();

        /* A listener for click events on the Register Button */
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Obtain the inputs from the fields */
                String registeredName = eRegName.getText().toString().trim();;
                String registeredPassword = eRegPassword.getText().toString().trim();;
                String registeredEmail = eRegEmail.getText().toString().trim();
                String registeredMobileNo = eRegMobileNo.getText().toString().trim();

                isEmpty();  //Check input

                /* Check if the fields are valid */
                if(validate())
                {
                    /* Upload data to database *//*

                    *//* Add the credentials into our database *//*
                    credentials = new Credentials(registeredName, registeredPassword);
                    Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    *//* Go to Login Activity *//*
                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));*/

                    firebaseAuth.createUserWithEmailAndPassword(registeredEmail, registeredPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                Toast.makeText(RegistrationActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                addUser();  //Add User to database
//                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                                Intent intent=new Intent(RegistrationActivity.this, HomePageActivity.class);
                                intent.putExtra(EXTRA_TEXT, pusername);
                                intent.putExtra(EXTRA_TEXTmobileNo, pMobileNo);
                                intent.putExtra(EXTRA_TEXTemail, pEmail);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        /* Set listener on SignUp textview */
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    private void isEmpty(){
        // Obtain the inputs from the fields
        String registeredName = eRegName.getText().toString().trim();;
        String registeredPassword = eRegPassword.getText().toString().trim();;
        String registeredEmail = eRegEmail.getText().toString().trim();
        String registeredMobileNo = eRegMobileNo.getText().toString().trim();

        if(TextUtils.isEmpty(registeredName) && TextUtils.isEmpty(registeredPassword)
                && TextUtils.isEmpty(registeredEmail) && TextUtils.isEmpty(registeredMobileNo)) {
            eRegName.setError("Name is Required.");
            eRegPassword.setError("Password is Required.");
            eRegEmail.setError("Email is Required.");
            eRegMobileNo.setError("Mobile Num is Required.");
            return;
        }
        else if(TextUtils.isEmpty(registeredName)){
            eRegName.setError("Name is Required.");
            return;
        }
        else if(TextUtils.isEmpty(registeredPassword)){
            eRegPassword.setError("Password is Required.");
            return;
        }
        else if(TextUtils.isEmpty(registeredEmail)){
            eRegEmail.setError("Email is Required.");
            return;
        }
        else if(TextUtils.isEmpty(registeredMobileNo)){
            eRegMobileNo.setError("Mobile Num is Required.");
            return;
        }

        if(registeredPassword.length() < 6){
            eRegPassword.setError("Password Must be >= 6 Characters.");
            return;
        }
    }
    /* Bind the XML views to Java Code Elements */
    private void setupUIViews(){
        eRegName = findViewById(R.id.txtUsername);
        eRegPassword = findViewById(R.id.txtPassword);
        eRegMobileNo = findViewById(R.id.txtMobileNo);
        eRegEmail = findViewById(R.id.txtEmail);
        eRegister = findViewById(R.id.btnSignUp);
        eLogin = findViewById(R.id.txtLogin);
    }

    /* Function for validating the input credentials from the user */
    private Boolean validate(){
        Boolean result = false;

        name = eRegName.getText().toString();
        password = eRegPassword.getText().toString();
        email = eRegEmail.getText().toString();
        mobileNo = eRegMobileNo.getText().toString();

        /*if(name.isEmpty() || password.isEmpty() || email.isEmpty() || mobileNo.isEmpty() ){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else */
        if (TextUtils.isEmpty(name)){
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)){
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(email)){
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(mobileNo)){
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter mobile num", Toast.LENGTH_LONG).show();
        }else{
            result = true;
        }
        return result;
    }

    private void addUser() {
        // Obtain the inputs from the fields
        String registeredName = eRegName.getText().toString().trim();;
        String registeredPassword = eRegPassword.getText().toString().trim();;
        String registeredEmail = eRegEmail.getText().toString().trim();
        String registeredMobileNo = eRegMobileNo.getText().toString().trim();

        //it will create a unique id and we will use it as the Primary Key for Appointment
        String id = String.valueOf(uid+1);

        //creating an Artist Object
        User user = new User(id, registeredName, registeredPassword, registeredEmail, registeredMobileNo);

        //Saving the Apointment
        databaseUser.child(id).setValue(user);

        //displaying a success toast
        Toast.makeText(this, "Register successful", Toast.LENGTH_LONG).show();

        //it will create a unique id and we will use it as the Primary Key for Patient
        String pId = String.valueOf(pid+1);

        //creating an Artist Object
        Patient patient = new Patient(pId, registeredName, registeredEmail, registeredMobileNo);

        //Saving the Apointment
        databaseUser.child(id).setValue(patient);
        pusername = registeredName;
        pMobileNo = registeredMobileNo;
        pEmail = registeredEmail;
    }
    /* boolean validate(String name, String password)
    {
        *//* Check if the name is empty and password field is 8 characters long *//*
        if(name.isEmpty() || (password.length() < 8))
        {
            Toast.makeText(this, "Please enter your name and ensure password is more than 8 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }*/
}