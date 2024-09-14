package com.example.correctvoice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myref;
    String uname , uemail , password , conpassword;
    EditText name , email , pass, conpass;
    Button reg , log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        conpass = (EditText) findViewById(R.id.conpass);
        reg = (Button) findViewById(R.id.regbutton);
        log = (Button) findViewById(R.id.signinButton);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = name.getText().toString().trim();
                uemail = email.getText().toString().trim();
                password = pass.getText().toString().trim();
                conpassword = conpass.getText().toString().trim();
                if(uname.isEmpty() || uemail.isEmpty() || password.isEmpty() || conpassword.isEmpty() )
                {
                    Toast.makeText(Register.this , "Field is empty!!!" , Toast.LENGTH_SHORT).show();
                }
                else if(password.length() < 6)
                {
                    Toast.makeText(Register.this , "Password must be of length six!!!!" , Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(conpassword)){
                    createAccount(uemail , password);
                }
                else{
                    Toast.makeText(Register.this , "Password and Confirm Password is not matching!!!!" , Toast.LENGTH_SHORT).show();
                    pass.setText("");
                    conpass.setText("");
                }
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this , Login.class));
            }
        });
    }

    private void createAccount(String email1, String word) {
        auth.createUserWithEmailAndPassword(email1 , word).addOnCompleteListener(Register.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(uname)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("SignUp", "User profile updated.");
                                                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                    saveUserData();
                                                    startActivity(new Intent(Register.this , Home.class));
                                                }
                                            }
                                        });
                            }
                        }
                        else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Account creation failed";
                            Toast.makeText(Register.this, "Account creation failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                            name.setText("");
                            email.setText("");
                            pass.setText("");
                            conpass.setText("");
                        }
                    }
                });
    }

    private void saveUserData() {

        UserModel um = new UserModel(uname ,uemail, password);

        myref.child("users").child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).setValue(um)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Data stored successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, "Data store failed!", Toast.LENGTH_SHORT).show();
                            name.setText("");
                            email.setText("");
                            pass.setText("");
                            conpass.setText("");
                        }
                    }
                });
    }
}