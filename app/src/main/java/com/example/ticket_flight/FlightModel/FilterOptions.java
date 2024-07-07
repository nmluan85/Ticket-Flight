package com.example.ticket_flight.FlightModel;

import android.os.Parcel;
import android.os.Parcelable;

public class FilterOptions implements Parcelable {
    private String departureStartTime;
    private String departureEndTime;
    private String arrivalStartTime;
    private String arrivalEndTime;
    private int minPrice;
    private int maxPrice;
    private String sortOption;

    // Constructor, Getters, and Setters

    public FilterOptions(String departureStartTime, String departureEndTime, String arrivalStartTime, String arrivalEndTime, int minPrice, int maxPrice, String sortOption) {
        this.departureStartTime = departureStartTime;
        this.departureEndTime = departureEndTime;
        this.arrivalStartTime = arrivalStartTime;
        this.arrivalEndTime = arrivalEndTime;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortOption = sortOption;
    }

    protected FilterOptions(Parcel in) {
        departureStartTime = in.readString();
        departureEndTime = in.readString();
        arrivalStartTime = in.readString();
        arrivalEndTime = in.readString();
        minPrice = in.readInt();
        maxPrice = in.readInt();
        sortOption = in.readString();
    }

    public static final Creator<FilterOptions> CREATOR = new Creator<FilterOptions>() {
        @Override
        public FilterOptions createFromParcel(Parcel in) {
            return new FilterOptions(in);
        }

        @Override
        public FilterOptions[] newArray(int size) {
            return new FilterOptions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(departureStartTime);
        dest.writeString(departureEndTime);
        dest.writeString(arrivalStartTime);
        dest.writeString(arrivalEndTime);
        dest.writeInt(minPrice);
        dest.writeInt(maxPrice);
        dest.writeString(sortOption);
    }

    // Getters and Setters
    public String getDepartureStartTime() {
        return departureStartTime;
    }

    public void setDepartureStartTime(String departureStartTime) {
        this.departureStartTime = departureStartTime;
    }

    public String getDepartureEndTime() {
        return departureEndTime;
    }

    public void setDepartureEndTime(String departureEndTime) {
        this.departureEndTime = departureEndTime;
    }

    public String getArrivalStartTime() {
        return arrivalStartTime;
    }

    public void setArrivalStartTime(String arrivalStartTime) {
        this.arrivalStartTime = arrivalStartTime;
    }

    public String getArrivalEndTime() {
        return arrivalEndTime;
    }

    public void setArrivalEndTime(String arrivalEndTime) {
        this.arrivalEndTime = arrivalEndTime;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSortOption() {
        return sortOption;
    }

    public void setSortOption(String sortOption) {
        this.sortOption = sortOption;
    }
}
