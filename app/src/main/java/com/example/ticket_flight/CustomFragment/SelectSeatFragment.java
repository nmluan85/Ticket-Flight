package com.example.ticket_flight.CustomFragment;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticket_flight.CustomAdapter.RowSeatAdapter;
import com.example.ticket_flight.CustomAdapter.TravellerAdapter;
import com.example.ticket_flight.CustomItem.TravellerItem;
import com.example.ticket_flight.R;
import com.example.ticket_flight.CustomItem.RowSeatItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectSeatFragment extends Fragment {

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
    private static final String ARG_PARAM12 = "param12";
    private static final String ARG_PARAM13 = "param13";
    private static final String ARG_PARAM14 = "param14";
    private static final String ARG_PARAM15 = "param15";
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
    private String price, total_price;
    private String departure_time;
    private String number_id;
    private String departure_place_abb;
    private String arrival_place_abb;

    ////////////////
    private ImageButton back, continue_button;
    private TextView text_your_seat, text_total_price;
    private int currentTraveller, previousTraveller, currentSeat_index = -1;
    private String currentSeatDetail;
    private RowSeatAdapter.SeatType currentSeatType;
    private ConstraintLayout layout_select_seat;
    private RecyclerView recyclerView_traveller, recyclerView_seat;
    private List<TravellerItem> travellerItems;
    private List<RowSeatItem> rowSeatItems;
    private TravellerAdapter travellerAdapter;
    private RowSeatAdapter rowSeatAdapter;
    private int selectedTravellerPosition = -1;
    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
        void onFragmentBackSuccess();
    }
    private OnFragmentInteractionListener mListener;
    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }

    public SelectSeatFragment() {
        // Required empty public constructor
    }
    public static SelectSeatFragment newInstance(String param1, String param2, String param3
            , String param4, String param5, String param6, String param7
            , String param8, String param9, String param10, String param11
            , String param12, String param13, String param14, String param15) {
        SelectSeatFragment fragment = new SelectSeatFragment();
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
        args.putString(ARG_PARAM12, param12);
        args.putString(ARG_PARAM13, param13);
        args.putString(ARG_PARAM14, param14);
        args.putString(ARG_PARAM15, param15);
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
            price = getArguments().getString(ARG_PARAM11);
            departure_time = getArguments().getString(ARG_PARAM12);
            number_id = getArguments().getString(ARG_PARAM13);
            departure_place_abb = getArguments().getString(ARG_PARAM14);
            arrival_place_abb = getArguments().getString(ARG_PARAM15);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_seat, container, false);
        layout_select_seat = view.findViewById(R.id.constraint_layout_select_seat);
        recyclerView_traveller = view.findViewById(R.id.recycler_view_traveller);
        recyclerView_seat = view.findViewById(R.id.recycler_view_list_seats);

        back = view.findViewById(R.id.button_back_seat);
        continue_button = view.findViewById(R.id.button_continue_select_seats);
        text_your_seat = view.findViewById(R.id.text_your_seats);
        text_total_price = view.findViewById(R.id.text_total_price);

        travellerItems = generateTravellerItems(Integer.parseInt(numPeo)+ Integer.parseInt(numBaby));
        travellerAdapter = new TravellerAdapter(travellerItems);

        rowSeatItems = generateRowSeatItems();
        rowSeatAdapter = new RowSeatAdapter(rowSeatItems, getContext());

        travellerAdapter.setOnItemClickListener(new TravellerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                currentTraveller = position;
                if(currentTraveller != previousTraveller){
                    rowSeatAdapter.setselectedPosition(RecyclerView.NO_POSITION);
                    rowSeatAdapter.updateSateSeat();
                    rowSeatAdapter.notifyDataSetChanged();
                    TravellerItem itemTraveller = travellerItems.get(previousTraveller);
                    if (currentSeat_index != -1){
                        RowSeatItem itemSeat = rowSeatItems.get(currentSeat_index);
                        itemTraveller.setIsBooked(2);
                        itemTraveller.setColumnName(itemSeat.getSelectedSeatType());
                        itemTraveller.setRowNum(String.valueOf(currentSeat_index + 1));
                    }
                    else {
                        itemTraveller.setIsBooked(0);
                    }
                    previousTraveller = currentTraveller;
                    currentSeat_index = -1;
                }
                if (!travellerItems.get(position).getRowNum().isEmpty()){
                    TravellerItem item = travellerItems.get(position);
                    item.setIsBooked(1);
                    currentSeatDetail = "Traveller " + String.valueOf(currentTraveller + 1) + " / " + item.getRowNum() + item.getColumnName().name();
                    text_your_seat.setText(currentSeatDetail);

                    currentSeat_index = Integer.parseInt(item.getRowNum()) - 1;
                    RowSeatItem item_seat = rowSeatItems.get(Integer.parseInt(item.getRowNum()) - 1);
                    item_seat.setSeatSelected(item.getColumnName());
                    rowSeatAdapter.notifyDataSetChanged();
                }
                travellerAdapter.notifyDataSetChanged();
            }
        });
        rowSeatAdapter.setOnItemClickListener(new RowSeatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                rowSeatAdapter.notifyDataSetChanged();
                currentSeat_index = position;
                currentSeatType = rowSeatItems.get(position).getSelectedSeatType();
                currentSeatDetail = "Traveller " + String.valueOf(currentTraveller + 1) + " / " + String.valueOf(currentSeat_index + 1) + currentSeatType.name();
                text_your_seat.setText(currentSeatDetail);

                rowSeatAdapter.updateSateSeat();

                TravellerItem item = travellerItems.get(currentTraveller);
                item.setRowNum(String.valueOf(currentSeat_index + 1));
                item.setColumnName(currentSeatType);
            }
        });
        recyclerView_traveller.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView_traveller.setAdapter(travellerAdapter);

        recyclerView_seat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView_seat.setAdapter(rowSeatAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentBack();
            }
        });
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkComplete()){
                    BoardingPassFragment boardingPassFragment = BoardingPassFragment
                            .newInstance(departure_place, arrival_place, departure_date, departure_time
                                    ,number_id, class_name, total_price, numPeo, numBaby, departure_place_abb, arrival_place_abb);
                    boardingPassFragment.setTravellersInformation(travellerItems);
                    boardingPassFragment.setOnFragmentInteractionListener(new BoardingPassFragment.OnFragmentInteractionListener() {
                        @Override
                        public void onFragmentBack() {
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .remove(boardingPassFragment)
                                    .commit();
                            layout_select_seat.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onFragmentSaveChanges() {

                        }

                        @Override
                        public void onFragmentBackSuccess() {
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .remove(boardingPassFragment)
                                    .commit();
                            mListener.onFragmentBackSuccess();
                            layout_select_seat.setVisibility(View.VISIBLE);
                        }
                    });
                    getChildFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container_view_select_seat, boardingPassFragment)
                            .addToBackStack(null)
                            .commit();
                    layout_select_seat.setVisibility(View.GONE);
                }
                else {
                    showToast("There are a few people who have not been selected for their seats!");
                }
            }
        });
        return view;
    }

    private List<RowSeatItem> generateRowSeatItems() {
        List<RowSeatItem> rowSeatItems = new ArrayList<>();
        rowSeatItems.add(new RowSeatItem(1, 2, 1, 1, 2));
        rowSeatItems.add(new RowSeatItem(2, 2, 2, 2, 1));
        rowSeatItems.add(new RowSeatItem(3, 1, 2, 2, 1));
        rowSeatItems.add(new RowSeatItem(4, 2, 1, 1, 1));
        rowSeatItems.add(new RowSeatItem(5, 2, 2, 2, 2));
        rowSeatItems.add(new RowSeatItem(6, 2, 1, 1, 2));
        rowSeatItems.add(new RowSeatItem(7, 2, 2, 2, 1));
        rowSeatItems.add(new RowSeatItem(8, 1, 2, 2, 1));
        rowSeatItems.add(new RowSeatItem(9, 2, 1, 1, 1));
        rowSeatItems.add(new RowSeatItem(10, 2, 2, 2, 1));
        return rowSeatItems;
    }
    private List<TravellerItem> generateTravellerItems(int length){
        List<TravellerItem> travellerItems = new ArrayList<>();
        for (int i = 0; i < length; i++){
            travellerItems.add(new TravellerItem(i + 1, null, ""));
        }
        travellerItems.get(0).setIsBooked(1);
        currentTraveller = 0;
        previousTraveller = 0;
        return travellerItems;
    }
    private boolean checkComplete(){
        for (int i = 0; i < travellerItems.size(); i++){
            if (travellerItems.get(i).getIsBooked() == 0){
                return false;
            }
        }
        return true;
    }
    private float totalPrice(){
        float total = 0;
        String priceItem = price.substring(1);
        for (int i = 0; i < travellerItems.size(); i++){
            if (!travellerItems.get(i).getRowNum().isEmpty()){
                total += Float.parseFloat(price);
            }
        }
        return total;
    }
    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
