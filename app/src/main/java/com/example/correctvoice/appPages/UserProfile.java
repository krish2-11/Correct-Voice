package com.example.correctvoice.appPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.correctvoice.R;
import com.example.correctvoice.database.UserData;
import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {

    EditText name , email , phone ;
    RadioGroup rg;
    Button logOut , submit;
    String sgender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        logOut = findViewById(R.id.logout);
        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        rg = findViewById(R.id.genderGroup);
        sgender = "";
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }

            private void logout() {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the selected radio button by its ID
                RadioButton selectedRadioButton = findViewById(checkedId);
                sgender = selectedRadioButton.getText().toString();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname = name.getText().toString().trim();
                String semail = email.getText().toString().trim();
                String sphone = phone.getText().toString().trim();
                if(sname.isEmpty() || sphone.isEmpty() || semail.isEmpty() || sgender.isEmpty()){
                    Toast.makeText(UserProfile.this , "Field is empty" , Toast.LENGTH_SHORT).show();
                }
                else{
                    UserData udb = new UserData(UserProfile.this);
                    udb.insertUserDetails(sname , semail , sphone ,sgender);
                    Toast.makeText(UserProfile.this , "Data Inserted!" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button press
        onBackPressed();
        return true;
    }
}