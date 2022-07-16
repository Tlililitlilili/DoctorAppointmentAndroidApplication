package com.example.doctorappointmentapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.button.MaterialButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    /* Define the UI elements */
    private TextView eName;
    private TextView ePassword;
    private MaterialButton eLogin;
    private TextView eRegister;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    String userName = "";
    String userPassword = "";

    /* Class to hold credentials */
    /* Get an object of Credentials Class */
    Credentials credentials = new Credentials("admin@gmail.com", "admin");

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, HomePageActivity.class));
        }

        eLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /* Obtain the inputs from the fields */
                String registeredName = eName.getText().toString().trim();;
                String registeredPassword = ePassword.getText().toString().trim();;

                if(TextUtils.isEmpty(registeredName) && TextUtils.isEmpty(registeredPassword)) {
                    eName.setError("Email is Required.");
                    ePassword.setError("Password is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(registeredName)) {
                    eName.setError("Email is Required.");
                    return;
                }
                else if(TextUtils.isEmpty(registeredPassword)){
                    ePassword.setError("Password is Required.");
                    return;
                }
                if (registeredName.equals(credentials.Username) && registeredPassword.equals(credentials.Userpassword)){
                    startActivity(new Intent(MainActivity.this, AdminHomePageActivity.class));
                } else{
                    validate(eName.getText().toString(), ePassword.getText().toString());
                }
            }
        });

        /* Set listener on SignUp textview */
        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }

    /* Bind the XML views to Java Code Elements */
    private void setupUIViews(){
        eName = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        eLogin = findViewById(R.id.btnLogin);
        eRegister = findViewById(R.id.txtNewUser);
    }

    /* Validate the credentials */
    private void validate(String uName, String uPassword)
    {
        /* Check the credentials *//*
        if(userName.equals(credentials.Username) && userPassword.equals(credentials.Userpassword))
        {
            return true;
        }

        return false;*/
        progressDialog.setMessage("Loading");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(uName, uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(MainActivity.this, HomePageActivity.class));

//        if(emailflag){
//            finish();
//            startActivity(new Intent(MainActivity.this, HomePageActivity.class));
//        }else{
//            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
//            firebaseAuth.signOut();
//        }
    }
}
