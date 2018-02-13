package pl.xngiezak5db.smarthouse.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartHouseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHouseBackendApplication.class, args);
	}
}
