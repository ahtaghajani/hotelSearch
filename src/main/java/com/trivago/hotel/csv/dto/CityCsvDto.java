package com.trivago.hotel.csv.dto;

import com.opencsv.bean.CsvBindByName;

public class CityCsvDto {

    @CsvBindByName
    private int id;

    @CsvBindByName(column = "city_name")
    private String cityName;

    public CityCsvDto() {
    }

    public CityCsvDto(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
