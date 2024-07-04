package com.example.ticket_flight;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private List<TicketItem> tickets;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public TicketAdapter(List<TicketItem> tickets){
        this.tickets = tickets;
    }
    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position){
        TicketItem item = tickets.get(position);
        holder.from.setText(item.getFrom());
        holder.to.setText(item.getTo());
        holder.date.setText(item.getDate());
        holder.departure.setText(item.getDeparture());
        holder.price.setText(item.getPrice());
        holder.number.setText(item.getNumber());
    }
    @Override
    public int getItemCount() {
        return tickets.size();
    }
    class TicketViewHolder extends RecyclerView.ViewHolder {

        ImageButton ticket;
        TextView from, to, date, departure, price, number;
        public TicketViewHolder(@NonNull View itemView){
            super(itemView);
            ticket = itemView.findViewById(R.id.image_button_card);
            from = itemView.findViewById(R.id.from_place);
            to = itemView.findViewById(R.id.to_place);
            date = itemView.findViewById(R.id.date);
            departure = itemView.findViewById(R.id.departure);
            price = itemView.findViewById(R.id.price);
            number = itemView.findViewById(R.id.number);
            ticket.setOnClickListener(new View.OnClickListener() {
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
