package com.example.ticket_flight;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
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
    ConstraintLayout filter_layout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";
    private static final String ARG_PARAM7 = "param7";
    private static final String ARG_PARAM8 = "param8";
    private static final String ARG_PARAM9 = "param9";
    private static final String ARG_PARAM10 = "param10";
    private String departure_place;
    private String arrival_place;
    private String departure_date;
    private String arrival_date;
    private String numPeo;
    private String numBaby;
    private String numPet;
    private String numLuggage;
    private String class_name;
    private String transport_name;


    ///////
    private String arrivalTime, departureTime, typeSort;
    private float minPrice, maxPrice;
    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
        void onFragmentBackSuccess();
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
    public static BookingTransportFlightFragment newInstance(String param1, String param2, String param3
            , String param4, String param5, String param6, String param7
            , String param8, String param9, String param10) {
        BookingTransportFlightFragment fragment = new BookingTransportFlightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putString(ARG_PARAM6, param6);
        args.putString(ARG_PARAM7, param7);
        args.putString(ARG_PARAM8, param8);
        args.putString(ARG_PARAM9, param9);
        args.putString(ARG_PARAM10, param10);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            departure_place = getArguments().getString(ARG_PARAM1);
            arrival_place = getArguments().getString(ARG_PARAM2);
            departure_date = getArguments().getString(ARG_PARAM3);
            arrival_date = getArguments().getString(ARG_PARAM4);
            numPeo = getArguments().getString(ARG_PARAM5);
            numBaby = getArguments().getString(ARG_PARAM6);
            numPet = getArguments().getString(ARG_PARAM7);
            numLuggage = getArguments().getString(ARG_PARAM8);
            class_name = getArguments().getString(ARG_PARAM9);
            transport_name = getArguments().getString(ARG_PARAM10);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_transport_flight, container, false);
        filter_layout = view.findViewById(R.id.main_layout_filter);

        TextView numFlight = view.findViewById(R.id.text_numFlight);

        ImageButton filter = view.findViewById(R.id.button_filter);
        ImageButton back = view.findViewById(R.id.button_back_booking);

        RecyclerView recyclerView_Date = view.findViewById(R.id.recycler_view_datebooking);
        recyclerView_Date.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        RecyclerView recyclerView_Booking_flight = view.findViewById(R.id.recycler_view_booking_flight);
        recyclerView_Booking_flight.setLayoutManager(new LinearLayoutManager(getContext()));

        List<DateItem> dateItems = generateDateItem(departure_date, arrival_date);
        DateAdapter dateAdapter = new DateAdapter(dateItems);
        recyclerView_Date.setAdapter(dateAdapter);

        List<TicketItem> ticketItems = generateTicketItem(departure_place, arrival_place, departure_date);
        TicketAdapter ticketAdapter = new TicketAdapter(ticketItems);
        recyclerView_Booking_flight.setAdapter(ticketAdapter);
        String text = ticketItems.size() + " " + "flights avaliable " + extractCityName(departure_place) + " to " + extractCityName(arrival_place);
        numFlight.setText(text);
        dateAdapter.setOnItemClickListener(new DateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Calendar calendar = stringToCalendar(departure_date);
                calendar.add(Calendar.DAY_OF_YEAR, position);
                String newDate = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(calendar.getTime());
                updateTicketItem(ticketItems, newDate);
                ticketAdapter.notifyDataSetChanged();
            }
        });
        ticketAdapter.setOnItemClickListener(new TicketAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TicketItem item = ticketItems.get(position);
                String departure_place_city = extractCityName(departure_place);
                String arrival_place_city = extractCityName(arrival_place);
                String departure_place_abb = extractAbbreviation(departure_place);
                String arrival_place_abb = extractAbbreviation(arrival_place);
                String date = extractDateAndMonth(departure_date);

                SelectSeatFragment selectSeatFragment = SelectSeatFragment
                        .newInstance(departure_place_city, arrival_place_city, date, arrival_date
                                , numPeo, numBaby, numPet, numLuggage
                                , class_name, transport_name, item.getPrice()
                                , item.getDeparture(), item.getNumber(), departure_place_abb, arrival_place_abb);
                selectSeatFragment.setOnFragmentInteractionListener(new SelectSeatFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getChildFragmentManager()
                                .beginTransaction()
                                .remove(selectSeatFragment)
                                .commit();
                        filter_layout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFragmentSaveChanges() {

                    }

                    @Override
                    public void onFragmentBackSuccess() {
                        getChildFragmentManager()
                                .beginTransaction()
                                .remove(selectSeatFragment)
                                .commit();
                        mListener.onFragmentBackSuccess();
                        filter_layout.setVisibility(View.VISIBLE);
                    }
                });
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view_filter, selectSeatFragment)
                        .addToBackStack(null)
                        .commit();
                filter_layout.setVisibility(View.GONE);

            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterBookingFragment filterBookingFragment = new FilterBookingFragment();
                filterBookingFragment.setOnFragmentInteractionListener(new FilterBookingFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getChildFragmentManager()
                                .beginTransaction()
                                .remove(filterBookingFragment)
                                .commit();
                        filter_layout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFragmentSaveChanges() {
                        String[] information = filterBookingFragment.getInformation();
                        arrivalTime = information[0];
                        departureTime = information[1];
                        typeSort = information[2];
                        minPrice = Float.parseFloat(information[3]);
                        maxPrice = Float.parseFloat(information[4]);
                    }
                });
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view_filter, filterBookingFragment)
                        .addToBackStack(null)
                        .commit();
                filter_layout.setVisibility(View.GONE);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentBack();
            }
        });
        return view;
    }
    private List<DateItem> generateDateItem(String departure_date, String arrival_date) {
        Calendar departure = stringToCalendar(departure_date);
        Calendar arrival = stringToCalendar(arrival_date);
        int length = arrival.get(Calendar.DAY_OF_YEAR) - departure.get(Calendar.DAY_OF_YEAR);

        List<DateItem> list = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());


        for (int i = 0; i < length; i++) {
            String fullDayName = dateFormat.format(departure.getTime());
            String abbreviatedDayName = fullDayName.substring(0, 2).toUpperCase();
            String day = dayFormat.format(departure.getTime());
            list.add(new DateItem(day, abbreviatedDayName));
            departure.add(Calendar.DAY_OF_YEAR, 1);
        }
        list.get(0).setIsSelected(true);
        return list;
    }
    private List<TicketItem> generateTicketItem(String departure_place, String arrival_place, String departure_date){
        List<TicketItem> list = new ArrayList<>();
        String cityNameFrom = extractCityName(departure_place);
        String abbreviationFrom = extractAbbreviation(departure_place);
        String cityNameTo = extractCityName(arrival_place);
        String abbreviationTo = extractAbbreviation(arrival_place);
        String date = extractDateAndMonth(departure_date);
        list.add(new TicketItem(cityNameFrom, abbreviationFrom, cityNameTo, abbreviationTo, date, "9:00 AM", "$50", "NL-41"));
        list.add(new TicketItem(cityNameFrom, abbreviationFrom, cityNameTo, abbreviationTo, date, "9:00 AM", "$50", "NL-42"));
        list.add(new TicketItem(cityNameFrom, abbreviationFrom, cityNameTo, abbreviationTo, date, "9:00 AM", "$50", "NL-43"));
        list.add(new TicketItem(cityNameFrom, abbreviationFrom, cityNameTo, abbreviationTo, date, "9:00 AM", "$50", "NL-44"));
        list.add(new TicketItem(cityNameFrom, abbreviationFrom, cityNameTo, abbreviationTo, date,"9:00 AM", "$50", "NL-45"));
        return list;
    }
    private Calendar stringToCalendar(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
    private String extractCityName(String s) {
        int index = s.indexOf(" (");
        if (index != -1) {
            return s.substring(0, index);
        }
        return s;
    }
    private static String extractAbbreviation(String s) {
        int startIndex = s.indexOf(" (") + 2;
        int endIndex = s.indexOf(")");
        if (startIndex != -1 && endIndex != -1) {
            return s.substring(startIndex, endIndex);
        }
        return "";
    }
    private void updateTicketItem(List<TicketItem> list, String departure_date) {
        String date = extractDateAndMonth(departure_date);
        for (TicketItem item : list) {
            item.setDate(date);
        }
    }
    private String extractDateAndMonth(String s) {
        int index = s.indexOf(",");
        if (index != -1) {
            return s.substring(0, index).trim();
        }
        return s;
    }
}