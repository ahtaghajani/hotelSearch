package com.trivago.hotel.service;

import com.trivago.hotel.csv.dto.HotelAdvertiserCsvDto;
import com.trivago.hotel.rest.dto.HotelDto;

import java.io.IOException;
import java.util.List;

public interface HotelService {
    List<HotelDto> getOffersByCityAndDate(String city, int startDate, int endDate) throws IOException;

    void updatePriceOfOffers(List<HotelAdvertiserCsvDto> hotelAdvertiserCsvDtoList);
}
