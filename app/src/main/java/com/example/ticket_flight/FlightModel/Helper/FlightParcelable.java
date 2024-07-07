package com.example.ticket_flight.FlightModel.Helper;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ticket_flight.FlightModel.Flight;
import com.example.ticket_flight.FlightModel.FlightSeat;

public class FlightParcelable implements Parcelable {

    private final Flight flight;

    public FlightParcelable(Flight flight) {
        this.flight = flight;
    }

    protected FlightParcelable(Parcel in) {
        flight = in.readParcelable(Flight.class.getClassLoader());
    }

    public static final Creator<FlightParcelable> CREATOR = new Creator<FlightParcelable>() {
        @Override
        public FlightParcelable createFromParcel(Parcel in) {
            return new FlightParcelable(in);
        }

        @Override
        public FlightParcelable[] newArray(int size) {
            return new FlightParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(flight, flags);
    }

    public Flight getFlight() {
        return flight;
    }
}
