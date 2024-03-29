package com.betuiasi.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class BetuiasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetuiasiApplication.class, args);
	}
}
