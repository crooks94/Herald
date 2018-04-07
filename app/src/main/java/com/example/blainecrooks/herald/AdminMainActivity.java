package com.example.blainecrooks.herald;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class AdminMainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button adminLogout;
    private ImageButton adminButton;
    private ImageButton adminRequest;
    private ImageButton adminOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        adminLogout = (Button) findViewById(R.id.adminLogOut);
        adminButton = (ImageButton) findViewById(R.id. adminButton);
        adminRequest = (ImageButton) findViewById(R.id.adminRequest);
        adminOrder = (ImageButton) findViewById(R.id.adminOrder);

        firebaseAuth = FirebaseAuth.getInstance();

        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == adminLogout)
                {
                    firebaseAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });

        adminRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            }
        });

        adminOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OrderActivity.class));
            }
        });





    }

//    @Override
//    public void onClick(View view) {
//        if (view == adminLogout)
//        {
//            firebaseAuth.signOut();
//            startActivity(new Intent(this, MainActivity.class));
//        }

//        if (view == reportButton)
//        {
//
//            startActivity(new Intent(this, requestActivity.class));
//        }

    }





