package com.example.ticket_flight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> items;
    private LayoutInflater inflater;
    public CustomSpinnerAdapter(Context context, int resource, List<String> items){
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent){
        return createItemView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        return createItemView(position, convertView, parent);
    }
    private View createItemView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(R.layout.custom_item_spinner, parent, false);
        TextView textView = view.findViewById(R.id.text1);
        textView.setText(items.get(position));
        return view;
    }
    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
        animateSpinner();
    }
    private void animateSpinner(){
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(500);
        context.getResources().getAnimation(android.R.anim.fade_in);
    }
}
