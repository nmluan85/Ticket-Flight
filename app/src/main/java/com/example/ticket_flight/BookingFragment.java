package com.example.ticket_flight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
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
                        hotelBookingFragment.setOnFragmentInteractionListener(new HotelBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(hotelBookingFragment)
                                        .commit();
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
                        break;
                    case "Trips":
                        TripsBookingFragment tripsBookingFragment = new TripsBookingFragment();
                        tripsBookingFragment.setOnFragmentInteractionListener(new TripsBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(tripsBookingFragment)
                                        .commit();
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
                        break;
                    case "Transport":
                        TransportBookingFragment transportBookingFragment = new TransportBookingFragment();
                        transportBookingFragment.setOnFragmentInteractionListener(new TransportBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(transportBookingFragment)
                                        .commit();
                            }
                            @Override
                            public void onFragmentSaveChanges() {

                            }
                        });
                        getChildFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .replace(R.id.fragmentContainer_Booking, transportBookingFragment)
                                .commit();

                        break;
                    case "Events":
                        EventsBookingFragment eventsBookingFragment = new EventsBookingFragment();
                        eventsBookingFragment.setOnFragmentInteractionListener(new EventsBookingFragment.OnFragmentInteractionListener() {
                            @Override
                            public void onFragmentBack() {
                                getChildFragmentManager()
                                        .beginTransaction()
                                        .remove(eventsBookingFragment)
                                        .commit();
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
                        break;
                }
            }
        });
        return view;
    }
}