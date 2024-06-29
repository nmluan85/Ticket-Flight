package com.example.ticket_flight;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private List<BookingItem> bookingList;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public BookingAdapter(List<BookingItem> bookingList){
        this.bookingList = bookingList;
    }
    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingItem booking = bookingList.get(position);
        holder.booking_item.setImageResource(booking.getId_item());
        holder.name_booking.setText(booking.getName_item());
    }
    public int getItemCount() {
        return bookingList.size();
    }
    class BookingViewHolder extends RecyclerView.ViewHolder {
        ImageButton booking_item;
        TextView name_booking;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            booking_item = itemView.findViewById(R.id.booking_service);
            name_booking = itemView.findViewById(R.id.name_booking_service);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
