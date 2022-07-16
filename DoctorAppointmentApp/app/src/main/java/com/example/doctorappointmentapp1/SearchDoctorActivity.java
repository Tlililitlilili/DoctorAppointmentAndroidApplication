package com.example.doctorappointmentapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchDoctorActivity extends AppCompatActivity {

    private TextView eSearchDoctor;
    private Button mSearchButton;
    private RecyclerView mResultList;
    private SearchView mSearchField;

    private SearchDoctorAdapter adapter;
    private List<Doctor> doctorList;
    private List<String> userList;
    DatabaseReference dbDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);

        mSearchField = findViewById(R.id.search_view);
        mSearchField.clearFocus();
        eSearchDoctor = findViewById(R.id.txtSearch);
        mSearchButton = findViewById(R.id.btnSearch);
        mResultList = findViewById(R.id.resultSearch);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        doctorList = new ArrayList<>();
        userList = new ArrayList<>();
        adapter = new SearchDoctorAdapter(this,doctorList,userList);
        mResultList.setAdapter(adapter);

        mSearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String serachText = newText.toUpperCase();
                Query query = FirebaseDatabase.getInstance
                        ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("Doctor")
                        .orderByChild("doctorName")
                        .startAt("DR "+ serachText)
                        .endAt("DR "+serachText+"\uf8ff");
                query.addListenerForSingleValueEvent(valueEventListener);
                closeKeyboard();
                return true;
            }
        });

        //1. SELECT * FROM Doctor
        dbDoctor = FirebaseDatabase.getInstance
                ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Doctor");
        dbDoctor.addListenerForSingleValueEvent(valueEventListener);

                /*String serachText = mSearchField.getText().toString();

        mSearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Query query = FirebaseDatabase.getInstance
                        ("https://doctorappointmentapp1-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference("Doctor")
                        .orderByChild("doctorName")
                        .startAt(newText)
                        .endAt("\uf8ff");
                query.addListenerForSingleValueEvent(valueEventListener);
                return true;
            }
        });*/

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Intent i = getIntent();
            String username = i.getStringExtra("pusername");
            doctorList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor doctor = snapshot.getValue(Doctor.class);
                    doctorList.add(doctor);
                }
                userList.add(username);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void  closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view!=null){
            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(mSearchField.getWindowToken(), 0);
        }
    }
}