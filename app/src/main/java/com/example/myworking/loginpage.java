package com.example.myworking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {

    public TextView create,singin,frogetpass;
    public EditText lgemail,lgpass;
    public FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        create=findViewById(R.id.create);
        lgemail=findViewById(R.id.userName);
        lgpass=findViewById(R.id.password);
        singin=findViewById(R.id.signIn);
       // frogetpass=findViewById(R.id.frogetpass);


        auth=FirebaseAuth.getInstance();  //link data auth

       create.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               startActivity(new Intent(getApplicationContext(),register.class));
               finish();

           }
       });



        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail=lgemail.getText().toString().trim();
                String pass=lgpass.getText().toString().trim();

                if (TextUtils.isEmpty(mail)){
                    lgemail.setError(" is empty");
                    return;

                }
                if (TextUtils.isEmpty(pass)){

                    lgpass.setError("password is requred");
                    return;

                }

                if (!(pass.length()>6)){

                    lgpass.setError("password have above 6 caracters");
                    return;

                }



                auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(loginpage.this, "login successfuly", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),userdash.class));
                            finish();

                        }else {

                            Toast.makeText(loginpage.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }



                    }
                });



            }
        });



    }
}