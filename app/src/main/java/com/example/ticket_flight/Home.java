package com.example.ticket_flight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    private int selectedTab = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout bookingLayout = findViewById(R.id.bookingLayout);
        final LinearLayout notificationLayout = findViewById(R.id.notificationLayout);
        final LinearLayout accountLayout = findViewById(R.id.accountLayout);

        final ImageView homeIcon = findViewById(R.id.icon_home);
        final ImageView bookingIcon = findViewById(R.id.icon_booking);
        final ImageView notificationIcon = findViewById(R.id.icon_notification);
        final ImageView accountIcon = findViewById(R.id.icon_account);

        final TextView homeText = findViewById(R.id.text_home);
        final TextView bookingText = findViewById(R.id.text_booking);
        final TextView notificationText = findViewById(R.id.text_notification);
        final TextView accountText = findViewById(R.id.text_account);

        homeText.setVisibility(View.VISIBLE);
        homeLayout.setBackgroundResource(R.drawable.active);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 1){

                    loadFragment(new HomeFragment());

                    bookingText.setVisibility(View.GONE);
                    notificationText.setVisibility(View.GONE);
                    accountText.setVisibility(View.GONE);

                    bookingLayout.setBackgroundResource(R.drawable.non_active);
                    notificationLayout.setBackgroundResource(R.drawable.non_active);
                    accountLayout.setBackgroundResource(R.drawable.non_active);

                    homeText.setVisibility(View.VISIBLE);
                    homeLayout.setBackgroundResource(R.drawable.active);

                    selectedTab = 1;
                }
            }
        });
        bookingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 2){

                    loadFragment(new BookingFragment());

                    homeText.setVisibility(View.GONE);
                    notificationText.setVisibility(View.GONE);
                    accountText.setVisibility(View.GONE);

                    homeLayout.setBackgroundResource(R.drawable.non_active);
                    notificationLayout.setBackgroundResource(R.drawable.non_active);
                    accountLayout.setBackgroundResource(R.drawable.non_active);

                    bookingText.setVisibility(View.VISIBLE);
                    bookingLayout.setBackgroundResource(R.drawable.active);

                    selectedTab = 2;
                }
            }
        });
        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 3){

                    loadFragment(new NotificationFragment());

                    bookingText.setVisibility(View.GONE);
                    homeText.setVisibility(View.GONE);
                    accountText.setVisibility(View.GONE);

                    bookingLayout.setBackgroundResource(R.drawable.non_active);
                    homeLayout.setBackgroundResource(R.drawable.non_active);
                    accountLayout.setBackgroundResource(R.drawable.non_active);

                    notificationText.setVisibility(View.VISIBLE);
                    notificationLayout.setBackgroundResource(R.drawable.active);

                    selectedTab = 3;
                }
            }
        });
        accountLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 4){

                    loadFragment(new AccountFragment());

                    bookingText.setVisibility(View.GONE);
                    notificationText.setVisibility(View.GONE);
                    homeText.setVisibility(View.GONE);

                    bookingLayout.setBackgroundResource(R.drawable.non_active);
                    notificationLayout.setBackgroundResource(R.drawable.non_active);
                    homeLayout.setBackgroundResource(R.drawable.non_active);

                    accountText.setVisibility(View.VISIBLE);
                    accountLayout.setBackgroundResource(R.drawable.active);

                    selectedTab = 4;
                }
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
