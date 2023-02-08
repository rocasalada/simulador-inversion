package com.practicascloud.baselineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaselineServiceApplication {

	static Simulation simulation;
	public static void main(String[] args) {
		SpringApplication.run(BaselineServiceApplication.class, args);
		new SimulationAction(args);
	}

}
