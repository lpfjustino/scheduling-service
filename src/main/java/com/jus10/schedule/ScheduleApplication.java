package com.jus10.schedule;

import com.jus10.schedule.models.Client;
import com.jus10.schedule.models.Professional;
import com.jus10.schedule.models.Scheduling;
import com.jus10.schedule.models.Service;
import com.jus10.schedule.repositories.ClientRepository;
import com.jus10.schedule.repositories.ProfessionalRepository;
import com.jus10.schedule.repositories.SchedulingRepository;
import com.jus10.schedule.repositories.ServiceRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class ScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ClientRepository clientRepository, ProfessionalRepository professionalRepository,
						   ServiceRepository serviceRepository, SchedulingRepository schedulingRepository) {
		return args -> {
			Client client = createClient(clientRepository);
			Professional professional = createProfessional(professionalRepository);
			Service service = createService(serviceRepository);

			Scheduling scheduling = createScheduling(schedulingRepository, client, service);
		};
	}

	private Scheduling createScheduling(SchedulingRepository schedulingRepository,
								  Client clientId, Service serviceId) {
		Scheduling scheduling = new Scheduling();
		scheduling.setClient(clientId);
		scheduling.setService(serviceId);

		return schedulingRepository.save(scheduling);
	}

	private Service createService(ServiceRepository serviceRepository) {
		UUID id = UUID.randomUUID();
		Service service = new Service();
		service.setId( UUID.randomUUID());
		service.setName("Massagem");

		return serviceRepository.save(service);
	}

	private Client createClient(ClientRepository repository) {
		Client client = new Client();
		client.setId(UUID.randomUUID());
		client.setFirstName("Luis");
		client.setLastName("Paulo");

		return repository.save(client);
	}

	private Professional createProfessional(ProfessionalRepository repository) {
		Professional professional = new Professional();
		professional.setId(UUID.randomUUID());
		professional.setFirstName("Falchi");
		professional.setLastName("Justino");

		return repository.save(professional);
	}
}
