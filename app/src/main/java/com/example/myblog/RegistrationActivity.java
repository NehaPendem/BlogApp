package com.example.myblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName,userEmail,userPassword;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    String name,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userName=findViewById(R.id.etName);
        userEmail=findViewById(R.id.etEmail);
        userPassword=findViewById(R.id.etPassword);
        signup=findViewById(R.id.btnReg);

        firebaseAuth=FirebaseAuth.getInstance();



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){

                    String user_email = userEmail.getText().toString().trim();
                    String user_password= userPassword.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this,"Successfully Registered, Upload Complete",Toast.LENGTH_SHORT).show();


                            finish();
                            startActivity(new Intent(RegistrationActivity.this,HomeActivity.class));
                        }
                        else{
                            Exception e = task.getException();
                            Log.d("Exception:"+ e.toString(),"Exception"+e.toString());

                            Toast.makeText(RegistrationActivity.this, "Registration Failed"+e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                }

            }
        });
    }

    private Boolean validate(){
        Boolean result=false;

        name= userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();



        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();


        }
        else{
            result=true;
        }
        return result;
    }
}