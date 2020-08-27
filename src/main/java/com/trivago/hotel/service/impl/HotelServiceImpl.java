package com.trivago.hotel.service.impl;

import com.trivago.hotel.csv.dto.AdvertiserCsvDto;
import com.trivago.hotel.csv.dto.CityCsvDto;
import com.trivago.hotel.csv.dto.HotelAdvertiserCsvDto;
import com.trivago.hotel.csv.dto.HotelCsvDto;
import com.trivago.hotel.csv.parser.CsvParser;
import com.trivago.hotel.model.Advertiser;
import com.trivago.hotel.model.Hotel;
import com.trivago.hotel.model.Offer;
import com.trivago.hotel.rest.dto.HotelDto;
import com.trivago.hotel.rest.dto.OfferDto;
import com.trivago.hotel.service.HotelService;
import com.trivago.hotel.util.OfferList;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final CsvParser csvParser;
    private final Map<String, OfferList> cityNameToOffers = new HashMap<>();
    private final Map<Integer, OfferList> hotelIdToOffers = new HashMap<>();

    public HotelServiceImpl(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    @PostConstruct
    public void reloadData() throws IOException {
        cityNameToOffers.clear();
        hotelIdToOffers.clear();

        List<CityCsvDto> cityCsvDtoList = csvParser.parseCities();
        Map<Integer, CityCsvDto> cityIdToCityMap = cityCsvDtoList.stream()
                .collect(Collectors.toMap(CityCsvDto::getId, Function.identity()));

        cityIdToCityMap.values().forEach(city -> {
            OfferList offerList = new OfferList();
            cityNameToOffers.put(city.getCityName(), offerList);
        });

        Map<Integer, HotelCsvDto> hotelIdToHotelMap = csvParser.parseHotels()
                .stream().collect(Collectors.toMap(HotelCsvDto::getId, Function.identity()));

        hotelIdToHotelMap.values().forEach(hotel -> {
            OfferList offerList = new OfferList();
            hotelIdToOffers.put(hotel.getId(), offerList);
        });

        Map<Integer, AdvertiserCsvDto> advertiserIdToAdvertiserMap = csvParser.parseAdvertisers()
                .stream().collect(Collectors.toMap(AdvertiserCsvDto::getId, Function.identity()));

        List<HotelAdvertiserCsvDto> hotelAdvertisers = csvParser.parseHotelAdvertisers();

        hotelAdvertisers.forEach(hotelAdvertiser -> {
            HotelCsvDto hotelCsvDto = hotelIdToHotelMap.get(hotelAdvertiser.getHotelId());
            if (hotelCsvDto == null) {
                throw new IllegalStateException("hotel with id: '" + hotelAdvertiser.getHotelId() + "' not found");
            }

            AdvertiserCsvDto advertiserCsvDto = advertiserIdToAdvertiserMap.get(hotelAdvertiser.getAdvertiserId());
            if (advertiserCsvDto == null) {
                throw new IllegalStateException("advertiser with id: '" + hotelAdvertiser.getAdvertiserId() + "' not found");
            }

            Offer offer = new Offer(new Hotel(hotelCsvDto), new Advertiser(advertiserCsvDto),
                    hotelAdvertiser.getCpc(), hotelAdvertiser.getPrice(), hotelAdvertiser.getCurrency()
                    , hotelAdvertiser.getAvailabilityStartDate(), hotelAdvertiser.getAvailabilityEndDate());

            String cityNameOfHotel = cityIdToCityMap.get(hotelCsvDto.getCityId()).getCityName();
            cityNameToOffers.get(cityNameOfHotel).addOffer(offer);
            hotelIdToOffers.get(hotelCsvDto.getId()).addOffer(offer);
        });
    }

    @Override
    public List<HotelDto> getOffersByCityAndDate(String cityName, int startDate, int endDate) throws IOException {

        OfferList offerList = cityNameToOffers.get(cityName);
        if (offerList == null) {
            return Collections.emptyList();
        }

        List<Offer> cityOffers = offerList.getByStartDateAndEndDate(startDate, endDate);

        Map<Hotel, List<Offer>> hotelToOffersMap =
                cityOffers.stream().collect(Collectors.groupingBy(offer -> offer.getHotel()));

        return hotelToOffersMap.entrySet().stream().map(entry -> {
            Hotel hotel = entry.getKey();
            List<Offer> hotelOffers = entry.getValue();
            List<OfferDto> offerDtoList = hotelOffers.stream()
                    .sorted((o1, o2) -> o1.getPrice() == o2.getPrice() ? o2.getCpc() - o1.getCpc() : o1.getPrice() - o2.getPrice())
                    .map(offer -> new OfferDto(offer.getAdvertiser().getId(), offer.getAdvertiser().getName()
                            , offer.getCpc(), offer.getPrice(), offer.getCurrency()))
                    .collect(Collectors.toList());
            return new HotelDto(hotel.getId(), hotel.getName(), hotel.getRating(), hotel.getStars(), offerDtoList);
        })
                .sorted((o1, o2) -> o2.getRating() - o1.getRating())
                .collect(Collectors.toList());
    }

    @Override
    public void updatePriceOfOffers(List<HotelAdvertiserCsvDto> hotelAdvertiserCsvDtoList) {
        for (HotelAdvertiserCsvDto hotelAdvertiser : hotelAdvertiserCsvDtoList) {
            OfferList hotelOfferList = hotelIdToOffers.get(hotelAdvertiser.getHotelId());
            Offer offer = hotelOfferList.getByAdvertiserIdAdnStartDateAndEndDate(hotelAdvertiser.getAdvertiserId(), hotelAdvertiser.getAvailabilityStartDate(), hotelAdvertiser.getAvailabilityEndDate());
            offer.updatePrice(hotelAdvertiser.getCpc(), hotelAdvertiser.getPrice(), hotelAdvertiser.getCurrency());
        }
    }
}
