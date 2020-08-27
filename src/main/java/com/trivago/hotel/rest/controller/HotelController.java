package com.trivago.hotel.rest.controller;

import com.trivago.hotel.csv.dto.HotelAdvertiserCsvDto;
import com.trivago.hotel.rest.dto.HotelDto;
import com.trivago.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("search")
    public ResponseEntity<List<HotelDto>> search(@RequestParam("city") String city
            , @RequestParam("startDate") int startDate, @RequestParam("endDate") int endDate) throws IOException {

        List<HotelDto> hotelAdvertiserList = hotelService.getOffersByCityAndDate(city, startDate, endDate);
        return new ResponseEntity<>(hotelAdvertiserList, HttpStatus.OK);
    }

    @PostMapping("price")
    public void updatePrice(@RequestBody List<HotelAdvertiserCsvDto> hotelAdvertiserCsvDtoList) {
        Assert.notEmpty(hotelAdvertiserCsvDtoList, "'hotelAdvertiserCsvDtoList' is empty");
        hotelService.updatePriceOfOffers(hotelAdvertiserCsvDtoList);
    }
}
