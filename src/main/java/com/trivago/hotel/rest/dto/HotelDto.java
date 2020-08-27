package com.trivago.hotel.rest.dto;

import java.util.List;
import java.util.Objects;

public class HotelDto {

    private int hotelId;
    private String hotelName;
    private int rating;
    private int stars;
    private List<OfferDto> offers;

    public HotelDto() {
    }

    public HotelDto(int hotelId, String hotelName, int rating, int stars, List<OfferDto> offers) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.rating = rating;
        this.stars = stars;
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HotelDto hotelDto = (HotelDto) o;
        return hotelId == hotelDto.hotelId &&
                rating == hotelDto.rating &&
                stars == hotelDto.stars &&
                Objects.equals(hotelName, hotelDto.hotelName) &&
                Objects.equals(offers, hotelDto.offers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hotelId, hotelName, rating, stars, offers);
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public List<OfferDto> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferDto> offers) {
        this.offers = offers;
    }
}
