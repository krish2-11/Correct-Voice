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

public class Register extends AppCompatActivity {

    EditText name , email , pass, conpass;
    Button reg;
    TextView log;
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
        reg = (Button) findViewById(R.id.reguser);
        log = (TextView) findViewById(R.id.logn);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString().trim();
                String email1 = email.getText().toString().trim();
                String pass1 = pass.getText().toString().trim();
                String conpass1 = conpass.getText().toString().trim();
                if(name1.isEmpty() || email1.isEmpty() || pass1.isEmpty()){
                    Toast.makeText(Register.this , "Field is empty!!" , Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!pass1.equals(conpass1)){
                        Toast.makeText(Register.this , "Password and confirm password must be same!!!",Toast.LENGTH_SHORT).show();
                        pass.setText("");
                        conpass.setText("");
                    }
                    else {
                        DbHandler dbh = new DbHandler(Register.this);
                        boolean auth = dbh.searchemail(email1);
                        if(auth){
                            Toast.makeText(Register.this , "Email is already registered!!" , Toast.LENGTH_SHORT).show();
                            email.setText("");
                            pass.setText("");
                            conpass.setText("");
                        }
                        else{
                            dbh.insertUserDetails(name1 , email1 , pass1);
                            startActivity(new Intent (Register.this , Home.class));
                        }
                    }
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
}