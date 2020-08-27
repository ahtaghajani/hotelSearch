package com.trivago.hotel.csv.dto;

import com.opencsv.bean.CsvBindByName;

public class HotelAdvertiserCsvDto {

    @CsvBindByName(column = "hotel_id")
    private int hotelId;

    @CsvBindByName(column = "advertiser_id")
    private int advertiserId;

    @CsvBindByName
    private int cpc;

    @CsvBindByName
    private int price;

    @CsvBindByName
    private String currency;

    @CsvBindByName(column = "availability_start_date")
    private int availabilityStartDate;

    @CsvBindByName(column = "availability_end_date")
    private int availabilityEndDate;

    public HotelAdvertiserCsvDto() {
    }

    public HotelAdvertiserCsvDto(int hotelId, int advertiserId, int cpc, int price, String currency, int availabilityStartDate, int availabilityEndDate) {
        this.hotelId = hotelId;
        this.advertiserId = advertiserId;
        this.cpc = cpc;
        this.price = price;
        this.currency = currency;
        this.availabilityStartDate = availabilityStartDate;
        this.availabilityEndDate = availabilityEndDate;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(int advertiserId) {
        this.advertiserId = advertiserId;
    }

    public int getCpc() {
        return cpc;
    }

    public void setCpc(int cpc) {
        this.cpc = cpc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAvailabilityStartDate() {
        return availabilityStartDate;
    }

    public void setAvailabilityStartDate(int availabilityStartDate) {
        this.availabilityStartDate = availabilityStartDate;
    }

    public int getAvailabilityEndDate() {
        return availabilityEndDate;
    }

    public void setAvailabilityEndDate(int availabilityEndDate) {
        this.availabilityEndDate = availabilityEndDate;
    }
}
