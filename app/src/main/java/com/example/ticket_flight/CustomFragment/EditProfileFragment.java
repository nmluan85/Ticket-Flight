package com.example.ticket_flight.CustomFragment;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static java.lang.StrictMath.log;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ticket_flight.AvatarService;
import com.example.ticket_flight.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditProfileFragment extends Fragment {
    private String[] information = new String[5];
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private final AvatarService avatarService = new AvatarService();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private ImageView image_account;

    // TODO: Rename and change types of parameters
    private String first_name, last_name, phone, email;
    private Integer id_image;
    public String[] getInformation(){
        return information;
    }

    public Bitmap getBitmap() {
        Bitmap userAvatar = avatarService.loadImageFromInternalStorage(getContext(), "avatar.png");
        return userAvatar;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentBack();
        void onFragmentSaveChanges();
    }
    private OnFragmentInteractionListener mListener;
    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }
    public EditProfileFragment() {
        // Required empty public constructor
    }
    public static EditProfileFragment newInstance(String param1, String param2, String param3, String param4) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
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
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        EditText edit_first_name = view.findViewById(R.id.edit_first_name);
        EditText edit_last_name = view.findViewById(R.id.edit_last_name);
        EditText edit_phone = view.findViewById(R.id.edit_phone);
        EditText edit_email = view.findViewById(R.id.edit_email);
        image_account = view.findViewById(R.id.image_Account);
        ImageButton image_edit = view.findViewById(R.id.edit_avatar);

        ImageButton back = view.findViewById(R.id.button_back_account);
        ImageButton save = view.findViewById(R.id.button_save_changes);

        edit_first_name.setText(first_name);
        edit_last_name.setText(last_name);
        edit_phone.setText(phone);
        edit_email.setText(email);

        image_account.setImageResource(R.drawable.avatar);

        Bitmap userAvatar = avatarService.loadImageFromInternalStorage(getContext(), "avatar.png");
        if (userAvatar != null) image_account.setImageBitmap(userAvatar);
        else image_account.setImageResource(R.drawable.avatar);
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        image_account.setImageURI(imageUri);
                        // Handle the selected image here
                        avatarService.saveImagePNGfromUri(getContext(), imageUri, "avatar_unsaved.png");
                    }
                }
        );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentBack();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                information[0] = edit_first_name.getText().toString();
                information[1] = edit_last_name.getText().toString();
                information[2] = edit_phone.getText().toString();
                information[3] = edit_email.getText().toString();
                information[4] = String.valueOf(id_image);
                avatarService.copyImage(getContext(), "avatar_unsaved.png", "avatar.png");
                mListener.onFragmentSaveChanges();
            }
        });
        image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                imagePickerLauncher.launch(intent);
            }
        });
        return view;
    }
    public void setAvatar() {
        Bitmap userAvatar = avatarService.loadImageFromInternalStorage(getContext(), "avatar.png");
        if (userAvatar != null) image_account.setImageBitmap(userAvatar);
        else image_account.setImageResource(R.drawable.avatar);
    }
}














