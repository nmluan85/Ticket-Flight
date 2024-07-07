package com.example.ticket_flight.FlightModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightData {
    private static FlightData instance;
    private Map<String, FlightContainer> flightContainers;
    private Random random;
    private SimpleDateFormat sdf;

    private FlightData(long seed) {
        flightContainers = new HashMap<>();
        random = new Random(seed);
        sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    public static synchronized FlightData getInstance(long seed) {
        if (instance == null) {
            instance = new FlightData(seed);
        }
        return instance;
    }

    public Map<String, FlightContainer> getFlightContainers() {
        return flightContainers;
    }

    public void generateDataForDate(String date) {
        if (!flightContainers.containsKey(date)) {
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(sdf.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            generateDataForCalendar(calendar);
        }
    }

    public void generateDataForRange(String startDate, String endDate) {
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        try {
            startCalendar.setTime(sdf.parse(startDate));
            endCalendar.setTime(sdf.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        while (!startCalendar.after(endCalendar)) {
            String date = sdf.format(startCalendar.getTime());
            if (!flightContainers.containsKey(date)) {
                generateDataForCalendar(startCalendar);
            }
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
    }


    private void generateDataForCalendar(Calendar calendar) {
        String date = sdf.format(calendar.getTime());
        FlightContainer flightContainer = new FlightContainer();
        int numberOfFlights = random.nextInt(5) + 8; // Between 8 and 12 flights

        for (int i = 0; i < numberOfFlights; i++) {
            Flight flight = generateRandomFlight(calendar);
            flightContainer.addFlight(flight);
        }

        flightContainers.put(date, flightContainer);
    }

    private Flight generateRandomFlight(Calendar calendar) {
        String[] destinations = {"Tokyo", "London", "Boston"};
        String[] origins = {"Boston", "Tokyo", "London"};

        String origin = origins[random.nextInt(origins.length)];
        String destination;
        do {
            destination = destinations[random.nextInt(destinations.length)];
        } while (origin.equals(destination));

        String flightNumber = "F" + (random.nextInt(900) + 100);
        String date = sdf.format(calendar.getTime());
        String departureTime = String.format("%02d:00", random.nextInt(24));
        String arrivalTime = String.format("%02d:00", (random.nextInt(24 - Integer.parseInt(departureTime.split(":")[0])) + Integer.parseInt(departureTime.split(":")[0])) % 24);

        int firstClassPrice = generateRandomPrice(true);
        int economyClassPrice = generateRandomPrice(false);

        FlightSeat[][] seats = new FlightSeat[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                int price = (i < 2) ? firstClassPrice : economyClassPrice;
                seats[i][j] = new FlightSeat(i * 4 + j + 1, (i < 2) ? 1 : 2, price);
            }
        }

        return new Flight(flightNumber, origin, destination, date, departureTime, seats, date, arrivalTime);
    }

    private int generateRandomPrice(boolean isFirstClass) {
        if (isFirstClass) {
            return random.nextInt(201) + 300; // Prices between 300 and 500 for first class
        } else {
            return random.nextInt(101) + 100; // Prices between 100 and 200 for economy class
        }
    }

    public static String getDayAbbreviation(String dayOfWeek) {
        switch (dayOfWeek) {
            case "Sunday":
                return "SU";
            case "Monday":
                return "MO";
            case "Tuesday":
                return "TU";
            case "Wednesday":
                return "WE";
            case "Thursday":
                return "TH";
            case "Friday":
                return "FR";
            case "Saturday":
                return "SA";
            default:
                return "";
        }
    }
}
//public class Main {
//    public static void main(String[] args) {
//        long seed = 12345L;
//        FlightData flightData = FlightData.getInstance(seed);
//
//        // Generate data for a range of dates
//        String startDate = "2024-07-01";
//        String endDate = "2024-07-10";
//        flightData.generateDataForRange(startDate, endDate);
//
//        // Retrieve flight data for the range of dates
//        Calendar calendar = Calendar.getInstance();
//        try {
//            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startDate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        while (!calendar.after(endDate)) {
//            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
//            FlightContainer flightContainer = flightData.getFlightContainers().get(date);
//            if (flightContainer != null) {
//                List<Flight> flights = flightContainer.getFlights();
//                System.out.println("Flights on " + date + ":");
//                for (Flight flight : flights) {
//                    System.out.println(flight.getFlightNumber() + " - " + flight.getOrigin() + " to " + flight.getDestination());
//                }
//            }
//            calendar.add(Calendar.DAY_OF_YEAR, 1);
//        }
//    }
//}


