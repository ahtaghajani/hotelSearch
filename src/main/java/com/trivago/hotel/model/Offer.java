package com.trivago.hotel.model;

public class Offer {
    private Hotel hotel;
    private Advertiser advertiser;
    private int cpc;
    private int price;
    private String currency;
    private int availabilityStartDate;
    private int availabilityEndDate;

    public Offer(Hotel hotel, Advertiser advertiser, int cpc, int price, String currency, int availabilityStartDate, int availabilityEndDate) {
        this.hotel = hotel;
        this.advertiser = advertiser;
        this.cpc = cpc;
        this.price = price;
        this.currency = currency;
        this.availabilityStartDate = availabilityStartDate;
        this.availabilityEndDate = availabilityEndDate;
    }

    public synchronized void updatePrice(int cpc, int price, String currency) {
        this.cpc = cpc;
        this.price = price;
        this.currency = currency;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public int getCpc() {
        return cpc;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAvailabilityStartDate() {
        return availabilityStartDate;
    }

    public int getAvailabilityEndDate() {
        return availabilityEndDate;
    }
}
