package com.example.ticket_flight;

public class BookingItem {
    private Integer id_item;
    private String name_item;
    public BookingItem(Integer id_item, String name_item) {
        this.id_item = id_item;
        this.name_item = name_item;
    }
    public Integer getId_item(){
        return id_item;
    }
    public String getName_item(){
        return name_item;
    }
    public void setId_item(Integer id_item){
        this.id_item = id_item;
    }
    public void setName_item(String name_item) {
        this.name_item = name_item;
    }
}
