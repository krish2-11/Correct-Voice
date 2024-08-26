package com.example.correctvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    Button login , reg;
    EditText email , pass;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        login = (Button) findViewById(R.id.loginButton);
        email = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        reg = (Button) findViewById(R.id.signupButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailstr = email.getText().toString().trim();
                String passstr = pass.getText().toString().trim();
                if (emailstr.isEmpty() || passstr.isEmpty()) {
                    Toast.makeText(Login.this, "Field is empty!!", Toast.LENGTH_SHORT).show();
                } else {
                    signInAccount(emailstr , passstr);
                }

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this , Register.class));
            }
        });

    }
    private void signInAccount(String uemail , String upass) {
        auth.signInWithEmailAndPassword(uemail , upass).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                FirebaseUser user = auth.getCurrentUser();
                Toast.makeText(Login.this , "Login Successful!" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this , Home.class));
            }
            else{
                Toast.makeText(Login.this , "Login failed!" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}