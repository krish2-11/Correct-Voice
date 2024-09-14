package com.example.correctvoice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    TabLayout tabs;
    ViewPager2 vp2;
    MyViewPagerAdapter mvpa;
    TextView tv;
    ImageView user_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tabs = findViewById(R.id.tabs);
        vp2 = findViewById(R.id.viewpager);
        mvpa = new MyViewPagerAdapter(this);
        vp2.setAdapter(mvpa);
        user_icon = (ImageView)findViewById(R.id.user_icon);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        if (user != null) {
            String uid = user.getUid();
                DatabaseReference userRef = firebaseDatabase.getReference("users").child(uid);

                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user data from the snapshot
                        UserModel user = dataSnapshot.getValue(UserModel.class);
                        if (user != null) {
                            // Handle user data
                            Toast.makeText(getApplicationContext(), "User Name: " + user.getName() , Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("UserData", "No user data found");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull  DatabaseError databaseError) {
                        Log.e("UserData", "Failed to read user data", databaseError.toException());
                    }
                });

//            String name = user.getDisplayName();
//            Toast.makeText(this, "User Name: " + name , Toast.LENGTH_LONG).show();
        }


        user_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this , UserProfile.class));
            }
        });
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabs.getTabAt(position).select();
            }
        });
    }
}