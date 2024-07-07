package com.example.ticket_flight;

public class HourItem {
    private String content;
    private boolean isSelected;
    public HourItem(String content) {
        this.content = content;
        this.isSelected = false;
    }
    public String getContent(){
        return content;
    }
    public boolean getIsSelected(){return isSelected;}
    public void setContent(String content){
        this.content = content;
    }
    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
}
