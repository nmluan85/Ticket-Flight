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
    private SearchView searching;
    private RecyclerView view_search;
    private List<SearchItem> searchItems;
    private List<SearchItem> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottom_navigation_bar = findViewById(R.id.include_layout);
        searching = findViewById(R.id.editText_search);
        view_search = findViewById(R.id.recycler_view_search);
        searching.requestFocus();

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
        view_search.setLayoutManager(new LinearLayoutManager(Home.this));
        searchItems = generateItemSearch();
        SearchAdapter searchAdapter = new SearchAdapter(searchItems);
        view_search.setAdapter(searchAdapter);
        view_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        searching.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                view_search.setVisibility(View.VISIBLE);
                resultList = new ArrayList<>();
                Log.d("TAG", "onQueryTextSubmit: "+query);
                if (!query.isEmpty()){
                    for (int i = 0; i < searchItems.size(); i++) {
                        if (searchItems.get(i).getFrom().toUpperCase().contains(query.toUpperCase())) {
                            SearchItem item = new SearchItem(searchItems.get(i).getFrom(), searchItems.get(i).getTo(), true);
                            resultList.add(item);
                        }
                    }
                    view_search.setLayoutManager(new LinearLayoutManager(Home.this));
                    SearchAdapter searchAdapter = new SearchAdapter(resultList);
                    view_search.setAdapter(searchAdapter);
                }
                else {
                    view_search.setLayoutManager(new LinearLayoutManager(Home.this));
                    SearchAdapter searchAdapter = new SearchAdapter(searchItems);
                    view_search.setAdapter(searchAdapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
    }

    private List<SearchItem> generateItemSearch() {
        List<SearchItem> searchItems = new ArrayList<>();
        searchItems.add(new SearchItem("London (LDN)", "Tokyo (TKY)", true));
        searchItems.add(new SearchItem("HCM", "Paris (PAR)", true));
        searchItems.add(new SearchItem("Lunar New Year Festival", "", false));
        searchItems.add(new SearchItem("Christmas", "", false));
        searchItems.add(new SearchItem("Eiffel Tower, Paris", "", false));
        searchItems.add(new SearchItem("Mexico (MEX)", "Vietnam (VN)", true));
        searchItems.add(new SearchItem("London (LDN)", "Tokyo (TKY)", true));
        searchItems.add(new SearchItem("Halloween", "", false));
        searchItems.add(new SearchItem("The Taj Mahal, India", "", false));
        searchItems.add(new SearchItem("Great Wall of China", "", false));
        return searchItems;
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}
