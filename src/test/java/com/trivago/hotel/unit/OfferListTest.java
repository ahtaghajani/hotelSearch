package com.trivago.hotel.unit;

import com.trivago.hotel.model.Advertiser;
import com.trivago.hotel.model.Hotel;
import com.trivago.hotel.model.Offer;
import com.trivago.hotel.util.OfferList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferListTest {

    @Test
    void addTest() {
        Hotel hotel = new Hotel(554, "Hotel test", 10, 5);
        Advertiser advertiser = new Advertiser(100, "Advertise test");
        Offer offer = new Offer(hotel, advertiser, 50, 38, "EUR", 20200808, 20200810);

        OfferList offerList = new OfferList();
        offerList.addOffer(offer);

        assertEquals(1, offerList.getByStartDateAndEndDate(20200808, 20200810).size());
        assertEquals(offer, offerList.getByStartDateAndEndDate(20200808, 20200810).get(0));
    }

    @Test
    void removeTest() {
        LocalDate now = LocalDate.now();

        int nowPlus5Days = formatDateAsInt(now.plusDays(5));
        int nowMinus5Days = formatDateAsInt(now.minusDays(5));
        int nowMinus2Days = formatDateAsInt(now.minusDays(2));
        int nowMinus4Days = formatDateAsInt(now.minusDays(4));
        int nowMinus9Days = formatDateAsInt(now.minusDays(9));

        Hotel hotel = new Hotel(554, "Hotel test", 10, 5);
        Advertiser advertiser = new Advertiser(100, "Advertise test");
        OfferList offerList = new OfferList();
        offerList.addOffer(new Offer(hotel, advertiser, 50, 38, "EUR", nowMinus4Days, nowPlus5Days));
        offerList.addOffer(new Offer(hotel, advertiser, 50, 38, "EUR", nowMinus5Days, nowMinus2Days));
        offerList.addOffer(new Offer(hotel, advertiser, 50, 38, "EUR", nowMinus9Days, nowMinus2Days));

        List<Offer> offerListSortedByStartDate = offerList.getByStartDateAndEndDate(nowMinus4Days, nowMinus2Days);
        assertEquals(3, offerListSortedByStartDate.size());
        assertEquals(nowMinus9Days, offerListSortedByStartDate.get(0).getAvailabilityStartDate());
        assertEquals(nowMinus5Days, offerListSortedByStartDate.get(1).getAvailabilityStartDate());
        assertEquals(nowMinus4Days, offerListSortedByStartDate.get(2).getAvailabilityStartDate());

        offerList.removeExpiredOffers();
        List<Offer> offerListAfterRemoveExpired = offerList.getByStartDateAndEndDate(nowMinus4Days, nowMinus2Days);
        assertEquals(1, offerListAfterRemoveExpired.size());
        assertEquals(nowMinus4Days, offerListAfterRemoveExpired.get(0).getAvailabilityStartDate());

    }

    private int formatDateAsInt(LocalDate date) {
        String dateString = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        return Integer.parseInt(dateString);
    }

    @Test
    void orderOfInsertionTest() {
        Offer offer1 = new Offer(null, null, 50, 38, "EUR", 20200810, 20201010);
        Offer offer2 = new Offer(null, null, 50, 38, "EUR", 20200802, 20201010);
        Offer offer3 = new Offer(null, null, 50, 38, "EUR", 20200829, 20201010);

        OfferList offerList = new OfferList();
        offerList.addOffer(offer1);
        offerList.addOffer(offer2);
        offerList.addOffer(offer3);

        List<Offer> sortedOfferList = offerList.getByStartDateAndEndDate(20200829, 20201010);
        assertEquals(20200802, sortedOfferList.get(0).getAvailabilityStartDate());
        assertEquals(20200810, sortedOfferList.get(1).getAvailabilityStartDate());
        assertEquals(20200829, sortedOfferList.get(2).getAvailabilityStartDate());
    }
}
