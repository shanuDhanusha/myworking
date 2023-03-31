package com.example.myworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

   public ImageView working,logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        working=findViewById(R.id.imgeworking);
        logo=findViewById(R.id.imgelogo);

        Animation animi= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.myanim);
        Animation animi2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.workinlebel);
         working.setAnimation(animi2);
         logo.setAnimation(animi);

         //strat login page after thred-------------------------------------
        new Thread(){

            @Override
            public void run() {
                super.run();

                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {
                    Intent in=new Intent(getApplicationContext(),loginpage.class);
                    startActivity(in);
                    MainActivity.this.finish();
                }


            }
        }.start();

    }
}