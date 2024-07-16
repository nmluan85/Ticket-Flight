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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket_flight.CustomAdapter.SearchAdapter;
import com.example.ticket_flight.CustomFragment.AccountFragment;
import com.example.ticket_flight.CustomFragment.BookingFragment;
import com.example.ticket_flight.CustomFragment.EventsBookingFragment;
import com.example.ticket_flight.CustomFragment.HomeFragment;
import com.example.ticket_flight.CustomFragment.HotelBookingFragment;
import com.example.ticket_flight.CustomFragment.NotificationFragment;
import com.example.ticket_flight.CustomFragment.TransportBookingFragment;
import com.example.ticket_flight.R;
import com.example.ticket_flight.CustomItem.SearchItem;
import com.example.ticket_flight.TripsBookingFragment;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private int selectedTab = 1;
    private RelativeLayout bottom_navigation_bar;
    private ConstraintLayout home_layout;
    private EditText search_bar;
    private ImageButton search_button;

    private TextView homeText, bookingText, notificationText, accountText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_layout = findViewById(R.id.constraint_layout_home);
        bottom_navigation_bar = findViewById(R.id.include_layout);
        search_bar = findViewById(R.id.editText_search);
        search_button = findViewById(R.id.imageButton);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout bookingLayout = findViewById(R.id.bookingLayout);
        final LinearLayout notificationLayout = findViewById(R.id.notificationLayout);
        final LinearLayout accountLayout = findViewById(R.id.accountLayout);

        final ImageView homeIcon = findViewById(R.id.icon_home);
        final ImageView bookingIcon = findViewById(R.id.icon_booking);
        final ImageView notificationIcon = findViewById(R.id.icon_notification);
        final ImageView accountIcon = findViewById(R.id.icon_account);

        homeText = findViewById(R.id.text_home);
        bookingText = findViewById(R.id.text_booking);
        notificationText = findViewById(R.id.text_notification);
        accountText = findViewById(R.id.text_account);

        ImageButton button_trips = findViewById(R.id.button_trips);
        ImageButton button_hotel = findViewById(R.id.button_hotel);
        ImageButton button_transport = findViewById(R.id.button_transport);
        ImageButton button_events = findViewById(R.id.button_events);

        homeText.setVisibility(View.VISIBLE);
        homeLayout.setBackgroundResource(R.drawable.active);

        button_trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeText.setVisibility(View.GONE);
                notificationText.setVisibility(View.GONE);
                accountText.setVisibility(View.GONE);

                homeLayout.setBackgroundResource(R.drawable.non_active);
                notificationLayout.setBackgroundResource(R.drawable.non_active);
                accountLayout.setBackgroundResource(R.drawable.non_active);

                bookingText.setVisibility(View.VISIBLE);
                bookingLayout.setBackgroundResource(R.drawable.active);
                TripsBookingFragment tripsBookingFragment = new TripsBookingFragment();
                tripsBookingFragment.setOnFragmentInteractionListener(new TripsBookingFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .remove(tripsBookingFragment)
                                .commit();
                        home_layout.setVisibility(View.VISIBLE);

                        bookingText.setVisibility(View.GONE);
                        notificationText.setVisibility(View.GONE);
                        accountText.setVisibility(View.GONE);

                        bookingLayout.setBackgroundResource(R.drawable.non_active);
                        notificationLayout.setBackgroundResource(R.drawable.non_active);
                        accountLayout.setBackgroundResource(R.drawable.non_active);

                        homeText.setVisibility(View.VISIBLE);
                        homeLayout.setBackgroundResource(R.drawable.active);
                    }
                    @Override
                    public void onFragmentSaveChanges() {

                    }
                });
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, tripsBookingFragment)
                        .addToBackStack(null)
                        .commit();
                home_layout.setVisibility(View.GONE);
            }
        });
        button_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeText.setVisibility(View.GONE);
                notificationText.setVisibility(View.GONE);
                accountText.setVisibility(View.GONE);

                homeLayout.setBackgroundResource(R.drawable.non_active);
                notificationLayout.setBackgroundResource(R.drawable.non_active);
                accountLayout.setBackgroundResource(R.drawable.non_active);

                bookingText.setVisibility(View.VISIBLE);
                bookingLayout.setBackgroundResource(R.drawable.active);
                HotelBookingFragment hotelBookingFragment = new HotelBookingFragment();
                hotelBookingFragment.setOnFragmentInteractionListener(new HotelBookingFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .remove(hotelBookingFragment)
                                .commit();
                        home_layout.setVisibility(View.VISIBLE);

                        bookingText.setVisibility(View.GONE);
                        notificationText.setVisibility(View.GONE);
                        accountText.setVisibility(View.GONE);

                        bookingLayout.setBackgroundResource(R.drawable.non_active);
                        notificationLayout.setBackgroundResource(R.drawable.non_active);
                        accountLayout.setBackgroundResource(R.drawable.non_active);

                        homeText.setVisibility(View.VISIBLE);
                        homeLayout.setBackgroundResource(R.drawable.active);
                    }
                    @Override
                    public void onFragmentSaveChanges() {

                    }
                });
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, hotelBookingFragment)
                        .addToBackStack(null)
                        .commit();
                home_layout.setVisibility(View.GONE);
            }
        });
        button_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeText.setVisibility(View.GONE);
                notificationText.setVisibility(View.GONE);
                accountText.setVisibility(View.GONE);

                homeLayout.setBackgroundResource(R.drawable.non_active);
                notificationLayout.setBackgroundResource(R.drawable.non_active);
                accountLayout.setBackgroundResource(R.drawable.non_active);

                bookingText.setVisibility(View.VISIBLE);
                bookingLayout.setBackgroundResource(R.drawable.active);
                TransportBookingFragment transportBookingFragment = new TransportBookingFragment();
                transportBookingFragment.setOnFragmentInteractionListener(new TransportBookingFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .remove(transportBookingFragment)
                                .commit();
                        home_layout.setVisibility(View.VISIBLE);

                        bookingText.setVisibility(View.GONE);
                        notificationText.setVisibility(View.GONE);
                        accountText.setVisibility(View.GONE);

                        bookingLayout.setBackgroundResource(R.drawable.non_active);
                        notificationLayout.setBackgroundResource(R.drawable.non_active);
                        accountLayout.setBackgroundResource(R.drawable.non_active);

                        homeText.setVisibility(View.VISIBLE);
                        homeLayout.setBackgroundResource(R.drawable.active);
                    }

                    @Override
                    public void onFragmentSaveChanges() {

                    }

                    @Override
                    public void onFragmentBackSuccess() {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .remove(transportBookingFragment)
                                .commit();
                    }
                });
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, transportBookingFragment, "TransportBookingFragment")
                        .addToBackStack(null)
                        .commit();
                home_layout.setVisibility(View.GONE);
            }
        });
        button_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeText.setVisibility(View.GONE);
                notificationText.setVisibility(View.GONE);
                accountText.setVisibility(View.GONE);

                homeLayout.setBackgroundResource(R.drawable.non_active);
                notificationLayout.setBackgroundResource(R.drawable.non_active);
                accountLayout.setBackgroundResource(R.drawable.non_active);

                bookingText.setVisibility(View.VISIBLE);
                bookingLayout.setBackgroundResource(R.drawable.active);
                EventsBookingFragment eventsBookingFragment = new EventsBookingFragment();
                eventsBookingFragment.setOnFragmentInteractionListener(new EventsBookingFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .remove(eventsBookingFragment)
                                .commit();
                        home_layout.setVisibility(View.VISIBLE);

                        bookingText.setVisibility(View.GONE);
                        notificationText.setVisibility(View.GONE);
                        accountText.setVisibility(View.GONE);

                        bookingLayout.setBackgroundResource(R.drawable.non_active);
                        notificationLayout.setBackgroundResource(R.drawable.non_active);
                        accountLayout.setBackgroundResource(R.drawable.non_active);

                        homeText.setVisibility(View.VISIBLE);
                        homeLayout.setBackgroundResource(R.drawable.active);
                    }
                    @Override
                    public void onFragmentSaveChanges() {

                    }
                });
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, eventsBookingFragment)
                        .addToBackStack(null)
                        .commit();
                home_layout.setVisibility(View.GONE);
            }
        });
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTab != 1){
                    HomeFragment homeFragment = new HomeFragment();

                    bookingText.setVisibility(View.GONE);
                    notificationText.setVisibility(View.GONE);
                    accountText.setVisibility(View.GONE);

                    bookingLayout.setBackgroundResource(R.drawable.non_active);
                    notificationLayout.setBackgroundResource(R.drawable.non_active);
                    accountLayout.setBackgroundResource(R.drawable.non_active);

                    homeText.setVisibility(View.VISIBLE);
                    homeLayout.setBackgroundResource(R.drawable.active);

                    selectedTab = 1;
                    homeFragment.setTextView(homeLayout, bookingLayout, notificationLayout, accountLayout, homeText, bookingText, notificationText, accountText);
                    homeFragment.setOnFragmentInteractionListener(new HomeFragment.OnFragmentInteractionListener() {
                        @Override
                        public void onFragmentBack() {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .remove(homeFragment)
                                    .commit();
                            home_layout.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onFragmentSaveChanges() {

                        }
                    });
                    loadFragment(homeFragment);
                    home_layout.setVisibility(View.GONE);
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

                    home_layout.setVisibility(View.GONE);

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
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Home.this)
                        .setTitle("Transport Booking")
                        .setMessage(search_bar.getText().toString())
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
