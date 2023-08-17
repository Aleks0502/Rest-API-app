package ru.restapi.testtask.RestAppForCosy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.restapi.testtask.RestAppForCosy.models.Weather;
import ru.restapi.testtask.RestAppForCosy.repositories.WeatherRepository;
import ru.restapi.testtask.RestAppForCosy.utils.City;

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

        cityAndAverageTemp.put(City.TOLYATTI, getAverageTemp(weatherRepository.findAllByName(City.TOLYATTI)));
        cityAndAverageTemp.put(City.PARIS, getAverageTemp(weatherRepository.findAllByName(City.PARIS)));
        cityAndAverageTemp.put(City.ROME, getAverageTemp(weatherRepository.findAllByName(City.ROME)));

        return cityAndAverageTemp;
    }

    public HashMap<String, Double> getAverageTempOfEachCityForPeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        HashMap<String, Double> cityAndAverageTemp = new HashMap<>();

        cityAndAverageTemp.put(City.TOLYATTI, getAverageTemp(weatherRepository.findAllByNameAndAddedAtBetween(
                City.TOLYATTI, fromDate, toDate)));
        cityAndAverageTemp.put(City.PARIS, getAverageTemp(weatherRepository.findAllByNameAndAddedAtBetween(
                City.PARIS, fromDate, toDate)));
        cityAndAverageTemp.put(City.ROME, getAverageTemp(weatherRepository.findAllByNameAndAddedAtBetween(
                City.ROME, fromDate, toDate)));

        return cityAndAverageTemp;
    }

    private double getAverageTemp(List<Weather> weatherList) {
        return weatherList.stream()
                .mapToDouble(Weather::getTemp)
                .average()
                .orElse(Double.NaN);
    }
}
