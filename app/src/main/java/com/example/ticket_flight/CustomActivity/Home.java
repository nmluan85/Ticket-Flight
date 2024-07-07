package com.example.ticket_flight.CustomActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket_flight.CustomAdapter.SearchAdapter;
import com.example.ticket_flight.CustomFragment.AccountFragment;
import com.example.ticket_flight.CustomFragment.BookingFragment;
import com.example.ticket_flight.CustomFragment.HomeFragment;
import com.example.ticket_flight.CustomFragment.NotificationFragment;
import com.example.ticket_flight.R;
import com.example.ticket_flight.CustomItem.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private int selectedTab = 1;
    private RelativeLayout bottom_navigation_bar;
    private EditText search_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottom_navigation_bar = findViewById(R.id.include_layout);
        search_bar = findViewById(R.id.editText_search);

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
                    HomeFragment homeFragment = new HomeFragment();
                    loadFragment(homeFragment);

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
                    BookingFragment bookingFragment = new BookingFragment();
                    bookingFragment.setLayoutNavigationBar(bottom_navigation_bar);
                    loadFragment(bookingFragment);

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
                    NotificationFragment notificationFragment = new NotificationFragment();
                    loadFragment(notificationFragment);

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
                    AccountFragment accountFragment = new AccountFragment();
                    loadFragment(accountFragment);

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
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Home.this)
                        .setTitle("Seat type Error!")
                        .setMessage("Please select the seats corresponding to your seat type (or class).")
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}
