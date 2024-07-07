package com.example.ticket_flight.CustomActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket_flight.R;

public class SignIn extends AppCompatActivity {
    ImageButton button_back;
    EditText edit_email;
    EditText edit_passwork;
    ImageButton button_sign_in;
    TextView text_sign_up;
    TextView text_forgot_password;

    ImageButton button_facebook;
    ImageView icon_facebook;
    ImageButton button_google;
    ImageView icon_google;
    ImageButton button_apple;
    ImageView icon_apple;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        button_back = findViewById(R.id.button_back);
        edit_email = findViewById(R.id.edit_text_email);
        edit_passwork = findViewById(R.id.edit_text_password);
        button_sign_in = findViewById(R.id.button_sign_in);
        text_sign_up = findViewById(R.id.text_sign_up);
        text_forgot_password = findViewById(R.id.text_forgot_password);
        button_facebook = findViewById(R.id.button_facebook);
        icon_facebook = findViewById(R.id.icon_facebook);
        button_google = findViewById(R.id.button_google);
        icon_google = findViewById(R.id.icon_google);
        button_apple = findViewById(R.id.button_apple);
        icon_apple = findViewById(R.id.icon_apple);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backWelcome = new Intent(SignIn.this, Welcome.class);
                startActivity(backWelcome);
            }
        });
        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString().trim();
                String password = edit_passwork.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(SignIn.this,"Please enter email and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent home = new Intent(SignIn.this, Home.class);
                    startActivity(home);
                }
            }
        });
        button_facebook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               icon_facebook.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction(new Runnable() {
                   @Override
                   public void run() {
                       Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                       startActivity(facebook);
                   }
               }).start();
           }
       });
        button_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icon_google.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Intent google = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                        startActivity(google);
                    }
                }).start();
            }
        });
        button_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icon_apple.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Intent apple = new Intent(Intent.ACTION_VIEW, Uri.parse("https://appleid.apple.com/"));
                        startActivity(apple);
                    }
                }).start();
            }
        });

    }
}
