package com.example.ticket_flight.FlightModel;

public class FlightTicket {
    private String flightNumber;
    private String passengerName;
    private String seatNumber;

    private boolean accompaniedByChild;

    public FlightTicket(String flightNumber, String passengerName, String seatNumber, boolean accompaniedByChild) {
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.accompaniedByChild = accompaniedByChild;
    }

    public boolean isAccompaniedByChild() {
        return accompaniedByChild;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
