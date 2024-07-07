package com.example.ticket_flight;

public class TicketItem {
    private String from, from_abb, to, to_abb, date, departure, price, number;
    public TicketItem(String from, String from_abb, String to, String to_abb, String date, String departure, String price, String number){
        this.from = from;
        this.from_abb = from_abb;
        this.to = to;
        this.to_abb = to_abb;
        this.date = date;
        this.departure = departure;
        this.price = price;
        this.number = number;
    }
    public String getFrom(){
        return from;
    }
    public String getFrom_abb(){return from_abb;}
    public  String getTo(){
        return to;
    }
    public String getTo_abb(){return to_abb;}
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
    public void setFrom_abb(String from_abb){
        this.from_abb = from_abb;
    }
    public void setTo(String to){
        this.to = to;
    }
    public void setTo_abb(String to_abb){
        this.to_abb = to_abb;
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
