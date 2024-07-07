package com.example.ticket_flight.CustomFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ticket_flight.CustomActivity.Home;
import com.example.ticket_flight.CustomItem.TravellerItem;
import com.example.ticket_flight.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardingPassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardingPassFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
    private static final String ARG_PARAM11 = "param11";

    // TODO: Rename and change types of parameters
    private List<TravellerItem> travellers;
    private String departure_place;
    private String arrival_place;
    private String departure_date;
    private String departure_time;
    private String ticketID;
    private String name_class;
    private String price;
    private String numPeo;
    private String numBaby;
    private String departure_place_abb;
    private String arrival_place_abb;

    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
        void onFragmentBackSuccess();
    }
    private OnFragmentInteractionListener mListener;
    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }
    public void setTravellersInformation(List<TravellerItem> travellers){
        this.travellers = travellers;
    }
    public BoardingPassFragment() {
        // Required empty public constructor
    }
    public static BoardingPassFragment newInstance(String param1, String param2, String param3
            , String param4, String param5, String param6, String param7, String param8, String param9, String param10, String param11) {
        BoardingPassFragment fragment = new BoardingPassFragment();
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
        args.putString(ARG_PARAM11, param11);
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
            departure_time = getArguments().getString(ARG_PARAM4);
            ticketID = getArguments().getString(ARG_PARAM5);
            name_class = getArguments().getString(ARG_PARAM6);
            price = getArguments().getString(ARG_PARAM7);
            numPeo = getArguments().getString(ARG_PARAM8);
            numBaby = getArguments().getString(ARG_PARAM9);
            departure_place_abb = getArguments().getString(ARG_PARAM10);
            arrival_place_abb = getArguments().getString(ARG_PARAM11);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_boarding_pass, container, false);
        ImageButton back = view.findViewById(R.id.imageButton2);
        ImageButton download = view.findViewById(R.id.imageButton3);

        TextView name_flight = view.findViewById(R.id.text_pass);
        TextView from_place_abb = view.findViewById(R.id.from_place_abb_pass);
        TextView from_place = view.findViewById(R.id.from_place_pass);
        TextView to_place_abb = view.findViewById(R.id.to_place_abb_pass);
        TextView to_place = view.findViewById(R.id.to_place_pass);
        TextView from_date = view.findViewById(R.id.date_pass);
        TextView from_time = view.findViewById(R.id.departure_pass);
        TextView ticket_id = view.findViewById(R.id.num_ticket);
        TextView class_name = view.findViewById(R.id.class_name);
        TextView passenger_num = view.findViewById(R.id.num_passenger);
        TextView seat = view.findViewById(R.id.list_seat);

        String name = "British Airways Flight " + ticketID;
        name_flight.setText(name);
        from_place.setText(departure_place);
        from_place_abb.setText(departure_place_abb);

        to_place.setText(arrival_place);
        to_place_abb.setText(arrival_place_abb);

        from_date.setText(departure_date);
        from_time.setText(departure_time);
        class_name.setText(name_class);

        passenger_num.setText(setPassenger(numPeo, numBaby));
        seat.setText(setSeat(travellers));

        for (TravellerItem traveller : travellers) {
            Log.d("TAG", "onCreateView: " + traveller.getRowNum() + traveller.getColumnName());
        }
        Log.d("TAG", "onCreateView: " + setSeat(travellers));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentBackSuccess();
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_home = new Intent(getActivity(), Home.class);
                startActivity(back_home);
            }
        });
        return view;
    }
     private String setPassenger(String numPeo, String numBaby){
        int adults = Integer.parseInt(numPeo);
        int children = Integer.parseInt(numBaby);

        String adultLabel = adults == 1 ? "Adult" : "Adults";
        String childLabel = children == 1 ? "Child" : "Children";

        return adults + " " + adultLabel + "\n" + children + " " + childLabel;
    }
    private String setSeat(List<TravellerItem> travellers){
        String seatList = new String();
        for (TravellerItem traveller : travellers) {
            String number = traveller.getRowNum();
            String letter = traveller.getColumnName().toString();
            seatList += number + letter + ", ";
        }
        seatList = seatList.substring(0, seatList.length() - 2);
        return seatList;
    }
}