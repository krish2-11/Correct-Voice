package com.example.correctvoice.appPages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.correctvoice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Login extends AppCompatActivity {
    Button login , reg;
    EditText email , pass;
    private FirebaseAuth auth;
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
                    MyAsyncTask myasynctask = new MyAsyncTask();
                    myasynctask.execute(emailstr , passstr);
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
                if (user != null) {

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(email.getText().toString().trim())
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("SignUp", "User profile updated.");
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this , Home.class));
                                    }
                                }
                            });
                }
            }
            else{
                pass.setText("");
                Toast.makeText(Login.this , "Login failed!" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<String , String , String> {

        ProgressDialog progressDialog;
        @Override
        protected void onProgressUpdate(String... strings){
            progressDialog = ProgressDialog.show(Login.this , "Login" , "Signing in...");
        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Login");
            signInAccount(strings[0] , strings[1]);
            return "Successfull";
        }

        @Override
        protected void onPostExecute(String string){
            progressDialog.dismiss();
        }
    }
}