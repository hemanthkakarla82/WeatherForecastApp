package com.org.marsh.poc.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class WeatherForecastServiceTest {

    private RestTemplate mockRestTemplate;
    private WeatherForecastService weatherForecastService;
    private String url ="https://api.weatherbit.io/v2.0/current?postal_code=60616&key=f4eb860774724fe1bb9f3db983acb5f5";

    @Before
    public void setup(){
        mockRestTemplate = mock(RestTemplate.class);
        weatherForecastService = new WeatherForecastService(mockRestTemplate);
    }

    @Test
    public void testGetWeatherForecastByZipcode(){
        String zipcode = "60616";
        Object emp = new Object();
        Mockito.when(mockRestTemplate.getForEntity(url, Object.class)).thenReturn(new ResponseEntity(emp, HttpStatus.OK));
        ResponseEntity<?> response = weatherForecastService.getWeatherForecastDataByZipcode(zipcode);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testGetWeatherForecastByDateRange(){
        String startDate = "2020-07-01";
        String endDate = "2020-07-02";
        Object emp = new Object();
        Mockito.when(mockRestTemplate.getForEntity(url, Object.class)).thenReturn(new ResponseEntity(emp, HttpStatus.OK));
        ResponseEntity<?> response = weatherForecastService.getWeatherForecastHistoryByDateRange(startDate,endDate);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


}
