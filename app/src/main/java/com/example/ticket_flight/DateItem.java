package com.example.ticket_flight;

public class DateItem {
    private String date;
    private String day;
    public DateItem(String date, String day) {
       this.date = date;
       this.day = day;
    }
    public String getDay(){
        return day;
    }
    public String getDate(){
        return date;
    }
    public void setDate(){
        this.date = date;
    }
    public void setDay(){
        this.day = day;
    }
}
