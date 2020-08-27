package com.trivago.hotel.model;

import com.trivago.hotel.csv.dto.AdvertiserCsvDto;

public class Advertiser {
    private int id;
    private String name;

    public Advertiser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Advertiser(AdvertiserCsvDto advertiserCsvDto) {
        this(advertiserCsvDto.getId(),advertiserCsvDto.getAdvertiserName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
