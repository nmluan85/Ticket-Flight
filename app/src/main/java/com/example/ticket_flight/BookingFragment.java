package com.example.ticket_flight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public BookingFragment() {
        // Required empty public constructor
    }
    private RelativeLayout bottom_navigation_bar;
    public void setLayoutNavigationBar(RelativeLayout bottom_navigation_bar) {
        this.bottom_navigation_bar = bottom_navigation_bar;
    }
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_booking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<BookingItem> bookingItems = new ArrayList<>();
        bookingItems.add(new BookingItem(R.drawable.booking_hotel, "Hotel"));
        bookingItems.add(new BookingItem(R.drawable.booking_transport, "Transport"));
        bookingItems.add(new BookingItem(R.drawable.booking_trip, "Trips"));
        bookingItems.add(new BookingItem(R.drawable.booking_event, "Events"));

        BookingAdapter bookingAdapter = new BookingAdapter(bookingItems);
        recyclerView.setAdapter(bookingAdapter);

        bookingAdapter.setOnItemClickListener(new BookingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BookingItem bookingItem = bookingItems.get(position);
                switch (bookingItem.getName_item()) {
                    case "Hotel":
                        HotelBookingFragment hotelBookingFragment = new HotelBookingFragment();
                        hotelBookingFragment.setLayoutNavigationBar(bottom_navigation_bar);
                        hotelBookingFragment.setOnFragmentInteractionListener(new HotelBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(hotelBookingFragment)
                                        .commit();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            @Override
                            public void onFragmentSaveChanges() {
                            }
                        });
                        getChildFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragmentContainer_Booking, hotelBookingFragment)
                                .commit();
                        recyclerView.setVisibility(View.GONE);
                        break;
                    case "Trips":
                        TripsBookingFragment tripsBookingFragment = new TripsBookingFragment();
                        tripsBookingFragment.setLayoutNavigationBar(bottom_navigation_bar);
                        tripsBookingFragment.setOnFragmentInteractionListener(new TripsBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(tripsBookingFragment)
                                        .commit();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            @Override
                            public void onFragmentSaveChanges() {
                            }
                        });
                        getChildFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragmentContainer_Booking, tripsBookingFragment)
                                .commit();
                        recyclerView.setVisibility(View.GONE);
                        break;
                    case "Transport":
                        TransportBookingFragment transportBookingFragment = new TransportBookingFragment();
                        transportBookingFragment.setLayoutNavigationBar(bottom_navigation_bar);
                        transportBookingFragment.setOnFragmentInteractionListener(new TransportBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(transportBookingFragment)
                                        .commit();
                                recyclerView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFragmentSaveChanges() {
                            }

                            @Override
                            public void onFragmentBackSuccess() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(transportBookingFragment)
                                        .commit();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        });
                        getChildFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragmentContainer_Booking, transportBookingFragment)
                                .commit();
                        recyclerView.setVisibility(View.GONE);
                        break;
                    case "Events":
                        EventsBookingFragment eventsBookingFragment = new EventsBookingFragment();
                        eventsBookingFragment.setLayoutNavigationBar(bottom_navigation_bar);

                        eventsBookingFragment.setOnFragmentInteractionListener(new EventsBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(eventsBookingFragment)
                                        .commit();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                            @Override
                            public void onFragmentSaveChanges() {
                            }
                        });
                        getChildFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragmentContainer_Booking, eventsBookingFragment)
                                .commit();
                        recyclerView.setVisibility(View.GONE);
                        break;
                }
            }
        });
        return view;
    }
}