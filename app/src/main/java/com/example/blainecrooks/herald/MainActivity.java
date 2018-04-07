package com.example.blainecrooks.herald;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button loginButton;
    private EditText logEmail;
    private EditText logPassword;
    private TextView regView;



    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        //final String userEmail = user.getEmail();
       // String uid = firebaseAuth.getCurrentUser().getUid();


        if (user != null) {
            if (firebaseAuth.getCurrentUser().getUid().equals("TvLf7AY679OgE0vxzlgBXH59ur53")) {
                startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));

            } else {
                startActivity(new Intent(getApplicationContext(), helpActivity.class));
                finish();
            }
        }

        loginButton = (Button) findViewById(R.id.loginButton);
        logEmail = (EditText) findViewById(R.id.logEmail);
        logPassword = (EditText) findViewById(R.id.logPassword);
        regView = (TextView) findViewById(R.id.regView);

        progressDialog = new ProgressDialog(this);

        loginButton.setOnClickListener(this);
        regView.setOnClickListener(this);






    }

    private void userLogin(){
        String email = logEmail.getText().toString().trim();
        String password = logPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

            //stops function executing further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //email is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            //stops function executing further
            return;
        }

        progressDialog.setMessage("Logging You In, Please Wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //admin login
                    if (firebaseAuth.getCurrentUser().getUid().equals("TvLf7AY679OgE0vxzlgBXH59ur53")){
                        Toast.makeText(MainActivity.this, "Signing In", Toast.LENGTH_LONG);
                        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));

                    }
                    //user login
                    else {
                        Toast.makeText(MainActivity.this, "Signing In", Toast.LENGTH_LONG);

//                    Intent log = new Intent(MainActivity.this, helpActivity.class);
                        startActivity(new Intent(getApplicationContext(), helpActivity.class));
                        finish();
                    }

                }
                //failure
                else {
                    Toast.makeText(MainActivity.this, "Sign In Error", Toast.LENGTH_LONG);
                }
            }
        });
        {


        }

    }

    @Override
    public void onClick(View view) {
        if (view == loginButton){
            userLogin();
        }

        if(view == regView){
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
