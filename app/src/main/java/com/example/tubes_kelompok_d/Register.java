package com.example.tubes_kelompok_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class Register extends AppCompatActivity {
    private Button register;
    private EditText Email,Password,Nama,Phone;
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Nama = findViewById(R.id.Nama);
        Phone = findViewById(R.id.Phone);
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

    }

    public void Register(){
        String email = Email.getText().toString();
        String pass = Password.getText().toString();

        if(email.isEmpty() && pass.isEmpty()) {
            Toast.makeText(Register.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
        }else if(email.isEmpty() || !email.endsWith("@gmail.com")) {
            Toast.makeText(Register.this,"Email Invalid",Toast.LENGTH_SHORT).show();
        }else if(pass.isEmpty()) {
            Toast.makeText(Register.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }else if(pass.length() < 6) {
            Toast.makeText(Register.this,"Password too short",Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(Register.this,Login.class));
                        Toast.makeText(getApplicationContext(),"Sign Up Successfull",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Sign Up Failed",Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
        }
    }
}