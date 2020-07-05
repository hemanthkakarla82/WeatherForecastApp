package com.org.marsh.poc.service;

import static com.org.marsh.poc.util.WeatherConstants.API_KEY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherForecastService {

    Logger log = LoggerFactory.getLogger(WeatherForecastService.class);

    private RestTemplate restTemplate;

    @Value("${weather.api.baseUrl}")
    private String baseUrl;

    @Autowired
    public WeatherForecastService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<?> getWeatherForecastDataByZipcode(String zipcode) {
        try {
            final String url = baseUrl + "/current?postal_code=" + zipcode + "&key=" + API_KEY;
            log.debug("weather api url:"+url);
            return restTemplate.getForEntity(url, Object.class);
        } catch (RestClientException restClientException) {
            log.error("Exception while calling weather api", restClientException);
        }
        return null;
    }

    public ResponseEntity<?> getWeatherForecastHistoryByDateRange(String startDate, String endDate) {
        try {
            final String url = baseUrl + "/history/energy?lat=38.00&lon=-78.25&start_date=" + startDate + "&end_date=" + endDate + "&key=" + API_KEY;
            log.debug("weather api url:"+url);
            return restTemplate.getForEntity(url, Object.class);
        } catch (RestClientException restClientException) {
            log.error("Exception while calling weather forecast history api", restClientException);
        }
        return null;
    }
}
