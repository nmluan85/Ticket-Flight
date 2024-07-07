package com.example.ticket_flight.FlightModel;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Flight implements Parcelable {
    public String number;
    public String origin;
    public String destination;
    public String departure_date;
    public String departure_time;
    public String arrival_date;
    public String arrival_time;
    private FlightSeat[][] seats;
    public String getDuration() {
        // Ensure compatibility with devices running Android O and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate departureDate = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime departureTime = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDate arrivalDate = LocalDate.parse(arrival_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime arrivalTime = LocalTime.parse(arrival_time, DateTimeFormatter.ofPattern("HH:mm"));

            // Combine date and time into LocalDateTime
            LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
            LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);

            // Calculate the duration between the two LocalDateTime instances
            Duration duration = Duration.between(departureDateTime, arrivalDateTime);

            // Convert the duration to hours and minutes
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;

            // Return the duration as a formatted string
            return String.format("%d hours %d minutes", hours, minutes);
        } else {
            // For devices running on Android versions below O, handle appropriately
            // You can either throw an exception or use an alternative method to calculate the duration
            throw new UnsupportedOperationException("This method requires Android O or above");
        }
    }

    public String getArrivalTime() {
        return arrival_time;
    }
    public String getDepartureTime() {
        return departure_time;

    }
    public String getDepartureDate() {
        return departure_date;
    }

    public String getOrigin() {
        return origin;
    }
    public String getDestination() {
        return destination;
    }

    public Flight(String number, String origin, String destination, String departure_date, String departure_time, FlightSeat[][] seats, String arrival_date, String arrival_time) {
        this.number = number;
        this.origin = origin;
        this.destination = destination;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.seats = seats;
    }

    protected Flight(Parcel in) {
        number = in.readString();
        origin = in.readString();
        destination = in.readString();
        departure_date = in.readString();
        departure_time = in.readString();
        arrival_date = in.readString();
        arrival_time = in.readString();

        // Read the 2D array from the parcel
        int rows = in.readInt();
        int cols = in.readInt();
        seats = new FlightSeat[rows][cols];
        for (int i = 0; i < rows; i++) {
            in.readTypedArray(seats[i], FlightSeat.CREATOR);
        }
    }

    public static final Creator<Flight> CREATOR = new Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel in) {
            return new Flight(in);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(number);
        parcel.writeString(origin);
        parcel.writeString(destination);
        parcel.writeString(departure_date);
        parcel.writeString(departure_time);
        parcel.writeString(arrival_date);
        parcel.writeString(arrival_time);

        // Write the 2D array to the parcel
        int rows = seats.length;
        int cols = seats[0].length;
        parcel.writeInt(rows);
        parcel.writeInt(cols);
        for (int i = 0; i < rows; i++) {
            parcel.writeTypedArray(seats[i], flags);
        }
    }

    // Method to get the cheapest price of this flight
    public int getCheapestPrice() {
        int cheapestPrice = Integer.MAX_VALUE;
        for (FlightSeat[] row : seats) {
            for (FlightSeat seat : row) {
                if (seat.getPrice() < cheapestPrice) {
                    cheapestPrice = seat.getPrice();
                }
            }
        }
        return cheapestPrice;
    }

    public int getHighestPrice() {
        int highestPrice = Integer.MIN_VALUE;
        for (FlightSeat[] row : seats) {
            for (FlightSeat seat : row) {
                if (seat.getPrice() > highestPrice) {
                    highestPrice = seat.getPrice();
                }
            }
        }
        return highestPrice;
    }

    // Other methods...

    public String getFlightNumber() {
        return number;
    }

    public FlightSeat[][] getSeats() {
        return seats;
    }

    public static boolean checkCityOrigin(String origin) {
        List<String> validCities = Arrays.asList("Tokyo", "London", "Boston");
        return validCities.contains(origin);
    }

    public static ArrayList<String> getValidCities() {
        ArrayList<String> validCities = new ArrayList<>();
        validCities.add("Tokyo");
        validCities.add("London");
        validCities.add("Boston");
        return validCities;
    }

    public static String[] validCities = {"Tokyo", "London", "Boston"};

    public static boolean checkCityDestination(String destination) {
        List<String> validCities = Arrays.asList("Tokyo", "London", "Boston");
        return validCities.contains(destination);
    }

    public static boolean checkDepartureDate(String departure_date) {
        LocalDate currentDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        LocalDate departureDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            departureDate = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return !departureDate.isBefore(currentDate);
        }
        return false;
    }

    public static boolean checkArrivalDate(String departure_date, String arrival_date) {
        LocalDate currentDate = null;
        LocalDate arrivalDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        LocalDate departureDate = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            departureDate = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            arrivalDate = LocalDate.parse(arrival_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return !departureDate.isBefore(currentDate) && departureDate.isBefore(arrivalDate);
        }
        return false;
    }

    public boolean checkTime() {
        LocalTime startTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startTime = LocalTime.of(5, 0);
        }
        LocalTime endTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            endTime = LocalTime.of(23, 0);
        }
        LocalTime departureTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            departureTime = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("HH:mm"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return !departureTime.isBefore(startTime) && !departureTime.isAfter(endTime);
        }
        return false;
    }

    public String formatted_date() {
        LocalDate date = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date = LocalDate.parse(departure_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        }
        return null;
    }

    public String formatted_time() {
        LocalTime time = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            time = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("HH:mm"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }
        return null;
    }

    public static String abbreviated_city(String city) {
        Map<String, String> cityAbbreviations = new HashMap<>();
        cityAbbreviations.put("Tokyo", "TKO");
        cityAbbreviations.put("London", "LDN");
        cityAbbreviations.put("Boston", "BOS");

        return cityAbbreviations.getOrDefault(city, city);
    }

    public static String getMonthDay(String dateStr) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat targetFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        try {
            return targetFormat.format(originalFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr; // Return the original date string if parsing fails
        }
    }

}

//public class Main {
//    public static void main(String[] args) {
//        long seed = 12345L;
//        String startDate = "2024-07-01";
//        FlightData flightData = FlightData.getInstance(seed, startDate);
//
//        FlightContainer flightContainerForDay10 = flightData.getFlightContainers().get(9); // Get flights for the 10th day from the start date
//        if (flightContainerForDay10 != null) {
//            List<Flight> flightsForDay10 = flightContainerForDay10.getFlights();
//
//            for (Flight flight : flightsForDay10) {
//                System.out.println(flight.getFlightNumber() + " - " + flight.getOrigin() + " to " + flight.getDestination());
//                int cheapestPrice = flight.getCheapestPrice();
//                System.out.println("Cheapest price: $" + cheapestPrice);
//            }
//        }
//    }
//}

