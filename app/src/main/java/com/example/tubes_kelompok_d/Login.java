package com.example.tubes_kelompok_d;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {

    private TextView register;
    private EditText edtEmail,edtPass;
    private Button btnSignIn;
    private FirebaseAuth mAuth;
    private String CHANNEL_ID = "Channel 1";
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
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

        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Enter Your Email", Toast.LENGTH_SHORT)
                    .show();
            edtEmail.setError("Enter Your Email");
            return;
        } else if(!isValidEmail(email)){
            Toast.makeText(getApplicationContext(), "Email Invalid", Toast.LENGTH_SHORT)
                    .show();
            edtEmail.setError("Email Invalid");
            return;
        } else if (TextUtils.isEmpty(pass)){
            Toast.makeText(getApplicationContext(), "Enter Your Password", Toast.LENGTH_SHORT)
                    .show();
            edtPass.setError("Enter Your Password");
            return;
        }

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    getUser();
                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    clearText();
                }
                progressDialog.dismiss();
            }
        });
    }

    @NotNull
    private Boolean isValidEmail(CharSequence charSequence){
        return (!TextUtils.isEmpty(charSequence) &&
                Patterns.EMAIL_ADDRESS.matcher(charSequence).matches());
    }

    private void clearText(){
        edtEmail.setText("");
        edtPass.setText("");
    }

    private void getUser(){
        final String emailEntered = edtEmail.getText().toString().trim();
        final String passEntered = edtPass.getText().toString().trim();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");

        Query queryCek = databaseReference.orderByChild("password").equalTo(passEntered);
        queryCek.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String emailFormDB = snapshot.child(passEntered).child("email").getValue(String.class);

                    if (emailFormDB.equals(emailEntered)){
                        String nameFormDB = snapshot.child(passEntered).child("nama").getValue(String.class);
                        String phoneFormDB = snapshot.child(passEntered).child("phone").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Navbar.class);

                        intent.putExtra("nama", nameFormDB);
                        intent.putExtra("phone", phoneFormDB);
                        intent.putExtra("email", emailFormDB);

                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}