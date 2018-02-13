package pl.xngiezak5db.smarthouse.backend.domain;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoomWeatherRepository extends CrudRepository<RoomWeather, Long> {
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO room_weather(temp, humidity, timestamp) VALUES (?1,?2,CURRENT_TIMESTAMP())", nativeQuery=true)
	public void addRoomWeather(double temp, double humidity);
	
	@Query(value="SELECT * FROM room_weather ORDER BY id DESC LIMIT 720", nativeQuery=true)
	public Iterable<RoomWeather> getLastMonthRoomWeather();
	
	@Query(value="SELECT * FROM room_weather ORDER BY id DESC LIMIT 10", nativeQuery=true)
	public Iterable<RoomWeather> getMostRecentRoomWeather();
	
}