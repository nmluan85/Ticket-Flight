package com.example.ticket_flight;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
    private List<DateItem> bookingDate;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public DateAdapter(List<DateItem> bookingDate){
        this.bookingDate = bookingDate;
    }
    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new DateViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position){
        DateItem item = bookingDate.get(position);
        holder.day.setText(item.getDay());
        holder.dayNum.setText(item.getDate());

        if (item.getIsSelected()){
            holder.button_day.setBackgroundResource(R.drawable.icon_date_active);
        }
        else {
            holder.button_day.setBackgroundResource(R.drawable.icon_date);
        }
    }
    @Override
    public int getItemCount() {
        return bookingDate.size();
    }
    class DateViewHolder extends RecyclerView.ViewHolder {
        LinearLayout button_day;
        TextView day;
        TextView dayNum;
        public DateViewHolder(@NonNull View itemView){
            super(itemView);
            button_day = itemView.findViewById(R.id.button_day);
            day = itemView.findViewById(R.id.day);
            dayNum = itemView.findViewById(R.id.dayNum);
            button_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                        for (int i = 0; i < bookingDate.size(); i++){
                            bookingDate.get(i).setIsSelected(i == position);
                        }
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
