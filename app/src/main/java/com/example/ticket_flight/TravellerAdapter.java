package com.example.ticket_flight;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.function.LongFunction;

public class TravellerAdapter extends RecyclerView.Adapter<TravellerAdapter.TravellerViewHolder> {
    private List<TravellerItem> listTraveller;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public TravellerAdapter(List<TravellerItem> listTraveller){
        this.listTraveller = listTraveller;
    }
    @NonNull
    @Override
    public TravellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_traveller, parent, false);
        return new TravellerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TravellerViewHolder holder, int position){
        TravellerItem item = listTraveller.get(position);
        String positionString = String.valueOf(position + 1);
        holder.text_traveller.setText(positionString);
        if (item.getIsBooked() == 0){ //default
            holder.button_traveller.setImageResource(R.drawable.traveller_available);
        } else if (item.getIsBooked() == 1){ //booking
            holder.button_traveller.setImageResource(R.drawable.traveller_booking);
        } else if (item.getIsBooked() == 2){ //booked
            holder.button_traveller.setImageResource(R.drawable.traveller_booked);
        }
    }
    public void clearSelection() {
        for (TravellerItem item : listTraveller) {
            item.setIsBooked(0);
        }
        selectedPosition = RecyclerView.NO_POSITION;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listTraveller.size();
    }
    class TravellerViewHolder extends RecyclerView.ViewHolder {
        ImageButton button_traveller;
        TextView text_traveller;
        public TravellerViewHolder(@NonNull View itemView){
            super(itemView);
            button_traveller = itemView.findViewById(R.id.button_traveller);
            text_traveller = itemView.findViewById(R.id.text_traveller);

            button_traveller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position == RecyclerView.NO_POSITION) return;
                    TravellerItem item = listTraveller.get(position);
                    if (item.getIsBooked() == 0){
                        item.setIsBooked(1);
                    } else if (item.getIsBooked() == 2){
                        item.setIsBooked(1);
                    }
                    notifyItemChanged(position);
                    if (listener != null) listener.onItemClick(position);
                }
            });
        }
    }
}

