package com.example.myworking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    public EditText user,password,fullName,email,phone;
    public Button regbut;
    public FirebaseAuth fauth;
    public ProgressBar progres ;
    public FirebaseFirestore fstore;
    public String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user=findViewById(R.id.usergister);
        password=findViewById(R.id.passregister);
        fullName=findViewById(R.id.fullNName);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        regbut=findViewById(R.id.regbutt);
       // progres=findViewById(R.id.progess);

        fauth=FirebaseAuth.getInstance();  // we can conect firebase auth
        fstore=FirebaseFirestore.getInstance();// we can cteate firebase firestore


        if (fauth.getCurrentUser() !=null){

//           Intent in=new Intent(getApplicationContext(),register.class);
//           startActivity(in);
//            finish();


        }


        regbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String use=user.getText().toString();
                String fullN=fullName.getText().toString();
                String pho=phone.getText().toString();

                if (TextUtils.isEmpty(mail)){
                    email.setError(" is empty");
                    return;

                }
                if (TextUtils.isEmpty(pass)){

                    password.setError("password is requred");
                    return;

                }
                if (TextUtils.isEmpty(use))
                {
                    user.setError("user name is Empty");

                }
                if (TextUtils.isEmpty(fullN))
                {

                    fullName.setError("Enter your name");
                }

                if (TextUtils.isEmpty(pho))

                 {
                     phone.setError("Enter your phone number");


                 }



                if (!(pass.length()>6)){

                    password.setError("password have above 6 caracters");
                    return;

                }



                //set data firbase-------------------------------------
                fauth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                     if (task.isSuccessful()){

                         Toast.makeText(register.this, "user created", Toast.LENGTH_LONG).show();


                         // create fireastor databas---------------------------------------------------

                         userID = fauth.getCurrentUser().getUid();// we can get userid auth table
                         DocumentReference doc=fstore.collection("user").document(userID);
                         Map<String,Object> user= new HashMap<>();
                         user.put("username",use);
                         user.put("email",mail);
                         user.put("fullName",fullN);
                         user.put("phone",pho);
                         doc.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                             @Override
                             public void onSuccess(Void unused) {

                                 System.out.println("successfull"+userID);


                             }
                         });

                         Intent in=new Intent(getApplicationContext(),loginpage.class);//when success user we can change login page
                         startActivity(in);
                         finish();

                         
                     }else {

                         Toast.makeText(register.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();



                     }
                        


                    }
                });

            }
        });

   //----------------------------------------------------------------------------------------------------------








    }
}