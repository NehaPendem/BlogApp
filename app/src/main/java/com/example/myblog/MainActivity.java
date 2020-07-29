package com.example.myblog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button Register;
    private Button Login;


    private EditText LoginEmail,LoginPassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Register=(Button)findViewById(R.id.btnRegister);
        Login=findViewById(R.id.btnLogin);
        LoginEmail=findViewById(R.id.etLoginEmail);
        LoginPassword=findViewById(R.id.etLoginPassword);

        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        /*if(user !=null){
            finish();
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }*/

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(LoginEmail.getText().toString(),LoginPassword.getText().toString());
            }
        });


            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
                }
            });



    }

    private void validate(String email, String password){

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}