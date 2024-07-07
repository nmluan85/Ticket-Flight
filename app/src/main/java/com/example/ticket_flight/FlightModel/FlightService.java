package com.example.ticket_flight.FlightModel;

import java.util.ArrayList;

public class FlightService {
    public static void bookFlight(String flightNumber, String passengerName, String seatNumber) {
        // Implement flight booking logic here
    }

    public static FlightContainer filterFlights(FlightContainer flights, String origin, String destination, String date1, String date2, String time1, String time2, int price) {
        // Implement flight filtering logic here

        return null;
    }

    public static FlightContainer sortFlights(FlightContainer flights, String sortBy) {
        // implement sort flights on arrival time & date, departure time & date, price, duration
        return null;
    }

    public static ArrayList<FlightContainer> searchFlights(FlightContainer flights, String origin, String destination, String date1, String date2) {
        // Implement flight search logic here, return flights categorized by date, for example position 0 of arraylist is for date 1, and so on
        return null;
    }

}
