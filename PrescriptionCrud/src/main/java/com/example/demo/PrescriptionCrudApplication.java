package com.example.demo;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
@ConfigurationPropertiesScan
public class PrescriptionCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrescriptionCrudApplication.class, args);
	}

}
