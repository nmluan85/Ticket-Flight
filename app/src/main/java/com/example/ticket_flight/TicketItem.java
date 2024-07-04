package com.example.ticket_flight;

public class TicketItem {
    private String from, to, date, departure, price, number;
    public TicketItem(String from, String to, String date, String departure, String price, String number){
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
        this.price = price;
        this.number = number;
    }
    public String getFrom(){
        return from;
    }
    public  String getTo(){
        return to;
    }
    public String getDate(){
        return date;
    }
    public String getDeparture() {
        return departure;
    }
    public String getPrice() {
        return price;
    }
    public String getNumber() {
        return number;
    }
    public void setFrom(String from){
        this.from = from;
    }
    public void setTo(String to){
        this.to = to;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setDeparture(String departure){
        this.departure = departure;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
