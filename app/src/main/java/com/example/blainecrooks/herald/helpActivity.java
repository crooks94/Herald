package com.example.blainecrooks.herald;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class helpActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;

    private Button logoutButton;
    private ImageButton reportButton;
    private ImageButton printerButton;
    private ImageButton wifiButton;
    private ImageButton passwordButton;
    private ImageButton projectorButton;

    EditText roomNumberPrinter;
    EditText roomNumberWifi;
    EditText roomNumberPassword;
    EditText roomNumberProjector;


//    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        databaseReference = FirebaseDatabase.getInstance().getReference("hotKeyRequest");

        //set printer alert dialogue
        final AlertDialog.Builder printer = new AlertDialog.Builder(this);
        printer.setTitle("Room Number");
        printer.setIcon(R.drawable.logo);
        printer.setMessage("Please Enter Your Room Number");


        //set wifi alert dialogue
        AlertDialog.Builder wifi = new AlertDialog.Builder(this);
        wifi.setTitle("Room Number");
        wifi.setIcon(R.drawable.logo);
        wifi.setMessage("Please Enter Your Room Number");

        //set password alert dialogue
        AlertDialog.Builder password = new AlertDialog.Builder(this);
        password.setTitle("Room Number");
        password.setIcon(R.drawable.logo);
        password.setMessage("Please Enter Your Room Number");

        //set projector alert dialogue
        AlertDialog.Builder projector = new AlertDialog.Builder(this);
        projector.setTitle("Room Number");
        projector.setIcon(R.drawable.logo);
        projector.setMessage("Please Enter Your Room Number");





        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this, MainActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userEmail = user.getEmail();

        //instantiate elements
        logoutButton = (Button) findViewById(R.id.logoutButton);
        reportButton = (ImageButton) findViewById(R.id.reportButton);
        printerButton = (ImageButton) findViewById(R.id.printerButton);
        wifiButton = (ImageButton) findViewById(R.id.wifiButton);
        passwordButton = (ImageButton) findViewById(R.id.passwordButton);
        projectorButton = (ImageButton) findViewById(R.id.projectorButton);

        //Create room number edittext for dialog box
        roomNumberPrinter = new EditText(this);
        roomNumberWifi = new EditText(this);
        roomNumberPassword = new EditText(this);
        roomNumberProjector = new EditText(this);

        //add roomNumber to dialog boxes
        printer.setView(roomNumberPrinter);
        wifi.setView(roomNumberWifi);
        password.setView(roomNumberPassword);
        projector.setView(roomNumberProjector);


        //Set Printer Dialog Box Options

        //Set Printer PositiveButton
        printer.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String room = roomNumberPrinter.getText().toString().trim();
                String request = "Printer Error";
                String name = userEmail;


                //Create Randomised Ticket Number
                String ticketNumber = databaseReference.push().getKey();

                //Create New Request
                Request requests = new Request(ticketNumber, room, request, name);

                //Store data into database by ticketNumber

                databaseReference.child(ticketNumber).child("Name").setValue(name);
                databaseReference.child(ticketNumber).child("Request").setValue(request);
                databaseReference.child(ticketNumber).child("Room").setValue(room);


                dialog.dismiss();

                Toast.makeText(getApplicationContext(), "Request Made", Toast.LENGTH_LONG).show();

            }
        });

        //Set NegativeButton
        printer.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //----------------------------------------------------------------------------
        //----------------------------------------------------------------------------

        //Set Wifi Dialog Box Options

        //Set Wifi PositiveButton
        wifi.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String room = roomNumberWifi.getText().toString().trim();
                String request = "Wifi Error";
                String name = userEmail;


                //Create Randomised Ticket Number
                String ticketNumber = databaseReference.push().getKey();

                //Create New Request
                Request requests = new Request(ticketNumber, room, request, name);

                //Store data into database by ticketNumber

                databaseReference.child(ticketNumber).child("Name").setValue(name);
                databaseReference.child(ticketNumber).child("Request").setValue(request);
                databaseReference.child(ticketNumber).child("Room").setValue(room);


                dialog.dismiss();

                Toast.makeText(getApplicationContext(), "Request Made", Toast.LENGTH_LONG).show();

            }
        });

        //Set NegativeButton
        wifi.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //----------------------------------------------------------------------------
        //----------------------------------------------------------------------------

        //Set Password Dialog Box Options

        //Set Password PositiveButton
        password.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String room = roomNumberPassword.getText().toString().trim();
                String request = "Password Change";
                String name = userEmail;


                //Create Randomised Ticket Number
                String ticketNumber = databaseReference.push().getKey();

                //Create New Request
                Request requests = new Request(ticketNumber, room, request, name);

                //Store data into database by ticketNumber

                databaseReference.child(ticketNumber).child("Name").setValue(name);
                databaseReference.child(ticketNumber).child("Request").setValue(request);
                databaseReference.child(ticketNumber).child("Room").setValue(room);


                dialog.dismiss();


                Toast.makeText(getApplicationContext(), "Request Made", Toast.LENGTH_LONG).show();

            }
        });

        //Set NegativeButton
        password.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //----------------------------------------------------------------------------
        //----------------------------------------------------------------------------

        //Set Projector Dialog Box Options

        //Set Projector PositiveButton
        projector.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String room = roomNumberProjector.getText().toString().trim();
                String request = "Projector Error";
                String name = userEmail;


                //Create Randomised Ticket Number
                String ticketNumber = databaseReference.push().getKey();

                //Create New Request
                Request requests = new Request(ticketNumber, room, request, name);

                //Store data into database by ticketNumber

                databaseReference.child(ticketNumber).child("Name").setValue(name);
                databaseReference.child(ticketNumber).child("Request").setValue(request);
                databaseReference.child(ticketNumber).child("Room").setValue(room);


                dialog.dismiss();

                Toast.makeText(getApplicationContext(), "Request Made", Toast.LENGTH_LONG).show();

            }
        });

        //Set NegativeButton
        projector.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //----------------------------------------------------------------------------
        //----------------------------------------------------------------------------

        //Create Dialog
        final AlertDialog printerDialog = printer.create();
        final AlertDialog wifiDialog = wifi.create();
        final AlertDialog passwordDialog = password.create();
        final AlertDialog projectorDialog = projector.create();




        //OnClick Listeners

        //Log out Listener
        logoutButton.setOnClickListener(this);

        //printer Listener
        printerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            printerDialog.show();



            }
        });

        //wifi listener
        wifiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiDialog.show();
            }
        });

        //password listener
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordDialog.show();
            }
        });

        //projector listener
        projectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectorDialog.show();
            }
        });



    }

    @Override
    public void onClick(View view) {
        if (view == logoutButton)
        {
            firebaseAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        }

//        if (view == reportButton)
//        {
//
//            startActivity(new Intent(this, requestActivity.class));
//        }

    }

    public void reportClick(View v){
        startActivity(new Intent(this, requestActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}


