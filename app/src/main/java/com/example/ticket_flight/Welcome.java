package com.example.ticket_flight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {
    ImageButton sign_up;
    ImageButton log_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        sign_up = findViewById(R.id.welcome_button_signup);
        log_in = findViewById(R.id.welcome_button_login);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Intent sign_up = new Intent(Welcome.this, SignIn.class);
                        startActivity(sign_up);
                        finish();
                    }
                }).start();
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
