package com.trivago.hotel.rest.dto;

import java.util.Objects;

public class OfferDto {
    private int advertiserId;
    private String advertiserName;
    private int cpc;
    private int price;
    private String currency;

    public OfferDto() {
    }

    public OfferDto(int advertiserId, String advertiserName, int cpc, int price, String currency) {
        this.advertiserId = advertiserId;
        this.advertiserName = advertiserName;
        this.cpc = cpc;
        this.price = price;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OfferDto offerDto = (OfferDto) o;
        return advertiserId == offerDto.advertiserId &&
                cpc == offerDto.cpc &&
                price == offerDto.price &&
                Objects.equals(advertiserName, offerDto.advertiserName) &&
                Objects.equals(currency, offerDto.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(advertiserId, advertiserName, cpc, price, currency);
    }

    public int getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(int advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
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
}
