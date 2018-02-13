package pl.xngiezak5db.smarthouse.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomWeather {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="timestamp")
	private String timestamp;
	
	@Column(name="temp")
	private double temp;
	
	@Column(name="humidity")
	private double humidity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return timestamp;
	}

	public void setDate(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	
	public RoomWeather() {
		
	}
	
	public RoomWeather(double temp, double humidity) {
		this.temp = temp;
		this.humidity = humidity;
	}
	
}
