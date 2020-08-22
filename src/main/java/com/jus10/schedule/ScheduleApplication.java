package com.jus10.schedule;

import com.jus10.schedule.models.Client;
import com.jus10.schedule.repositories.ClientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ClientRepository repository) {
		return args -> {
			Client client = new Client();
			client.setFirstName("Luis");
			client.setLastName("Paulo");

			repository.save(client);
			repository.findAll().forEach(System.out::println);
		};
	}
}
