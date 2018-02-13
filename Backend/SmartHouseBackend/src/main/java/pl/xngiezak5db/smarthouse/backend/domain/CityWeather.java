package pl.xngiezak5db.smarthouse.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CityWeather {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="timestamp")
	private String timestamp;
	
	@Column(name="temp")
	private String temp;	//I use grep to take temp and its not a number but rather a range so I think String will be better here
	
	@Column(name="wind_speed")
	private double windSpeed;
	
	@Column(name="rainfall")
	private double rainfall;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String gettimestamp() {
		return timestamp;
	}

	public void settimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getRainfall() {
		return rainfall;
	}

	public void setRainfall(double rainfall) {
		this.rainfall = rainfall;
	}
	
	public CityWeather() {
		
	}
	
	public CityWeather(String temp, double windSpeed, double rainfall) {
		this.temp = temp;
		this.windSpeed = windSpeed;
		this.rainfall = rainfall;
	}

}
