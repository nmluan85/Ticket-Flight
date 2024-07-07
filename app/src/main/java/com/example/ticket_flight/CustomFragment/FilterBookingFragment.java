package com.example.ticket_flight.CustomFragment;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ticket_flight.CustomAdapter.HourAdapter;
import com.example.ticket_flight.CustomItem.HourItem;
import com.example.ticket_flight.R;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterBookingFragment extends Fragment {

    final float stepSize = 5.0f;
    final float valueFrom = 0.0f;
    final float valueTo = 500.0f;
    List<HourItem> hourItems = new ArrayList<>();
    private String arrivalTime, departureTime, typeSort, minPrice, maxPrice;
    private RangeSlider rangeSlider;
    private EditText editFrom, editTo;
    private RadioButton arrival, departure, price, fare, duration;
    private RadioGroup radioGroup;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
    }
    public String[] getInformation(){
        return new String[]{arrivalTime, departureTime, typeSort, minPrice, maxPrice};
    }
    private OnFragmentInteractionListener mListener;
    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }
    public FilterBookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilterBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilterBookingFragment newInstance(String param1, String param2) {
        FilterBookingFragment fragment = new FilterBookingFragment();
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
        View view = inflater.inflate(R.layout.fragment_filter_booking, container, false);
        RecyclerView recyclerView_departure = view.findViewById(R.id.recycler_view_booking_hour_departure);
        RecyclerView recyclerView_arrival = view.findViewById(R.id.recycler_view_booking_hour_arrival);
        rangeSlider = view.findViewById(R.id.range_slider);
        editFrom = view.findViewById(R.id.edit_from_price);
        editTo = view.findViewById(R.id.edit_to_price);
        arrival = view.findViewById(R.id.radio_button_arrival);
        departure = view.findViewById(R.id.radio_button_departure);
        price = view.findViewById(R.id.radio_button_price);
        fare = view.findViewById(R.id.radio_button_lowest_fare);
        duration = view.findViewById(R.id.radio_button_duration);
        radioGroup = view.findViewById(R.id.radio_group_sort);

        ImageButton reset = view.findViewById(R.id.button_reset);
        ImageButton done = view.findViewById(R.id.button_done);
        ImageButton back = view.findViewById(R.id.button_back_flight);

        recyclerView_departure.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView_arrival.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        hourItems.add(new HourItem("12AM-06AM"));
        hourItems.add(new HourItem("06AM-12PM"));
        hourItems.add(new HourItem("12PM-06PM"));
        hourItems.add(new HourItem("06PM-12AM"));
        HourAdapter hourAdapter_departure = new HourAdapter(hourItems);
        HourAdapter hourAdapter_arrival = new HourAdapter(hourItems);

        recyclerView_arrival.setAdapter(hourAdapter_arrival);
        recyclerView_departure.setAdapter(hourAdapter_departure);

        if (rangeSlider.getValues().size() == 2) {
            editFrom.setText("$" + String.valueOf(rangeSlider.getValues().get(0)));
            editTo.setText("$" + String.valueOf(rangeSlider.getValues().get(1)));
        }
        rangeSlider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider rangeSlider, float v, boolean b) {
                if (rangeSlider.getValues().size() == 2) {
                    editFrom.setText("$" + String.valueOf(rangeSlider.getValues().get(0)));
                    editTo.setText("$" + String.valueOf(rangeSlider.getValues().get(1)));
                }
            }
        });
        editFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                if (!input.startsWith("$")) {
                    editFrom.setText("$");
                    editFrom.setSelection(editFrom.getText().length());
                    return;
                }
                try {
                    String valueStr = input.substring(1);  // Remove the dollar sign
                    float fromValue;
                    if (valueStr.isEmpty()) {
                        fromValue = valueFrom;  // Set to 0 if empty
                    } else {
                        fromValue = Float.parseFloat(valueStr);
                        fromValue = Math.round(fromValue / stepSize) * stepSize;  // Adjust to nearest multiple of stepSize
                        fromValue = Math.max(valueFrom, Math.min(fromValue, valueTo));  // Ensure within range
                    }
                    List<Float> currentValues = rangeSlider.getValues();
                    if (currentValues.size() == 2) {
                        rangeSlider.setValues(fromValue, currentValues.get(1));
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input (e.g., non-numeric string)
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                minPrice = input.substring(1);
            }
        });
        editTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();
                if (!input.startsWith("$")) {
                    editTo.setText("$");
                    editTo.setSelection(editTo.getText().length());
                    return;
                }

                try {
                    String valueStr = input.substring(1);
                    float toValue;
                    if (valueStr.isEmpty()) {
                        toValue = valueTo;
                    } else {
                        toValue = Float.parseFloat(valueStr);
                        toValue = Math.round(toValue / stepSize) * stepSize;  // Adjust to nearest multiple of stepSize
                        toValue = Math.max(valueFrom, Math.min(toValue, valueTo));  // Ensure within range
                    }
                    List<Float> currentValues = rangeSlider.getValues();
                    if (currentValues.size() == 2) {
                        rangeSlider.setValues(currentValues.get(0), toValue);
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input (e.g., non-numeric string)
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                 maxPrice = input.substring(1);
            }
        });
        hourAdapter_arrival.setOnItemClickListener(new HourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                arrivalTime = hourItems.get(position).getContent();
            }
        });
        hourAdapter_departure.setOnItemClickListener(new HourAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                departureTime = hourItems.get(position).getContent();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = view.findViewById(checkedId);
                if (radioButton != null) {
                    typeSort = radioButton.getText().toString();
                }
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentBack();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rangeSlider.setValues(valueFrom, valueTo);
                editFrom.setText("$" + valueFrom);
                editTo.setText("$" + valueTo);
                radioGroup.clearCheck();
                hourAdapter_arrival.clearSelection();
                hourAdapter_departure.clearSelection();
                arrivalTime = null;
                departureTime = null;
                typeSort = null;
                minPrice = null;
                maxPrice = null;
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