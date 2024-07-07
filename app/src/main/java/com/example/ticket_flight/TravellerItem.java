package com.example.ticket_flight;

public class TravellerItem {
    private int id;
    private int isBooked;
    private String rowNum;
    private RowSeatAdapter.SeatType columnName;
    public TravellerItem(int id, RowSeatAdapter.SeatType columnName, String rowNum) {
        this.id = id;
        this.columnName = columnName;
        this.rowNum = rowNum;
        this.isBooked = 0;
    }
    public int getIsBooked() {
        return isBooked;
    }
    public void setIsBooked(int isBooked) {
        this.isBooked = isBooked;
    }
    public int getId() {
        return id;
    }
    public RowSeatAdapter.SeatType getColumnName() {
        return columnName;
    }
    public void setColumnName(RowSeatAdapter.SeatType columnName) {
        this.columnName = columnName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRowNum(){
        return rowNum;
    }
    public void setRowNum(String rowNum){
        this.rowNum = rowNum;
    }
}
