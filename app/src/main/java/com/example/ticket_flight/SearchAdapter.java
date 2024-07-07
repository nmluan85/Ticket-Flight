package com.example.ticket_flight;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<SearchItem> listSearch;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public SearchAdapter(List<SearchItem> listSearch){
        this.listSearch = listSearch;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position){
        SearchItem item = listSearch.get(position);
        holder.from.setText(item.getFrom());
        holder.to.setText(item.getTo());
        if (item.getVisibility()){
            holder.arrow.setVisibility(View.VISIBLE);
        } else holder.arrow.setVisibility(View.GONE);
    }
    @Override
    public int getItemCount() {
        return listSearch.size();
    }
    class SearchViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item_search;
        TextView from;
        TextView to;
        ImageView arrow;
        public SearchViewHolder(@NonNull View itemView){
            super(itemView);
            item_search = itemView.findViewById(R.id.layout_search_item);
            from = itemView.findViewById(R.id.search_from);
            to = itemView.findViewById(R.id.search_to);
            arrow = itemView.findViewById(R.id.arrow);
            item_search.setOnClickListener(new View.OnClickListener() {
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
