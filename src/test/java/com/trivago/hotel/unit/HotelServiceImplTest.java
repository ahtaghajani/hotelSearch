package com.trivago.hotel.unit;

import com.trivago.hotel.csv.dto.AdvertiserCsvDto;
import com.trivago.hotel.csv.dto.CityCsvDto;
import com.trivago.hotel.csv.dto.HotelAdvertiserCsvDto;
import com.trivago.hotel.csv.dto.HotelCsvDto;
import com.trivago.hotel.csv.parser.CsvParser;
import com.trivago.hotel.rest.dto.HotelDto;
import com.trivago.hotel.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HotelServiceImplTest {

    @MockBean
    private CsvParser csvParser;

    @Autowired
    private HotelServiceImpl hotelService;

    @Test
    void testSearch() throws Exception {
        when(csvParser.parseCities()).thenReturn(Arrays.asList(new CityCsvDto(1, "Berlin")));
        when(csvParser.parseAdvertisers()).thenReturn(Arrays.asList(new AdvertiserCsvDto(1, "John")));

        when(csvParser.parseHotels()).thenReturn(Arrays.asList(
                new HotelCsvDto(1, 100, 2, "Royal Hotel", 4, 5, 1),
                new HotelCsvDto(2, 150, 3, "Nice Hotel", 3, 4, 1)
        ));

        when(csvParser.parseHotelAdvertisers()).thenReturn(Arrays.asList(
                new HotelAdvertiserCsvDto(1, 1, 2, 30, "Euro", 20200910, 20200915),
                new HotelAdvertiserCsvDto(2, 1, 3, 32, "Euro", 20200911, 20200916))
        );

        hotelService.reloadData();
        List<HotelDto> hotelsSortedByRating = hotelService.getOffersByCityAndDate("Berlin", 20200911, 20200915);
        assertEquals(2, hotelsSortedByRating.size());
        assertEquals(1,hotelsSortedByRating.get(0).getHotelId());
        assertEquals("Royal Hotel",hotelsSortedByRating.get(0).getHotelName());
        assertEquals(4,hotelsSortedByRating.get(0).getRating());
        assertEquals(5,hotelsSortedByRating.get(0).getStars());

        assertEquals(2,hotelsSortedByRating.get(1).getHotelId());
        assertEquals("Nice Hotel",hotelsSortedByRating.get(1).getHotelName());
        assertEquals(3,hotelsSortedByRating.get(1).getRating());
        assertEquals(4,hotelsSortedByRating.get(1).getStars());

        List<HotelDto> offersByCityAndDate = hotelService.getOffersByCityAndDate("Berlin", 20200911, 20200916);
        assertEquals(1, offersByCityAndDate.size());
        assertEquals(2,offersByCityAndDate.get(0).getHotelId());
        assertEquals("Nice Hotel",offersByCityAndDate.get(0).getHotelName());
        assertEquals(3,offersByCityAndDate.get(0).getRating());
        assertEquals(4,offersByCityAndDate.get(0).getStars());
    }


}
