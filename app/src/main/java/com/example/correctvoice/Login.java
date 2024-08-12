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

public class Login extends AppCompatActivity {

    Button login;
    EditText email , pass;
    TextView reg;
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
        login = (Button) findViewById(R.id.reguser);
        email = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.email);
        reg = (TextView) findViewById(R.id.logn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailstr = email.getText().toString().trim();
                String passstr = pass.getText().toString().trim();
                if(emailstr.isEmpty() || passstr.isEmpty()){
                    Toast.makeText(Login.this , "Field is empty!!" , Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean auth = false;
                    try {
                        auth = new DbHandler(Login.this).search(emailstr , passstr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(auth){
                        startActivity(new Intent(Login.this , Home.class));
                        Toast.makeText(Login.this , "Login successfull!!" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login.this , "Invalid email or password" , Toast.LENGTH_SHORT).show();
                        email.setText("");
                        pass.setText("");
                    }
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
}