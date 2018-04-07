package com.example.blainecrooks.herald;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private static final String TAG = "AdminActivity";

    //Firebase imports
//    private FirebaseDatabase mFirebaseDatabase;
//      private FirebaseAuth FirebaseAuth;
//      private DatabaseReference databaseReference, rootRef;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;
//    private DatabaseReference databaseRequest;

    private DatabaseReference databaseReference, mdatabase;



//    private String userID;
    private ListView listView;


    private ArrayList<HashMap<String, Object>> requests = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
//        FirebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("hotKeyRequest");
        mdatabase = FirebaseDatabase.getInstance().getReference().child("userRequest");

        listView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<HashMap<String, Object>> arrayAdapter = new ArrayAdapter<HashMap<String, Object>>(this, android.R.layout.simple_list_item_1, requests);

        listView.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                HashMap value = dataSnapshot.getValue(String.class);
                HashMap<String, Object> value = (HashMap<String, Object >) dataSnapshot.getValue();
                Log.d(TAG, "Requests: " + value);
                requests.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                HashMap value = dataSnapshot.getValue(String.class);
                HashMap<String, Object> value = (HashMap<String, Object >) dataSnapshot.getValue();
                Log.d(TAG, "Requests: " + value);
                requests.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });









//        FirebaseUser firebaseUser = FirebaseAuth.getCurrentUser();
//        String uid = firebaseUser.getUid();
////        if(firebaseUser != null){
////            String uid = firebaseUser.getUid();
////        }
//
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference postRef = rootRef.child(uid).child("hotKeyRequest");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    String name = ds.child("Name").getValue(String.class);
//                    String request = ds.child("Request").getValue(String.class);
//                    String room = ds.child("Room").getValue(String.class);
//                    Log.d("TAG", name + " / " + request + " / " + room);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//
//        postRef.addListenerForSingleValueEvent(eventListener);
//        //points to root of DB
//        rootRef = FirebaseDatabase.getInstance().getReference();
//
//        //pointing to specific node
//        databaseReference = rootRef.child("hotKeyRequest");
//
//        fetchRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                databaseReference.child("ticketNumber").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        String value = dataSnapshot.getValue(String.class);
//                        showRequests.setText(value);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });














        //hold collection of requests
//        final List<RequestInformation> requests = new ArrayList<RequestInformation>();
//
//        //set up DB connection
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = database.getReference();
////        databaseReference.child("hotKeyRequest");
//        databaseReference.child("hotKeyRequest" + "userRequest").addValueEventListener(new ValueEventListener() {
//            @Override
//
//            //will be invoked whenever data on DB changes & invoked as soon as listener connected
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //get all children of this level
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//
//                //iterate through DB
////                for (DataSnapshot child: children) {
////                    RequestInformation value = child.getValue(RequestInformation.class);
////                    requests.add(value);
////
//////                    RequestInformation requests = listView.setAdapter(requests);
////
////                }
//
//                for (DataSnapshot requestSnapshot: dataSnapshot.getChildren()){
//                    RequestInformation request = requestSnapshot.getValue(RequestInformation.class);
//
//                    if (request != null){
//                        requests.add(request);
//                    }
//                }
//
//                //ArrayAdapter adapter = new ArrayAdapter(AdminActivity.this, requests);
//                //listView.setAdapter(adapter);
//
//
//
//
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        ArrayAdapter<RequestInformation> requestAdapter = new ArrayAdapter<RequestInformation>(this, android.R.layout.simple_list_item_1,requests);





//        listView = (ListView) findViewById(R.id.listView);
//
//        //declare DB reference object
//        FirebaseAuth = FirebaseAuth.getInstance();
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        FirebaseUser user = FirebaseAuth.getCurrentUser();
//        userID = user.getUid();
    }
}
