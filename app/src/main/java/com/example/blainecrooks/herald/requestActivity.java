package com.example.blainecrooks.herald;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class requestActivity extends AppCompatActivity {

    //instantiate elements
    private EditText detailsText;
    private EditText roomNumber;
    private EditText nameText;
    private Button submitButton;

    DatabaseReference databaseRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        databaseRequest = FirebaseDatabase.getInstance().getReference("userRequest");

        //set up elements
        detailsText = (EditText) findViewById(R.id.detailsText);
        roomNumber = (EditText) findViewById(R.id.roomNumber);
        nameText = (EditText) findViewById(R.id.nameText);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRequest();

            }
        });



    }

    //store user request to DB
    private void submitRequest(){

        //get text from EditTexts
        String name = nameText.getText().toString().trim();
        String room = roomNumber.getText().toString().trim();
        String request = detailsText.getText().toString().trim();

        if(!TextUtils.isEmpty(request)){

            //create randomised ticket number
            String ticketNumber = databaseRequest.push().getKey();

            //Create new request
            Request requests = new Request(ticketNumber, room, request, name);

            //Store data into database by ticketNumber
            databaseRequest.child(ticketNumber).child("Name").setValue(name);
            databaseRequest.child(ticketNumber).child("Request").setValue(request);
            databaseRequest.child(ticketNumber).child("Room").setValue(room);

            //Confirmation that request has been sent
            Toast.makeText(this, "Request Made", Toast.LENGTH_LONG).show();

            nameText.setText("");
            roomNumber.setText("");
            detailsText.setText("");

        }else {
            Toast.makeText(this, "Enter Your Request Information", Toast.LENGTH_LONG).show();
        }

//

    }


}
