package com.trivago.hotel.csv.dto;

import com.opencsv.bean.CsvBindByName;

public class HotelCsvDto {

    @CsvBindByName
    private int id;

    @CsvBindByName
    private int clicks;

    @CsvBindByName
    private int impressions;

    @CsvBindByName(column = "name")
    private String hotelName;

    @CsvBindByName
    private int rating;

    @CsvBindByName
    private int stars;

    @CsvBindByName(column = "city_id")
    private int cityId;

    public HotelCsvDto() {
    }

    public HotelCsvDto(int id, int clicks, int impressions, String hotelName, int rating, int stars, int cityId) {
        this.id = id;
        this.clicks = clicks;
        this.impressions = impressions;
        this.hotelName = hotelName;
        this.rating = rating;
        this.stars = stars;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
