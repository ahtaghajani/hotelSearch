package com.trivago.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan()
public class HotelSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelSearchApplication.class, args);
    }
}
