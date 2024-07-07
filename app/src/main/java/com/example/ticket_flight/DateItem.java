package com.example.ticket_flight;

public class DateItem {
    private String date;
    private String day;
    private boolean isSelected;
    public DateItem(String date, String day) {
       this.date = date;
       this.day = day;
       this.isSelected = false;
    }
    public String getDay(){
        return day;
    }
    public String getDate(){
        return date;
    }
    public boolean getIsSelected(){
        return isSelected;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setDay(String day){
        this.day = day;
    }
    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
}
