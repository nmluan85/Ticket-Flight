package com.example.ticket_flight.CustomFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ticket_flight.R;
import com.example.ticket_flight.TripsBookingFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton button_trips = view.findViewById(R.id.button_trips);
        ImageButton button_hotel = view.findViewById(R.id.button_hotel);
        ImageButton button_transport = view.findViewById(R.id.button_transport);
        ImageButton button_events = view.findViewById(R.id.button_events);


        button_trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        .replace(R.id.fragment_container, tripsBookingFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        button_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        .replace(R.id.fragment_container, hotelBookingFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        button_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                    @Override
                    public void onFragmentBackSuccess() {
                        getChildFragmentManager()
                                .beginTransaction()
                                .remove(transportBookingFragment)
                                .commit();
                    }
                });
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, transportBookingFragment, "TransportBookingFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        button_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        .replace(R.id.fragment_container, eventsBookingFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}