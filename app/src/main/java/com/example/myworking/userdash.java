package com.example.myworking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class userdash extends AppCompatActivity {

    public ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdash);

        logo=findViewById(R.id.logodash);

        Animation an= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logoanimi);
        logo.setAnimation(an);



    }
}