package com.example.ticket_flight;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransportBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransportBookingFragment extends Fragment {
    private String[] infomationBooking;
    private String nameTrasport = "";
    private String nameClass = "";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Color colorTransport;
    private Color colorIconTransport;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText from, to, departure, arrival;
    private EditText editPeople, editBaby, editPet, editLuggage;
    private ImageView imagePeople, linePeople, imageBaby, lineBaby, imagePet, linePet, imageLuggage, lineLuggage;
    //Economy
    private ImageView imageEconomy, imageBusiness;
    private TextView iconEconomy, iconBusiness;
    private ImageView imagePlane, iconPlane, imageShip, iconShip, imageTrain, iconTrain, imageBus, iconBus;
    private ImageButton search, back;
    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
    }
    private OnFragmentInteractionListener mListener;
    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }
    public TransportBookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransportBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransportBookingFragment newInstance(String param1, String param2) {
        TransportBookingFragment fragment = new TransportBookingFragment();
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
        View view = inflater.inflate(R.layout.fragment_transport_booking, container, false);
        //Information
        from = view.findViewById(R.id.edit_from);
        to = view.findViewById(R.id.edit_to);
        departure = view.findViewById(R.id.edit_departure);
        arrival = view.findViewById(R.id.edit_return);
        //Edit People
        editPeople = view.findViewById(R.id.edit_people);
        imagePeople = view.findViewById(R.id.image_people);
        linePeople = view.findViewById(R.id.image_line_people);
        //Edit baby
        editBaby = view.findViewById(R.id.edit_baby);
        imageBaby = view.findViewById(R.id.image_baby);
        lineBaby = view.findViewById(R.id.image_line_baby);
        //Edit pet
        editPet = view.findViewById(R.id.edit_pet);
        imagePet = view.findViewById(R.id.image_pet);
        linePet = view.findViewById(R.id.image_line_pet);
        //Edit luggage
        editLuggage = view.findViewById(R.id.edit_luggage);
        imageLuggage = view.findViewById(R.id.image_luggage);
        lineLuggage = view.findViewById(R.id.image_line_luggage);
        //Choose class
        //Economy
        imageEconomy = view.findViewById(R.id.button_image_economy);
        iconEconomy = view.findViewById(R.id.text_economy);
        //Business
        imageBusiness = view.findViewById(R.id.button_business);
        iconBusiness = view.findViewById(R.id.text_business);
        //Choose transport
        //Plane
        imagePlane = view.findViewById(R.id.button_plane);
        iconPlane = view.findViewById(R.id.icon_plane);
        //Ship
        imageShip = view.findViewById(R.id.button_ship);
        iconShip = view.findViewById(R.id.icon_ship);
        //Train
        imageTrain = view.findViewById(R.id.button_train);
        iconTrain = view.findViewById(R.id.icon_train);
        //Bus
        imageBus = view.findViewById(R.id.button_bus);
        iconBus = view.findViewById(R.id.icon_bus);

        search = view.findViewById(R.id.button_search_flight);
        back = view.findViewById(R.id.button_back_1);
        //Set listener
        editPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!editPeople.getText().toString().isEmpty()) {
                    editPeople.setTextColor(getResources().getColor(R.color.color_icon));
                    imagePeople.setColorFilter(getResources().getColor(R.color.color_icon));
                    linePeople.setColorFilter(getResources().getColor(R.color.color_icon));
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                editPeople.setTextColor(getResources().getColor(R.color.color_icon));
                imagePeople.setColorFilter(getResources().getColor(R.color.color_icon));
                linePeople.setColorFilter(getResources().getColor(R.color.color_icon));
            }
        });
        editBaby.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!editBaby.getText().toString().isEmpty()) {
                    editBaby.setTextColor(getResources().getColor(R.color.color_icon));
                    imageBaby.setColorFilter(getResources().getColor(R.color.color_icon));
                    lineBaby.setColorFilter(getResources().getColor(R.color.color_icon));
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editBaby.setTextColor(getResources().getColor(R.color.color_icon));
                imageBaby.setColorFilter(getResources().getColor(R.color.color_icon));
                lineBaby.setColorFilter(getResources().getColor(R.color.color_icon));
            }
        });
        editPet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!editPet.getText().toString().isEmpty()) {
                    editPet.setTextColor(getResources().getColor(R.color.color_icon));
                    imagePet.setColorFilter(getResources().getColor(R.color.color_icon));
                    linePet.setColorFilter(getResources().getColor(R.color.color_icon));
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                editPet.setTextColor(getResources().getColor(R.color.color_icon));
                imagePet.setColorFilter(getResources().getColor(R.color.color_icon));
                linePet.setColorFilter(getResources().getColor(R.color.color_icon));
            }
        });
        editLuggage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!editLuggage.getText().toString().isEmpty()) {
                    editLuggage.setTextColor(getResources().getColor(R.color.color_icon));
                    imageLuggage.setColorFilter(getResources().getColor(R.color.color_icon));
                    lineLuggage.setColorFilter(getResources().getColor(R.color.color_icon));
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editLuggage.setTextColor(getResources().getColor(R.color.color_icon));
                imageLuggage.setColorFilter(getResources().getColor(R.color.color_icon));
                lineLuggage.setColorFilter(getResources().getColor(R.color.color_icon));
            }
        });
        imageEconomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBusiness.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconBusiness.setTextColor(ContextCompat.getColor(getContext(), R.color.color_icon));
                nameClass = "Economy";
                imageEconomy.setImageResource(R.drawable.button_class);
                imageEconomy.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconEconomy.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            }
        });
        imageBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageEconomy.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconEconomy.setTextColor(ContextCompat.getColor(getContext(), R.color.color_icon));
                nameClass = "Business";
                imageBusiness.setImageResource(R.drawable.button_class);
                imageBusiness.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconBusiness.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            }
        });
        imagePlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imageTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imageBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                nameTrasport = "Plane";
                imagePlane.setImageResource(R.drawable.button_transport);
                iconPlane.setImageResource(R.drawable.icon_plane);
                imagePlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconPlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
            }
        });
        imageShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconPlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imageTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imageBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                nameTrasport = "Ship";
                imageShip.setImageResource(R.drawable.button_transport);
                iconShip.setImageResource(R.drawable.icon_ship);
                imageShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
            }
        });
        imageTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imagePlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconPlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imageBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                nameTrasport = "Train";
                imageTrain.setImageResource(R.drawable.button_transport);
                iconTrain.setImageResource(R.drawable.icon_train);
                imageTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
            }
        });
        imageBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imageTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                imagePlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconPlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_icon));

                nameTrasport = "Bus";
                imageBus.setImageResource(R.drawable.button_transport);
                iconBus.setImageResource(R.drawable.icon_bus);
                imageBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
            }
        });
        /*switch (nameTrasport){
            case "Plane":
                imagePlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconPlane.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                break;
            case "Ship":
                imageShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconShip.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                break;
            case "Train":
                imageTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconTrain.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                break;
            case "Bus":
                imageBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                break;
        }*/
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingTransportFlightFragment transportFlightFragment = new BookingTransportFlightFragment();
                transportFlightFragment.setOnFragmentInteractionListener(new BookingTransportFlightFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getChildFragmentManager()
                                .beginTransaction()
                                .remove(transportFlightFragment)
                                .commit();
                    }
                    @Override
                    public void onFragmentSaveChanges() {

                    }
                });
                getChildFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.containerView_booking_flight, transportFlightFragment)
                        .commit();
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
}