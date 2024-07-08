package com.example.ticket_flight.CustomFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ticket_flight.CustomActivity.SignIn;
import com.example.ticket_flight.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    // TODO: Rename and change types of parameters
    private String first_name = "Luan";
    private String last_name = "Nguyen";
    private String phone = "0987654321";
    private String email = "luan@gmail.com";
    ImageView image_account;

    public AccountFragment() {
        // Required empty public constructor
    }
    public static AccountFragment newInstance(String param1, String param2, String param3, String param4, Integer param5) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putInt(ARG_PARAM5, param5);
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
        }
        
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView name = view.findViewById(R.id.name_account);
        TextView info = view.findViewById(R.id.text_personal);
        TextView payment = view.findViewById(R.id.text_payment);
        TextView saved = view.findViewById(R.id.text_saved);
        TextView settings = view.findViewById(R.id.text_settings);
        image_account = view.findViewById(R.id.image_Account);
        ImageButton exit = view.findViewById(R.id.button_end_session);

        name.setText(first_name + " " + last_name);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignIn.class);
                startActivity(intent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileFragment editProfileFragment = EditProfileFragment
                        .newInstance(first_name, last_name, phone, email);
                editProfileFragment.setOnFragmentInteractionListener(new EditProfileFragment.OnFragmentInteractionListener() {
                    @Override
                    public void onFragmentBack() {
                        getChildFragmentManager()
                                .beginTransaction()
                                .remove(editProfileFragment)
                                .commit();
                    }
                    @Override
                    public void onFragmentSaveChanges() {
                        String[] information = editProfileFragment.getInformation();
                        first_name = information[0];
                        last_name = information[1];
                        phone = information[2];
                        email = information[3];
                        getBitmap(editProfileFragment.getBitmap());
                        name.setText(first_name + " " + last_name);
                        onFragmentBack();
                    }
                });
                getChildFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragmentContainer_Account, editProfileFragment).commit();
            }
        });
        return view;
    }
    public void getBitmap(Bitmap image){
        if (image != null){
            image_account.setImageBitmap(image);
        } else {
            image_account.setImageResource(R.drawable.avatar);
        }

    }
}