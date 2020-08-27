package com.trivago.hotel.csv.dto;

import com.opencsv.bean.CsvBindByName;

public class AdvertiserCsvDto {

    @CsvBindByName
    private int id;

    @CsvBindByName(column = "advertiser_name")
    private String advertiserName;

    public AdvertiserCsvDto() {
    }

    public AdvertiserCsvDto(int id, String advertiserName) {
        this.id = id;
        this.advertiserName = advertiserName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        this.advertiserName = advertiserName;
    }
}
