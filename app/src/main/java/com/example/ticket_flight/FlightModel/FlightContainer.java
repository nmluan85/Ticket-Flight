package com.example.ticket_flight.FlightModel;

import java.util.ArrayList;

public class FlightContainer {
    private ArrayList<Flight> flights;
    public FlightContainer() {
        flights = new ArrayList<Flight>();
    }
    public void addFlight(Flight flight) {
        flights.add(flight);
    }
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public FlightContainer filterFlights(String origin, String destination) {
        FlightContainer filteredFlights = new FlightContainer();
        for (Flight flight : flights) {
            if (flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)) {
                filteredFlights.addFlight(flight);
            }
        }
        return filteredFlights;
    }

}
