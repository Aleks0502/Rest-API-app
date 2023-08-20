package ru.restapi.testtask.RestAppForCosy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.restapi.testtask.RestAppForCosy.models.Weather;
import ru.restapi.testtask.RestAppForCosy.repositories.WeatherRepository;
import ru.restapi.testtask.RestAppForCosy.services.WeatherService;
import ru.restapi.testtask.RestAppForCosy.utils.City;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class RestAppForCosyApplicationTests {

    @Test
    public void testGetAverageWeatherByCity() {
        // Создание мок-объекта репозитория
        WeatherRepository weatherRepository = mock(WeatherRepository.class);
        List<Weather> tolyattiMockWeatherList = Stream.of(
                        new Weather(City.TOLYATTI, 20),
                        new Weather(City.TOLYATTI, 25),
                        new Weather(City.TOLYATTI, 30))
                .collect(Collectors.toList());

        // Задание ожидаемого результата при вызове метода findAllByName
        when(weatherRepository.findAllByName(City.TOLYATTI)).thenReturn(tolyattiMockWeatherList);

        // Создание объекта сервиса, передача ему мок-объекта репозитория
        WeatherService weatherService = new WeatherService(weatherRepository);

        // Вызов тестируемого метода
        Weather result = weatherService.getAverageWeatherByCity(City.TOLYATTI);

        // Проверка, что имя полученной погоды совпадает с ожидаемым значением
        assertEquals("Tolyatti", result.getName());

        // Проверка, что температура полученной погоды совпадает с ожидаемым средним значением
        assertEquals(25, result.getTemp());

        // Проверка, что метод findAllByName вызывался один раз с аргументом "City.TOLYATTI"
        verify(weatherRepository, times(1)).findAllByName(City.TOLYATTI);
    }

}
