package edu.lethe.gameslib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.lethe.gameslib")
public class GameslibApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameslibApplication.class, args);
	}

}
