package com.example.ticket_flight.CustomAdapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.example.ticket_flight.R;
import com.example.ticket_flight.CustomItem.RowSeatItem;

import java.util.List;

public class RowSeatAdapter extends RecyclerView.Adapter<RowSeatAdapter.RowSeatViewHolder> {
    private List<RowSeatItem> listRowSeat;
    private OnItemClickListener listener;
    private int selectedPosition = RecyclerView.NO_POSITION;
    private Context context;
    private SeatType selectedSeatType;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public RowSeatAdapter(List<RowSeatItem> listRowSeat, Context context){
        this.listRowSeat = listRowSeat;
        this.context = context;
    }
    @NonNull
    @Override
    public RowSeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_seat, parent, false);
        return new RowSeatViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RowSeatViewHolder holder, int position){
        RowSeatItem item = listRowSeat.get(position);
        String positionString = String.valueOf(position + 1);
        holder.row_i.setText(positionString);
        updateStateSeat(holder.button_seat_Ai, item.getStateSeatA_i(), position == selectedPosition && selectedSeatType == SeatType.A);
        updateStateSeat(holder.button_seat_Bi, item.getStateSeatB_i(), position == selectedPosition && selectedSeatType == SeatType.B);
        updateStateSeat(holder.button_seat_Ci, item.getStateSeatC_i(), position == selectedPosition && selectedSeatType == SeatType.C);
        updateStateSeat(holder.button_seat_Di, item.getStateSeatD_i(), position == selectedPosition && selectedSeatType == SeatType.D);
    }
    public enum SeatType { A, B, C, D }
    @Override
    public int getItemCount() {
        return listRowSeat.size();
    }
    class RowSeatViewHolder extends RecyclerView.ViewHolder {
        ImageButton button_seat_Ai;
        ImageButton button_seat_Bi;
        ImageButton button_seat_Ci;
        ImageButton button_seat_Di;
        TextView row_i;
        public RowSeatViewHolder(@NonNull View itemView){
            super(itemView);
            button_seat_Ai = itemView.findViewById(R.id.button_seat_A_i);
            button_seat_Bi = itemView.findViewById(R.id.button_seat_B_i);
            button_seat_Ci = itemView.findViewById(R.id.button_seat_C_i);
            button_seat_Di = itemView.findViewById(R.id.button_seat_D_i);
            row_i = itemView.findViewById(R.id.row_number);

            button_seat_Ai.setOnClickListener(new SeatClickListener(SeatType.A, this));
            button_seat_Bi.setOnClickListener(new SeatClickListener(SeatType.B, this));
            button_seat_Ci.setOnClickListener(new SeatClickListener(SeatType.C, this));
            button_seat_Di.setOnClickListener(new SeatClickListener(SeatType.D, this));
        }
    }
    private class SeatClickListener implements View.OnClickListener {
        private final RowSeatViewHolder holder;
        private final SeatType seatType;

        public SeatClickListener(SeatType seatType, RowSeatViewHolder holder) {
            this.seatType = seatType;
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            int position = holder.getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return;

            RowSeatItem item = listRowSeat.get(position);
            if (isSeatBooked(item, seatType)){
                showToast("This seat is already booked");
                return;
            }

            int previousPosition = selectedPosition;
            RowSeatItem previousItem = null;
            if (previousPosition != RecyclerView.NO_POSITION) {
                previousItem = listRowSeat.get(previousPosition);
                previousItem.setSelectedSeatType(null);
            }

            if (selectedPosition == position && selectedSeatType == seatType) {
                selectedPosition = RecyclerView.NO_POSITION;
                selectedSeatType = null;
                item.setSelectedSeatType(null);
            } else {
                selectedPosition = position;
                selectedSeatType = seatType;
                item.setSelectedSeatType(seatType);
            }
            notifyItemChanged(previousPosition);
            notifyItemChanged(position);
            if (listener != null) listener.onItemClick(position);
        }
    }

    private boolean isSeatBooked(RowSeatItem item, SeatType seatType) {
        switch (seatType) {
            case A:
                return item.getStateSeatA_i() == 1;
            case B:
                return item.getStateSeatB_i() == 1;
            case C:
                return item.getStateSeatC_i() == 1;
            case D:
                return item.getStateSeatD_i() == 1;
            default:
                return false;
        }
    }
    private void updateStateSeat(ImageButton button, int state, boolean isSelected){
        if (isSelected){
            button.setImageResource(R.drawable.icon_seat_selected);
        } else {
            switch (state){
                case 2:
                    button.setImageResource(R.drawable.icon_seat_available);
                    break;
                case 1:
                    button.setImageResource(R.drawable.icon_seat_booked);
                    break;
                case 0:
                    button.setImageResource(R.drawable.icon_seat_selected);
                    break;
            }
        }
    }
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
