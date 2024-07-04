package com.example.ticket_flight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingTransportFlightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingTransportFlightFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
    }
    private OnFragmentInteractionListener mListener;
    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }
    public BookingTransportFlightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingTransportFlightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingTransportFlightFragment newInstance(String param1, String param2) {
        BookingTransportFlightFragment fragment = new BookingTransportFlightFragment();
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
        View view = inflater.inflate(R.layout.fragment_booking_transport_flight, container, false);
        ImageButton back = view.findViewById(R.id.button_back_flight);

        RecyclerView recyclerView_Date = view.findViewById(R.id.recycler_view_datebooking);
        recyclerView_Date.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        RecyclerView recyclerView_Booking_flight = view.findViewById(R.id.recycler_view_booking_flight);
        recyclerView_Booking_flight.setLayoutManager(new LinearLayoutManager(getContext()));

        List<DateItem> dateItems = generateDateItem(15);
        DateAdapter dateAdapter = new DateAdapter(dateItems);
        recyclerView_Date.setAdapter(dateAdapter);

        List<TicketItem> ticketItems = generateTicketItem();
        TicketAdapter ticketAdapter = new TicketAdapter(ticketItems);
        recyclerView_Booking_flight.setAdapter(ticketAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentBack();
            }
        });
        return view;
    }
    private List<DateItem> generateDateItem(int length) {
        List<DateItem> list = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());

        Calendar calendar = Calendar.getInstance();

        for (int i = 0; i < length; i++) {
            String fullDayName = dateFormat.format(calendar.getTime());
            String abbreviatedDayName = fullDayName.substring(0, 2).toUpperCase();
            String day = dayFormat.format(calendar.getTime());
            list.add(new DateItem(day, abbreviatedDayName));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return list;
    }
    private List<TicketItem> generateTicketItem(){
        List<TicketItem> list = new ArrayList<>();
        list.add(new TicketItem("New York", "London", "02 Jun", "9:00 AM", "$50", "NL-41"));
        list.add(new TicketItem("New York", "London", "04 Jun", "9:00 AM", "$50", "NL-41"));
        list.add(new TicketItem("New York", "London", "05 Jun", "9:00 AM", "$50", "NL-41"));
        list.add(new TicketItem("New York", "London", "02 Jun", "9:00 AM", "$50", "NL-41"));
        list.add(new TicketItem("New York", "London", "02 Jun", "9:00 AM", "$50", "NL-41"));
        return list;
    }
}