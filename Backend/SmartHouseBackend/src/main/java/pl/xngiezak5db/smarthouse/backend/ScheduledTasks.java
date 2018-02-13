package pl.xngiezak5db.smarthouse.backend;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.xngiezak5db.smarthouse.backend.domain.CityWeatherRepository;

@Component
public class ScheduledTasks {
	
	@Autowired
	CityWeatherRepository cwr;
	
	//initialDelay 2sec so there won't be any 'extra' value in database when test are running
	@Scheduled(fixedRate=3600000, initialDelay=2000)
	public void getCityWeatherAndProcess() {
		Document doc;
		try {
			doc = Jsoup.connect("http://wttr.in/belchatow?lang=en").get();
			String[] rawWeather = doc.text().split("\n");
			//getting raw website text and pick only line where temperature is
			String tempBeforeProcess = rawWeather[4];
			//processing - deleting unneccesery sign at the beggining, removing "°C" and empty characters
			String temp = tempBeforeProcess.substring(15).replaceAll("°C","").replaceAll("\\s+","");
			
			//same thing for wind speed and rainfall
			String windBeforeProcess = rawWeather[5];
			String windSpeed = windBeforeProcess.substring(17).replaceAll("km/h","").replaceAll("\\s+","");
			
			String rainfallBeforeProcess = rawWeather[7]; 
			String rainfall = rainfallBeforeProcess.substring(15).replaceAll("mm","").replaceAll("\\s+","");
			
			//System.out.println("Weather: " + temp + " C, " + windSpeed + " km/h, " + rainfall + " mm");
			cwr.addCityWeather(temp, Double.parseDouble(windSpeed), Double.parseDouble(rainfall));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
