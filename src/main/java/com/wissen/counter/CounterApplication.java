package com.wissen.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class CounterApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}



	public static void main(String[] args) {
		SpringApplication.run(CounterApplication.class, args);
	}

}
