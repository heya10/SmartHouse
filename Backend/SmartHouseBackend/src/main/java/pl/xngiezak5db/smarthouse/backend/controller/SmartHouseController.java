package pl.xngiezak5db.smarthouse.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.xngiezak5db.smarthouse.backend.domain.CityWeatherRepository;
import pl.xngiezak5db.smarthouse.backend.domain.RoomWeatherRepository;

@Controller
public class SmartHouseController {
	
	@Autowired
	RoomWeatherRepository roomWeatherRepository;
	
	@Autowired
	CityWeatherRepository cityWeatherRepository;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("roomWeather", roomWeatherRepository.getMostRecentRoomWeather());
		model.addAttribute("cityWeather", cityWeatherRepository.getMostRecentCityWeather());
		return "dashboard";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model){
		model.addAttribute("roomWeather", roomWeatherRepository.getMostRecentRoomWeather());
		model.addAttribute("cityWeather", cityWeatherRepository.getMostRecentCityWeather());
		return "dashboard";
	}
	
	@RequestMapping("/room-weather")
	public String roomWeather(Model model){
		model.addAttribute("roomWeather", roomWeatherRepository.getMostRecentRoomWeather());
		return "room-weather";
	}
	
	@RequestMapping("/city-weather")
	public String cityWeather(Model model){
		model.addAttribute("cityWeather", cityWeatherRepository.getMostRecentCityWeather());		
		return "city-weather";
	}
	
	@RequestMapping("/api/add/room-weather")
	public @ResponseBody String addRoomWeather(@RequestParam(name="temp", required=true) double temp
			, @RequestParam(name="humidity", required=true) double humidity) {
		roomWeatherRepository.addRoomWeather(temp, humidity);
		return "success";
	}
	
	@RequestMapping("/api/add/city-weather")
	public @ResponseBody String addCityWeather(@RequestParam(name="temp", required=true) String temp
			, @RequestParam(name="windSpeed", required=true) double windSpeed
			, @RequestParam(name="rainfall", required=true) double rainfall) {
		cityWeatherRepository.addCityWeather(temp, windSpeed, rainfall);
		return "success";
	}
}
