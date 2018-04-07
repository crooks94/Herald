package com.example.blainecrooks.herald;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {

    //instantiate elements
    private EditText orderDetails;
    private EditText quantity;
    private Button submitOrder;
    private Button viewOrder;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseRequest;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up DB connection
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();
        databaseRequest = mFirebaseDatabase.getInstance().getReference("Orders");

        //Set up elements
        orderDetails = (EditText) findViewById(R.id.orderDetails);
        quantity = (EditText) findViewById(R.id.quantity);
        submitOrder = (Button) findViewById(R.id.submitOrder);
        viewOrder = (Button) findViewById(R.id.viewOrder);

        submitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();
            }
        });

//        viewOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(OrderActivity.this, ViewOrders.class));
//
//            }
//        });
        viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewOrders.class));
            }
        });


    }

    //Store order Details to DB
    private void submitOrder(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userEmail = user.getEmail();

        //Get text from EditTexts
        String name = userEmail;
        String order = orderDetails.getText().toString().trim();
        String orderQuantity = quantity.getText().toString().trim();

        if (!TextUtils.isEmpty(order)){

            //create order store number
            String orderNumber = databaseRequest.push().getKey();

            //Store data to DB
            databaseRequest.child(orderNumber).child("Name").setValue(name);
            databaseRequest.child(orderNumber).child("Order Details").setValue(order);
            databaseRequest.child(orderNumber).child("Quantity").setValue(orderQuantity);

            //Confirmation of request sent
            Toast.makeText(this, "Order Stored", Toast.LENGTH_LONG).show();

            //Clear EditTexts
            orderDetails.setText("");
            quantity.setText("");

        }else {
            Toast.makeText(this, "Enter Your Order Information", Toast.LENGTH_LONG).show();

        }



    }

//    public void onClick(View view) {
//        if (view == viewOrder){
//            startActivity(new Intent(this, ViewOrders.class));
//
//        }
//
//
//    }

}
