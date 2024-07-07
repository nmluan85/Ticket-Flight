package com.example.ticket_flight.CustomItem;

import android.opengl.Visibility;
import android.view.View;

public class SearchItem {
    private String from;
    private String to;
    private boolean visibility;

    public SearchItem(String from, String to, boolean visibility) {
        this.from = from;
        this.to = to;
        this.visibility = visibility;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
