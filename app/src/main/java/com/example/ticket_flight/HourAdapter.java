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

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    private List<HourItem> listHour;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public HourAdapter(List<HourItem> listHour){
        this.listHour = listHour;
    }
    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hour, parent, false);
        return new HourViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position){
        HourItem item = listHour.get(position);
        holder.content.setText(item.getContent());
        holder.button.setImageResource(R.drawable.button_hour);

        if (item.getIsSelected()){
            holder.button.setImageResource(R.drawable.button_hour_active);
            holder.content.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else {
            holder.button.setImageResource(R.drawable.button_hour);
            holder.content.setTextColor(Color.parseColor("#01635D"));
        }
    }
    public void clearSelection() {
        for (HourItem item : listHour) {
            item.setIsSelected(false);
        }
        selectedPosition = RecyclerView.NO_POSITION;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listHour.size();
    }
    class HourViewHolder extends RecyclerView.ViewHolder {
        ImageButton button;
        TextView content;
        public HourViewHolder(@NonNull View itemView){
            super(itemView);
            button = itemView.findViewById(R.id.button_hour);
            content = itemView.findViewById(R.id.text_hour);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                        for (int i = 0; i < listHour.size(); i++){
                            listHour.get(i).setIsSelected(i == position);
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}

