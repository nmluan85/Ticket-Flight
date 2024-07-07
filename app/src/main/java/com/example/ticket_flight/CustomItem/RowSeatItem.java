package com.example.ticket_flight.CustomItem;

import com.example.ticket_flight.CustomAdapter.RowSeatAdapter;

public class RowSeatItem {
    private int stateSeatA_i;
    private int stateSeatB_i;
    private int stateSeatC_i;
    private int stateSeatD_i;
    private int row_i;
    private RowSeatAdapter.SeatType selectedSeatType;
    public RowSeatItem(int row_i, int stateSeatA_i, int stateSeatB_i, int stateSeatC_i, int stateSeatD_i) {
        this.row_i = row_i;
        this.stateSeatA_i = stateSeatA_i;
        this.stateSeatB_i = stateSeatB_i;
        this.stateSeatC_i = stateSeatC_i;
        this.stateSeatD_i = stateSeatD_i;
    }
    public int getStateSeatA_i() {
        return stateSeatA_i;
    }
    public void setStateSeatA_i(int stateSeatA_i) {
        this.stateSeatA_i = stateSeatA_i;
    }
    public int getStateSeatB_i(){
        return stateSeatB_i;
    }
    public void setStateSeatB_i(int stateSeatB_i) {
        this.stateSeatB_i = stateSeatB_i;
    }
    public int getStateSeatC_i() {
        return stateSeatC_i;
    }
    public void setStateSeatC_i(int stateSeatC_i) {
        this.stateSeatC_i = stateSeatC_i;
    }
    public int getStateSeatD_i() {
        return stateSeatD_i;
    }
    public void setStateSeatD_i(int stateSeatD_i) {
        this.stateSeatD_i = stateSeatD_i;
    }
    public int getRow_i() {
        return row_i;
    }
    public void setRow_i(int row_i) {
        this.row_i = row_i;
    }
    public RowSeatAdapter.SeatType getSelectedSeatType() {
        return selectedSeatType;
    }

    public void setSelectedSeatType(RowSeatAdapter.SeatType selectedSeatType) {
        this.selectedSeatType = selectedSeatType;
    }
    public void setSeatBooked(RowSeatAdapter.SeatType seatType) {
        switch (seatType) {
            case A:
                stateSeatA_i = 1;
                break;
            case B:
                stateSeatB_i = 1;
                break;
            case C:
                stateSeatC_i = 1;
                break;
            case D:
                stateSeatD_i = 1;
                break;
        }
    }
    public void setSeatSelected(RowSeatAdapter.SeatType seatType) {
        switch (seatType) {
            case A:
                stateSeatA_i = 0;
                break;
            case B:
                stateSeatB_i = 0;
                break;
            case C:
                stateSeatC_i = 0;
                break;
            case D:
                stateSeatD_i = 0;
                break;
        }
    }
}
