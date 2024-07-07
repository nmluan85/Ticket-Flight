package com.example.ticket_flight.FlightModel;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightSeat implements Parcelable {
    private int seatNumber;
    private boolean isAvailable;
    private int seatType;
    private int price;
    public String getSeatClass() {
        if (seatType == 2) {
            return "Economy";
        }
        return "Business";
    }
    public FlightSeat(int seatNumber, int seatType, int price) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.isAvailable = true;
    }

    protected FlightSeat(Parcel in) {
        seatNumber = in.readInt();
        isAvailable = in.readByte() != 0;
        seatType = in.readInt();
        price = in.readInt();
    }

    public static final Creator<FlightSeat> CREATOR = new Creator<FlightSeat>() {
        @Override
        public FlightSeat createFromParcel(Parcel in) {
            return new FlightSeat(in);
        }

        @Override
        public FlightSeat[] newArray(int size) {
            return new FlightSeat[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(seatNumber);
        dest.writeByte((byte) (isAvailable ? 1 : 0));
        dest.writeInt(seatType);
        dest.writeInt(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Setter and getter methods
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
    public void setAvailable() {
        this.isAvailable = true;
    }

    public void setUnavailable() {
        this.isAvailable = false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public static String convertSeatNumberToSeatCode(int seatNumber) {
        int row = (seatNumber - 1) / 4 + 1; // Calculate the row number (1-based)
        char column = (char) ('A' + (seatNumber - 1) % 4); // Calculate the column letter (A, B, C, or D)
        return row + String.valueOf(column);
    }
}
