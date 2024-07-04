package com.example.ticket_flight;

public class HourItem {
    private String from;
    private String to;
    public HourItem(String from, String day) {
        this.from = from;
        this.to = to;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public void setDate(){
        this.from = from;
    }
    public void setDay(){
        this.to = to;
    }
}
