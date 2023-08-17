package ru.restapi.testtask.RestAppForCosy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.restapi.testtask.RestAppForCosy.utils.City;
import ru.restapi.testtask.RestAppForCosy.utils.OWMResponse;
import ru.restapi.testtask.RestAppForCosy.models.Weather;
import ru.restapi.testtask.RestAppForCosy.repositories.WeatherRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OWMClient {

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    @Autowired
    public OWMClient(RestTemplate restTemplate, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
    }

    @Scheduled(fixedRate = 60000) // выполнение запроса каждую минуту
    public void fetchWeatherDataAndSave() {
        String baseUrl = "https://api.openweathermap.org/data/2.5/weather";
        String apiKey = "16a77f7fd156c6e1111c2b37f87715d6";

        String[] cities = {City.TOLYATTI, City.PARIS, City.ROME};
        List<Weather> weatherList = new ArrayList<>();

        for (String city : cities) {
            OWMResponse response = restTemplate.getForObject(
                    baseUrl + "?q=" + city + "&units=metric&appid=" + apiKey, OWMResponse.class);
            Weather weather = new Weather();
            weather.setName(Objects.requireNonNull(response).getName());
            weather.setTemp(response.getMain().getTemp());
            weather.setAddedAt(LocalDateTime.now());

            weatherList.add(weather);
        }
        weatherRepository.saveAll(weatherList);
    }
}
