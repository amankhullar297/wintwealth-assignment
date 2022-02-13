package com.wintweatlh.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.wintweatlh.assignment"})
public class WintwealthAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(WintwealthAssignmentApplication.class, args);
	}

}
