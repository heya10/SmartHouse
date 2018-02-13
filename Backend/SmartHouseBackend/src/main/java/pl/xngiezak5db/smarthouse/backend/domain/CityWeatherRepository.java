package pl.xngiezak5db.smarthouse.backend.domain;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CityWeatherRepository extends CrudRepository<CityWeather, Long>{

	@Modifying
	@Transactional
	@Query(value="INSERT INTO city_weather(temp, wind_speed, rainfall, timestamp) VALUES (?1,?2,?3,CURRENT_TIMESTAMP())", nativeQuery=true)
	public void addCityWeather(String temp, double windSpeed, double rainfall);

	@Query(value="SELECT * FROM city_weather ORDER BY id DESC LIMIT 720", nativeQuery=true)
	public Iterable<CityWeather> getLastMonthCityWeather();

	@Query(value="SELECT * FROM city_weather ORDER BY id DESC LIMIT 10", nativeQuery=true)
	public Iterable<CityWeather> getMostRecentCityWeather();
}