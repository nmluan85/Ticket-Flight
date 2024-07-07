package com.example.ticket_flight.FlightModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.ticket_flight.FlightModel.FlightSeat;

import java.util.ArrayList;

public class SeatsReservation implements Parcelable {
    private ArrayList<FlightSeat> reservation_seats;
    private int totalPrice = 0;

    public SeatsReservation() {
        this.reservation_seats = new ArrayList<>();
    }

    protected SeatsReservation(Parcel in) {
        reservation_seats = in.createTypedArrayList(FlightSeat.CREATOR);
        totalPrice = in.readInt();
    }

    public static final Creator<SeatsReservation> CREATOR = new Creator<SeatsReservation>() {
        @Override
        public SeatsReservation createFromParcel(Parcel in) {
            return new SeatsReservation(in);
        }

        @Override
        public SeatsReservation[] newArray(int size) {
            return new SeatsReservation[size];
        }
    };

    public void reserveSeat(FlightSeat seat) {
        Log.d("SeatsReservation", "reserveSeat: " + seat.getSeatNumber());
        if (!seat.isAvailable()) return;
        Log.d("SeatsReservation", "reserveSeatSuccessful: " + seat.getSeatNumber());
        reservation_seats.add(seat);
        seat.setUnavailable();
        totalPrice += seat.getPrice();
    }

    public void refundAllSeats() {
        for (FlightSeat seat : reservation_seats) {
            seat.setAvailable();
        }
        reservation_seats.clear();
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public ArrayList<FlightSeat> getReservationSeats() {
        return reservation_seats;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(reservation_seats);
        dest.writeInt(totalPrice);
    }
}
