package com.example.rahul.gamesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView bottle;
    private Random random=new Random();
    private int lastDir;
    private boolean spinning;
    private Button openQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottle=findViewById(R.id.bottle);
        openQuiz=findViewById(R.id.openQuiz);
        openQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,QuizAppActivity.class);
                startActivity(intent);
            }
        });
    }



    public void spinBottle(View view) {
        if(!spinning) {
            int newDir = random.nextInt(1800);
            float pivotX = bottle.getWidth() / 2;
            float pivotY = bottle.getHeight() / 2;
            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning=true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning=false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            lastDir = newDir;
            bottle.startAnimation(rotate);
        }
    }
}
