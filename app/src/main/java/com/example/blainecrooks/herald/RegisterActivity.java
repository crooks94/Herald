package com.example.blainecrooks.herald;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button regButton;
    private EditText regName;
    private EditText regEmail;
    private EditText regPassword;
    private EditText regConfirm;
    private TextView logView;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        regButton = (Button) findViewById(R.id.regButton);
        regName = (EditText) findViewById(R.id.regName);
        regEmail = (EditText) findViewById(R.id.regEmail);
        regPassword = (EditText) findViewById(R.id.regPassword);
        regConfirm = (EditText) findViewById(R.id.regConfirm);
        logView = (TextView) findViewById(R.id.logView);

        regButton.setOnClickListener(this);
    }

    private void registerUser() {
        String name = regName.getText().toString().trim();
        String email = regEmail.getText().toString().trim();
        String password = regPassword.getText().toString().trim();
        String passwordCon = regConfirm.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            //name is empty
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();

            //stops function executing further
            return;
        }


        if (TextUtils.isEmpty(email)) {
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

        if (TextUtils.isEmpty(passwordCon)) {
            //email is empty
            Toast.makeText(this, "Please Re-enter Password", Toast.LENGTH_SHORT).show();

            //stops function executing further
            return;
        }

        //if validations correct, show progress dialog
        progressDialog.setMessage("Registering User, Please Wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //successful login
                            //start profile activity
                            //display toast temporarily
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                            Intent reg = new Intent(RegisterActivity.this, helpActivity.class);
                            startActivity(reg);
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Register Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            //stops function executing further
            return;
        }
        //if validation okay

    }

    @Override
    public void onClick(View view) {
        if (view == regButton) {
            registerUser();
        }

        if (view == logView) {
            Intent log = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(log);
            finish();
        }
    }


}
