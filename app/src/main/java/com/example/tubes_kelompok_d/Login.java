package com.example.tubes_kelompok_d;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView register;
    private EditText edtEmail,edtPass;
    private Button btnSignIn;
    private FirebaseAuth mAuth;
    private String CHANNEL_ID = "Channel 1";


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.Email);
        edtPass = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.Login);
        register = (TextView) findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
           }
       });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }


    private void Login() {
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),Navbar.class);
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });
    }

}