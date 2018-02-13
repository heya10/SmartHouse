package pl.xngiezak5db.smarthouse.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.xngiezak5db.smarthouse.backend.domain.CityWeather;
import pl.xngiezak5db.smarthouse.backend.domain.CityWeatherRepository;
import pl.xngiezak5db.smarthouse.backend.domain.RoomWeather;
import pl.xngiezak5db.smarthouse.backend.domain.RoomWeatherRepository;

@RestController
public class SmartHouseRestController {
	
	@Autowired
	RoomWeatherRepository roomWeatherRepository;
	
	@Autowired
	CityWeatherRepository cityWeatherRepository;
	
	@RequestMapping("/api/get/lastMonthRoomWeather")
	public Iterable<RoomWeather> getRoomWeather(){
		return roomWeatherRepository.getLastMonthRoomWeather();
	}
	
	@RequestMapping("/api/get/lastMonthCityWeather")
	public Iterable<CityWeather> getCityWeather(){
		return cityWeatherRepository.getLastMonthCityWeather();
	}
	
	@RequestMapping("/api/get/recentRoomWeather")
	public Iterable<RoomWeather> getRecentRoomWeather(){
		return roomWeatherRepository.getMostRecentRoomWeather();
	}
	
	@RequestMapping("/api/get/recentCityWeather")
	public Iterable<CityWeather> getRecentCityWeather(){
		return cityWeatherRepository.getMostRecentCityWeather();
	}
}
