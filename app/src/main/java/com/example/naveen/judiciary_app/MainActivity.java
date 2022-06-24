package com.example.naveen.judiciary_app;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button logout;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,registerActivity.class);
                startActivity(i);
            }
        },3000);
        progressBar.setVisibility(View.VISIBLE);

        mAuth = FirebaseAuth.getInstance();

            currentUser = mAuth.getCurrentUser();
            logout = (Button)findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    //After logging out send user to Login Activity to login again
                    sendToLoginActivity();
                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            if(currentUser==null)
            {
                sendToLoginActivity();
            }
        }

        private void sendToLoginActivity() {

            Intent loginIntent = new Intent(MainActivity.this,loginActivity.class);
            startActivity(loginIntent);
        }

    }


