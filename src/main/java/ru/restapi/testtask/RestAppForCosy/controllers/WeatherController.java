package ru.restapi.testtask.RestAppForCosy.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.restapi.testtask.RestAppForCosy.dto.WeatherDTO;
import ru.restapi.testtask.RestAppForCosy.models.Weather;
import ru.restapi.testtask.RestAppForCosy.services.WeatherService;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/cosy-api")
public class WeatherController {

    private final WeatherService weatherService;
    private final ModelMapper modelMapper;

    @Autowired
    public WeatherController(WeatherService weatherService, ModelMapper modelMapper) {
        this.weatherService = weatherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{city}")
    public WeatherDTO getAverageCityTemp(@PathVariable("city") String city) {
        return convertToWeatherDTO(weatherService.getAverageCityTemp(city));
    }

    @GetMapping("/cities/average-temp")
    public HashMap<String, Double> getAverageTempOfEachCity() {
        return weatherService.getAverageTempOfEachCity();
    }

    @GetMapping("/cities")
    public HashMap<String, Double> getAverageTempOfEachCityForPeriod(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
        return weatherService.getAverageTempOfEachCityForPeriod(fromDate, toDate);
    }

    private WeatherDTO convertToWeatherDTO(Weather weather) {
        return modelMapper.map(weather, WeatherDTO.class);
    }
}
