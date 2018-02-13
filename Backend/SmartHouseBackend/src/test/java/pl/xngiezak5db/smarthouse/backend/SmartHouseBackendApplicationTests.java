package pl.xngiezak5db.smarthouse.backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.xngiezak5db.smarthouse.backend.domain.CityWeather;
import pl.xngiezak5db.smarthouse.backend.domain.CityWeatherRepository;
import pl.xngiezak5db.smarthouse.backend.domain.RoomWeather;
import pl.xngiezak5db.smarthouse.backend.domain.RoomWeatherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartHouseBackendApplicationTests {
	
	@Autowired
	private RoomWeatherRepository roomWeatherRepo;
	
	@Autowired
	private CityWeatherRepository cityWeatherRepo;
	
	@Before
	public void cleanDatabase() {
		//clean database
		roomWeatherRepo.deleteAll();
		cityWeatherRepo.deleteAll();
	}
	
	@Test
	public void areReposNull() {
		assertThat(roomWeatherRepo).isNotNull();
		assertThat(cityWeatherRepo).isNotNull();
	}
	
	@Test
	public void checkAreTablesEmpty() {
		assertThat(roomWeatherRepo.count()).isEqualTo(0);
		assertThat(cityWeatherRepo.count()).isEqualTo(0);
	}

	@Test
	public void roomRepoTest() {
		//lets check room weather table
		roomWeatherRepo.addRoomWeather(-5, 10.5);
		roomWeatherRepo.addRoomWeather(8.3, 73);
		roomWeatherRepo.addRoomWeather(25.1, 60.99);
		roomWeatherRepo.addRoomWeather(28.1, 50.1);
		
		Iterable<RoomWeather> roomWeatherList = roomWeatherRepo.findAll();
		
		System.out.println("Room weather status:");
		
		for(RoomWeather rw : roomWeatherList){
			System.out.println(rw.getTemp()+" Celsius, " + rw.getHumidity() + "% Humidity");
		}
		assertThat(roomWeatherRepo.count()).isEqualTo(4);
		
		roomWeatherRepo.deleteAll();

		System.out.println("\nAfter \"deleteAll\" there is: "+roomWeatherRepo.count()+" rows in table.\n");
		assertThat(roomWeatherRepo.count()).isEqualTo(0);		
	}
	
	@Test
	public void cityRepoTest() {
		cityWeatherRepo.addCityWeather("20.3", 22.5, 0.7);
		cityWeatherRepo.addCityWeather("-4.3", 17.0, 0.0);
		cityWeatherRepo.addCityWeather("-2.3-(-1.2)", 18, 0);
		cityWeatherRepo.addCityWeather("-5-(-3)", 25.1, 2.3);
		
		Iterable<CityWeather> cityWeatherList = cityWeatherRepo.findAll();
		
		System.out.println("\n********************\n\nCity weather status:");
		
		for(CityWeather cw : cityWeatherList) {
			System.out.println(cw.getTemp()+" Celsius, " + cw.getWindSpeed() + " km/h, " + cw.getRainfall() + " mm");
		}
		
		assertThat(cityWeatherRepo.count()).isEqualTo(4);
		
		cityWeatherRepo.deleteAll();
		
		System.out.println("After \"deleteAl\" there is: " + cityWeatherRepo.count() + " rows in table.\n ****************");
		assertThat(cityWeatherRepo.count()).isEqualTo(0);
	}

}
