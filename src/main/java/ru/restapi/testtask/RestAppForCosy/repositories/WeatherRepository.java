package ru.restapi.testtask.RestAppForCosy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.restapi.testtask.RestAppForCosy.models.Weather;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    public List<Weather> findAllByName(String name);
    public List<Weather> findAllByNameAndAddedAtBetween(String name, LocalDateTime fromDate, LocalDateTime toDate);
}