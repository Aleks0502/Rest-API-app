package ru.restapi.testtask.RestAppForCosy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restapi.testtask.RestAppForCosy.models.Weather;
import ru.restapi.testtask.RestAppForCosy.repositories.WeatherRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getAverageCityTemp(String city) {
        Weather weather = new Weather();
        weather.setName(city);
        weather.setTemp(getAverageTemp(weatherRepository.findAllByName(city)));

        return weather;
    }

    public HashMap<String, Double> getAverageTempOfEachCity() {
        HashMap<String, Double> cityAndAverageTemp = new HashMap<>();

        cityAndAverageTemp.put("Tolyatti", getAverageTemp(weatherRepository.findAllByName("Tolyatti")));
        cityAndAverageTemp.put("Paris", getAverageTemp(weatherRepository.findAllByName("Paris")));
        cityAndAverageTemp.put("Rome", getAverageTemp(weatherRepository.findAllByName("Rome")));

        return cityAndAverageTemp;
    }

    public HashMap<String, Double> getAverageTempOfEachCityForPeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        HashMap<String, Double> cityAndAverageTemp = new HashMap<>();

        cityAndAverageTemp.put("Tolyatti", getAverageTemp(weatherRepository.findAllByNameAndAddedAtBetween(
                "Tolyatti", fromDate, toDate)));
        cityAndAverageTemp.put("Paris", getAverageTemp(weatherRepository.findAllByNameAndAddedAtBetween(
                "Paris", fromDate, toDate)));
        cityAndAverageTemp.put("Rome", getAverageTemp(weatherRepository.findAllByNameAndAddedAtBetween(
                "Rome", fromDate, toDate)));

        return cityAndAverageTemp;
    }

    private double getAverageTemp(List<Weather> weatherList) {
        return weatherList.stream()
                .mapToDouble(Weather::getTemp)
                .average()
                .orElse(Double.NaN);
    }
}
