package com.example.ticket_flight.FlightModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilterFlight implements Parcelable {
    public String[] options = {"Arrival time", "Departure time", "Price", "Duration"};

    private int classType;
    private FilterOptions filterOptions;

    public FilterOptions getFilterOptions() {
        return filterOptions;
    }

    public void setFilterOptions(FilterOptions filterOptions) {
        this.filterOptions = filterOptions;
    }

    public FilterFlight(int classType) {
        this.classType = classType;
        this.filterOptions = new FilterOptions("06:00", "12:00", "00:00", "06:00", 0, 500, options[2]);
    }

    protected FilterFlight(Parcel in) {
        classType = in.readInt();
        filterOptions = in.readParcelable(FilterOptions.class.getClassLoader());
    }

    public static final Creator<FilterFlight> CREATOR = new Creator<FilterFlight>() {
        @Override
        public FilterFlight createFromParcel(Parcel in) {
            return new FilterFlight(in);
        }

        @Override
        public FilterFlight[] newArray(int size) {
            return new FilterFlight[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(classType);
        dest.writeParcelable(filterOptions, flags);
    }

    public FlightContainer filterFlights(FlightContainer flights) {
        FlightContainer filteredFlights = new FlightContainer();

        // Filter by departure time
        filteredFlights = filterByDepartureTime(flights, filterOptions.getDepartureStartTime(), filterOptions.getDepartureEndTime());

        // Filter by arrival time
        filteredFlights = filterByArrivalTime(filteredFlights, filterOptions.getArrivalStartTime(), filterOptions.getArrivalEndTime());

        // Filter by price
        filteredFlights = filterByPrice(filteredFlights, filterOptions.getMinPrice(), filterOptions.getMaxPrice());

        // Sort by the selected option
        filteredFlights = filterBySortOption(filteredFlights, filterOptions.getSortOption());

        return filteredFlights;
    }

    private FlightContainer filterByDepartureTime(FlightContainer flights, String startTime, String endTime) {
        FlightContainer filteredFlights = new FlightContainer();
        for (Flight flight : flights.getFlights()) {
            if (isTimeInRange(flight.getDepartureTime(), startTime, endTime)) {
                filteredFlights.addFlight(flight);
            }
        }
        return filteredFlights;
    }

    private FlightContainer filterByArrivalTime(FlightContainer flights, String startTime, String endTime) {
        FlightContainer filteredFlights = new FlightContainer();
        for (Flight flight : flights.getFlights()) {
            if (isTimeInRange(flight.getArrivalTime(), startTime, endTime)) {
                filteredFlights.addFlight(flight);
            }
        }
        return filteredFlights;
    }

    private FlightContainer filterByPrice(FlightContainer flights, int minPrice, int maxPrice) {
        FlightContainer filteredFlights = new FlightContainer();
        for (Flight flight : flights.getFlights()) {
            int price = (classType == 1) ? flight.getHighestPrice() : flight.getCheapestPrice();
            if (price >= minPrice && price <= maxPrice) {
                filteredFlights.addFlight(flight);
            }
        }
        return filteredFlights;
    }

    private FlightContainer filterBySortOption(FlightContainer flights, String sortOption) {
        List<Flight> sortedFlights = new ArrayList<>(flights.getFlights());
        switch (sortOption) {
            case "Arrival time":
                sortedFlights.sort(Comparator.comparing(Flight::getArrivalTime));
                break;
            case "Departure time":
                sortedFlights.sort(Comparator.comparing(Flight::getDepartureTime));
                break;
            case "Price":
                if (classType == 1) {
                    sortedFlights.sort(Comparator.comparing(Flight::getHighestPrice));
                } else {
                    sortedFlights.sort(Comparator.comparing(Flight::getCheapestPrice));
                }
                break;
            case "Duration":
                sortedFlights.sort(Comparator.comparing(Flight::getDuration));
                break;
        }
        FlightContainer sortedFlightContainer = new FlightContainer();
        for (Flight flight : sortedFlights) {
            sortedFlightContainer.addFlight(flight);
        }
        return sortedFlightContainer;
    }

    private boolean isTimeInRange(String time, String startTime, String endTime) {
        int timeInt = Integer.parseInt(time.replace(":", ""));
        int startTimeInt = Integer.parseInt(startTime.replace(":", ""));
        int endTimeInt = Integer.parseInt(endTime.replace(":", ""));
        return timeInt >= startTimeInt && timeInt <= endTimeInt;
    }
}
