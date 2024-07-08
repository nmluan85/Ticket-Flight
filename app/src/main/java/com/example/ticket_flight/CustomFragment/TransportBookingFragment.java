package com.example.ticket_flight.CustomFragment;

import static java.util.Collections.swap;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ticket_flight.CustomSpinnerAdapter;
import com.example.ticket_flight.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransportBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransportBookingFragment extends Fragment {
    private String[] infomationBooking;
    private ConstraintLayout layout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner sp_from, sp_to;
    private List<String> listFrom, listTo;
    private EditText editPeople, editBaby, editPet, editLuggage;
    private ImageView imagePeople, linePeople, imageBaby, lineBaby, imagePet, linePet, imageLuggage, lineLuggage;
    //Economy
    private ImageView imageEconomy, imageBusiness;
    private TextView iconEconomy, iconBusiness, text_departure_date, text_arrival_date;
    private ImageView imagePlane, iconPlane, imageShip, iconShip, imageTrain, iconTrain, imageBus, iconBus;
    private ImageButton search, back, swap, button_departure_date, button_arrival_date;
    private String from_place = "", to_place = "", departureDate = "" , returnDate ="", class_name = "", transport_name = "";
    private String numPeo = "", numBaby = "", numPet = "", numLuggage = "";
    private Calendar departureCalendar, arrivalCalendar;
    private SimpleDateFormat dateFormat;
    private RelativeLayout bottom_navigation_bar;
    public void setLayoutNavigationBar(RelativeLayout bottom_navigation_bar) {
        this.bottom_navigation_bar = bottom_navigation_bar;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
        void onFragmentBackSuccess();
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
        layout = view.findViewById(R.id.layout_transport_booking);

        //Spinner
        sp_from = view.findViewById(R.id.sp_from);
        sp_to = view.findViewById(R.id.sp_to);
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
        swap = view.findViewById(R.id.button_swap);
        button_departure_date = view.findViewById(R.id.button_departure_date);
        button_arrival_date = view.findViewById(R.id.button_arrival_date);

        text_departure_date = view.findViewById(R.id.text_departure_date);
        text_arrival_date = view.findViewById(R.id.text_arrival_date);
        //Dialog
        listFrom = generateListFrom();
        listTo = generateListTo();

        CustomSpinnerAdapter depatureAdapter = new CustomSpinnerAdapter(getContext(), R.layout.custom_item_spinner, listFrom);
        sp_from.setAdapter(depatureAdapter);

        CustomSpinnerAdapter returnAdapter = new CustomSpinnerAdapter(getContext(), R.layout.custom_item_spinner, listTo);
        sp_to.setAdapter(returnAdapter);

        sp_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from_place = listFrom.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });
        sp_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to_place = listTo.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapLocations();
            }
        });
        //Select day
        dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        departureCalendar = Calendar.getInstance();
        arrivalCalendar = Calendar.getInstance();
        arrivalCalendar.add(Calendar.MONTH, 3);
        button_departure_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePicker(true);
                departureDate = text_departure_date.getText().toString();
            }
        });
        button_arrival_date.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showDatePicker(false);
               returnDate = text_arrival_date.getText().toString();
           }
       });
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
                if (!editPeople.getText().toString().isEmpty())
                    numPeo = editPeople.getText().toString();
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
                if (!editBaby.getText().toString().isEmpty()) numBaby = editBaby.getText().toString();

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
                if (!editPet.getText().toString().isEmpty()) numPet = editPet.getText().toString();

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
                if (!editLuggage.getText().toString().isEmpty()) numLuggage = editLuggage.getText().toString();

            }
        });
        imageEconomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBusiness.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
                iconBusiness.setTextColor(ContextCompat.getColor(getContext(), R.color.color_icon));
                class_name = "Economy";
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
                class_name = "Business";
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

                transport_name = "Plane";
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

                transport_name = "Ship";
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

                transport_name = "Train";
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

                transport_name = "Bus";
                imageBus.setImageResource(R.drawable.button_transport);
                iconBus.setImageResource(R.drawable.icon_bus);
                imageBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.booking_picker));
                iconBus.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsValid()) {
                    BookingTransportFlightFragment transportFlightFragment = BookingTransportFlightFragment
                            .newInstance(from_place, to_place, departureDate, returnDate, numPeo, numBaby, numPet, numLuggage, class_name, transport_name);
                    transportFlightFragment.setOnFragmentInteractionListener(new BookingTransportFlightFragment.OnFragmentInteractionListener() {
                        @Override
                        public void onFragmentBack() {
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .remove(transportFlightFragment)
                                    .commit();
                            layout.setVisibility(View.VISIBLE);
                            bottom_navigation_bar.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onFragmentSaveChanges() {

                        }

                        @Override
                        public void onFragmentBackSuccess() {
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .remove(transportFlightFragment)
                                    .commit();
                            mListener.onFragmentBackSuccess();
                            layout.setVisibility(View.VISIBLE);
                            bottom_navigation_bar.setVisibility(View.VISIBLE);
                        }
                    });
                    getChildFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.containerView_booking_flight, transportFlightFragment)
                            .commit();
                    layout.setVisibility(View.GONE);
                    bottom_navigation_bar.setVisibility(View.GONE);
                }
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

    private List<String> generateListTo() {
        List<String> listTo = new ArrayList<>();
        listTo.add("New York (NYC)");
        listTo.add("London (LDN)");
        listTo.add("Paris (PAR)");
        listTo.add("Tokyo (TKY)");
        listTo.add("Los Angeles (LA)");
        listTo.add("Sydney (SYD)");
        listTo.add("Berlin (BER)");
        listTo.add("Rome (ROM)");
        listTo.add("Madrid (MAD)");
        listTo.add("Beijing (BJ)");
        listTo.add("Vietnam (VN)");
        listTo.add("Moscow (MOW)");
        listTo.add("Dubai (DXB)");
        listTo.add("Toronto (TOR)");
        listTo.add("Singapore (SG)");
        listTo.add("Hong Kong (HK)");
        listTo.add("Seoul (SEL)");
        listTo.add("Mexico City (MEX)");
        listTo.add("Sao Paulo (SAO)");
        listTo.add("Mumbai (BOM)");
        listTo.add("Cairo (CAI)");
        return listTo;
    }

    private List<String> generateListFrom() {
        List<String> listFrom = new ArrayList<>();
        listFrom.add("New York (NYC)");
        listFrom.add("London (LDN)");
        listFrom.add("Paris (PAR)");
        listFrom.add("Tokyo (TKY)");
        listFrom.add("Los Angeles (LA)");
        listFrom.add("Sydney (SYD)");
        listFrom.add("Berlin (BER)");
        listFrom.add("Rome (ROM)");
        listFrom.add("Madrid (MAD)");
        listFrom.add("Beijing (BJ)");
        listFrom.add("Vietnam (VN)");
        listFrom.add("Moscow (MOW)");
        listFrom.add("Dubai (DXB)");
        listFrom.add("Toronto (TOR)");
        listFrom.add("Singapore (SG)");
        listFrom.add("Hong Kong (HK)");
        listFrom.add("Seoul (SEL)");
        listFrom.add("Mexico City (MEX)");
        listFrom.add("Sao Paulo (SAO)");
        listFrom.add("Mumbai (BOM)");
        listFrom.add("Cairo (CAI)");
        return listFrom;
    }



    private void showDatePicker(boolean isDeparture) {
        if (!isDeparture) arrivalCalendar = departureCalendar;
        Calendar calendar = isDeparture ? departureCalendar : arrivalCalendar;
        Calendar today = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, month);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                if (isDeparture) {
                    if (selectedDate.before(today)){
                        showToast("Departure date must be after today.");
                    }
                    else if (isDateBefore(selectedDate, arrivalCalendar)) {
                        departureCalendar = selectedDate;
                        updateDateText(departureCalendar, true);
                        if (!isDateAfter(arrivalCalendar, departureCalendar)) {
                            arrivalCalendar = (Calendar) departureCalendar.clone();
                            arrivalCalendar.add(Calendar.DAY_OF_MONTH, 1);
                            updateDateText(arrivalCalendar, false);
                        }
                    } else {
                        showToast("Departure date must be before Return date.");
                    }
                } else {
                    if (selectedDate.before(today)){
                        showToast("Arrival date must be after today.");
                    }
                    if (isDateAfter(selectedDate, departureCalendar)) {
                        arrivalCalendar = selectedDate;
                        updateDateText(arrivalCalendar, false);
                    } else {
                        showToast("Arrival date must be after Departure date.");
                    }
                }
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());
        datePickerDialog.show();
    }
    private boolean isDateBefore(Calendar date1, Calendar date2) {
        // Compare two dates
        return date1.before(date2);
    }

    private boolean isDateAfter(Calendar date1, Calendar date2) {
        // Compare two dates
        return date1.after(date2);
    }
    private void updateDateText(Calendar selectedDate, boolean isDeparture) {
        TextView textViewToUpdate = isDeparture ? text_departure_date : text_arrival_date;
        if (isDeparture) departureDate = dateFormat.format(selectedDate.getTime());
        else returnDate = dateFormat.format(selectedDate.getTime());
        textViewToUpdate.setText(dateFormat.format(selectedDate.getTime()));
    }

    private void swapLocations() {
        int fromPosition = sp_from.getSelectedItemPosition();
        int toPosition = sp_to.getSelectedItemPosition();

        sp_from.setSelection(toPosition);
        sp_to.setSelection(fromPosition);

        String tmp = from_place;
        from_place = to_place;
        to_place = tmp;
    }
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
    private boolean areFieldsValid() {
        if (from_place.equals(to_place)){
            showToast("The departure and return locations cannot be the same.");
            return false;
        }
        if (from_place.isEmpty() || to_place.isEmpty() ||
                departureDate.isEmpty() || returnDate.isEmpty() ||
                numPeo.isEmpty() || sp_from.getSelectedItem() == null ||
                sp_to.getSelectedItem() == null || class_name.isEmpty() ||
                transport_name.isEmpty()) {
            showToast("Please fill in all the fields (except Baby, Pet, Luggage fields)");
            return false;
        }
        return true;
    }
}