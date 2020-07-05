package com.org.marsh.poc.controller;

import com.org.marsh.poc.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping("/weather-data/api")
public class WeatherForecastController {

    Logger log = LoggerFactory.getLogger(WeatherForecastController.class);

    private WeatherForecastService weatherForecastService;


    @Autowired
    public WeatherForecastController(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping("/v1/forecast/current")
    public ResponseEntity<?> getWeatherForecastDataByZipcode(@Valid @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$") @RequestParam("zipcode") String zipcode) {
        log.debug("weather forecast for the zipcode:" + zipcode);
        return weatherForecastService.getWeatherForecastDataByZipcode(zipcode);
    }

    @GetMapping("/v1/forecast/history")
    public ResponseEntity<?> getWeatherForecastHistoryByDateRange(@Valid @RequestParam("startDate") @DateTimeFormat(pattern = "YYYY-MM-DD") String startDate, @Valid @RequestParam("endDate") @DateTimeFormat(pattern = "YYYY-MM-DD") String endDate) {
        log.debug("weather forecast history for the dates:" + startDate + " and " + endDate);
        return weatherForecastService.getWeatherForecastHistoryByDateRange(startDate, endDate);
    }


}