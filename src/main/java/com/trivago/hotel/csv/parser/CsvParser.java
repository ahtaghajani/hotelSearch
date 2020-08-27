package com.trivago.hotel.csv.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import com.trivago.hotel.csv.dto.AdvertiserCsvDto;
import com.trivago.hotel.csv.dto.CityCsvDto;
import com.trivago.hotel.csv.dto.HotelAdvertiserCsvDto;
import com.trivago.hotel.csv.dto.HotelCsvDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvParser {
    @Value("${hotel.citiesFilePath}")
    private String citiesFilePath;

    @Value("${hotel.advertisersFilePath}")
    private String advertisersFilePath;

    @Value("${hotel.hotelFilePath}")
    private String hotelFilePath;

    @Value("${hotel.hotelAdvertiserFilePath}")
    private String hotelAdvertiserFilePath;

    public List<CityCsvDto> parseCities() throws IOException {
        return parse(citiesFilePath, CityCsvDto.class);
    }

    public List<AdvertiserCsvDto> parseAdvertisers() throws IOException {
        return parse(advertisersFilePath, AdvertiserCsvDto.class);
    }

    public List<HotelCsvDto> parseHotels() throws IOException {
        return parse(hotelFilePath,HotelCsvDto.class);
    }

    public List<HotelAdvertiserCsvDto> parseHotelAdvertisers() throws IOException {
        return parse(hotelAdvertiserFilePath,HotelAdvertiserCsvDto.class);
    }

    private <T> List<T> parse(String filePath, Class<T> type) throws IOException {
        try (FileReader fileReader = new FileReader(filePath)) {
            return new CsvToBeanBuilder<T>(fileReader)
                    .withType(type).build().parse();
        }
    }
}
