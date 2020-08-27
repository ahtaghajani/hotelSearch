package com.trivago.hotel.model;

import com.trivago.hotel.csv.dto.HotelCsvDto;

import java.util.Objects;

public class Hotel {
    private int id;
    private String name;
    private int rating;
    private int stars;

    public Hotel(int id, String name, int rating, int stars) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.stars = stars;
    }

    public Hotel(HotelCsvDto hotelCsvDto) {
        this(hotelCsvDto.getId(), hotelCsvDto.getHotelName(), hotelCsvDto.getRating(), hotelCsvDto.getStars());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hotel hotel = (Hotel) o;
        return id == hotel.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getStars() {
        return stars;
    }
}
