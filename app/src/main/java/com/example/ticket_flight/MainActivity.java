package com.example.ticket_flight;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    private ImageView vector;
    private ImageView loading;

    private ConstraintLayout constraintLayout;
    private Handler handler = new Handler();
    static int delayTime = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activation);

        constraintLayout = findViewById(R.id.activation);
        vector = (ImageView) findViewById(R.id.vector);
        loading = (ImageView) findViewById(R.id.loading_activation);

        animateFlight();
    }
    private void animateFlight(){
        /*final float startX = vector.getX();
        final float endX = vector.getX() + loading.getWidth();*/

        final float startX = 10;
        final float endX = 760;

        final float a = -0.002f;
        final float b = 0.789f;
        final float c = -660f;

        ValueAnimator animator = ValueAnimator.ofFloat(startX, endX);
        animator.setDuration(delayTime);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                float y = (float) (a * x * x + b * x + c);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.setTranslationX(R.id.vector, x);
                constraintSet.setTranslationY(R.id.vector, y);
                constraintSet.applyTo(constraintLayout);
            }
        });
        animator.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, OnBoarding.class);
                startActivity(i);
                finish();
            }
        }, delayTime);
    }
    private float dpToPx(float dp) {
        return dp * getResources().getDisplayMetrics().density;
    }
}