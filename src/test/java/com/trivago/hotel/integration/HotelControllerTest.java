package com.trivago.hotel.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trivago.hotel.csv.dto.HotelAdvertiserCsvDto;
import com.trivago.hotel.rest.dto.HotelDto;
import com.trivago.hotel.rest.dto.OfferDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void testSearch() throws Exception {
        List<HotelDto> exceptedOrderedResponse = new ArrayList<>();

        HotelDto hotelDto = new HotelDto(0, "hotel_basic_with_a_view_Berlin", 67, 4,
                Arrays.asList(new OfferDto(0, "travel_advisor_foryou", 178, 245, "EUR")));
        exceptedOrderedResponse.add(hotelDto);

        hotelDto = new HotelDto(1, "hotel_fun_with_a_view_Berlin", 51, 4,
                Arrays.asList(new OfferDto(0, "travel_advisor_foryou", 332, 950, "EUR"),
                        new OfferDto(4, "beach_search.de", 190, 995, "EUR")));
        exceptedOrderedResponse.add(hotelDto);

        hotelDto = new HotelDto(3, "hotel_sunshine_resort_Berlin", 27, 2,
                Arrays.asList(new OfferDto(0, "travel_advisor_foryou", 200, 982, "EUR")));
        exceptedOrderedResponse.add(hotelDto);

        String cityName = "Berlin";
        int startDate = 20200802;
        int endDate = 20201010;
        String content = mockMvc.perform(
                get("/search?city=" + cityName + "&startDate=" + startDate + "&endDate=" + endDate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andReturn().getResponse().getContentAsString();
        List<HotelDto> hotelDtoList = objectMapper.readValue(content, new TypeReference<List<HotelDto>>() {
        });

        assertEquals(exceptedOrderedResponse, hotelDtoList);
    }

    @Test
    @Order(2)
    public void testPriceUpdate() throws Exception {

        final int price1 = 928;
        final int price2 = 245;
        List<HotelAdvertiserCsvDto> requestBody = Arrays.asList(
                new HotelAdvertiserCsvDto(3, 0, 200, price1, "EUR", 20200702, 20201020),
                new HotelAdvertiserCsvDto(0, 0, 178, price2, "EUR", 20200721, 20201020));
        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(
                post("/price")
                        .content(requestBodyJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String cityName = "Berlin";
        int startDate = 20200802;
        int endDate = 20201010;
        mockMvc.perform(
                get("/search?city=" + cityName + "&startDate=" + startDate + "&endDate=" + endDate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].offers[0].price").value(price2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].offers[0].price").value(950))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].offers[1].price").value(995))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].offers[0].price").value(price1));

    }
}