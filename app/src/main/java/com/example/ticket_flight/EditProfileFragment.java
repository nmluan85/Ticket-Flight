package com.example.ticket_flight;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    // TODO: Rename and change types of parameters
    private String first_name, last_name, phone, email;
    private Integer id_image;

    public EditProfileFragment() {
        // Required empty public constructor
    }
    public static EditProfileFragment newInstance(String param1, String param2, String param3, String param4, Integer id_image) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putInt(ARG_PARAM5, id_image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            first_name = getArguments().getString(ARG_PARAM1);
            last_name = getArguments().getString(ARG_PARAM2);
            phone = getArguments().getString(ARG_PARAM3);
            email = getArguments().getString(ARG_PARAM4);
            id_image = getArguments().getInt(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        EditText edit_first_name = view.findViewById(R.id.edit_first_name);
        EditText edit_last_name = view.findViewById(R.id.edit_last_name);
        EditText edit_phone = view.findViewById(R.id.edit_phone);
        EditText edit_email = view.findViewById(R.id.edit_email);

        edit_first_name.setText(first_name);
        edit_last_name.setText(last_name);
        edit_phone.setText(phone);
        edit_email.setText(email);

        ImageButton back = view.findViewById(R.id.button_back_account);
        ImageButton save = view.findViewById(R.id.button_save_changes);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = edit_first_name.getText().toString();
                last_name = edit_last_name.getText().toString();
                phone = edit_phone.getText().toString();
                email = edit_email.getText().toString();
            }
        });
        return view;
    }
}