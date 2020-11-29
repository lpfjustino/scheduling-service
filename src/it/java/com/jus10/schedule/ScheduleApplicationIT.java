package com.jus10.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jus10.schedule.dtos.AppointmentCreationDTO;
import com.jus10.schedule.models.Client;
import com.jus10.schedule.models.Professional;
import com.jus10.schedule.models.Service;
import com.jus10.schedule.services.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("it")
@AutoConfigureTestDatabase(replace = NONE)
@SpringBootTest(classes = ScheduleApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ScheduleApplicationIT {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AppointmentService appointmentService;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void assert_create_client() throws Exception {
		createClient(UUID.randomUUID());

		mvc.perform(get("/clients"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("content[0].firstName", is("Luis")));
	}

	@Test
	public void assert_create_service() throws Exception {
		createService(UUID.randomUUID());

		mvc.perform(get("/services"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("content[0].name", is("Massagem")));
	}

	@Test
	public void assert_create_professional() throws Exception {
		createProfessional();

		mvc.perform(get("/professionals"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("content[0].firstName", is("Falchi")));
	}

	@Test
	public void assert_can_schedule() throws Exception {
		UUID clientId = UUID.randomUUID();
		UUID serviceId = UUID.randomUUID();
		createClient(clientId);
		createService(serviceId);

		AppointmentCreationDTO appointmentCreationDTO = AppointmentCreationDTO.builder()
				.clientId(clientId)
				.serviceId(serviceId)
				.date(Date.from(Instant.now()))
				.build();

		mvc.perform(post("/appointment")
				.content(mapper.writeValueAsString(appointmentCreationDTO))
				.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}

	private void createClient(UUID id) throws Exception {
		Client client = makeClient(id);
		mvc.perform(post("/clients").content(mapper.writeValueAsString(client)));
	}

	private void createProfessional() throws Exception {
		Professional professional = makeProfessional();
		mvc.perform(post("/professionals").content(mapper.writeValueAsString(professional)))
				.andExpect(status().isCreated());
	}

	private void createService(UUID id) throws Exception {
		Service service = makeService(id);
		mvc.perform(post("/services").content(mapper.writeValueAsString(service)));
	}

	private Client makeClient(UUID id) {
		Client client = new Client();
		client.setId(id);
		client.setFirstName("Luis");
		client.setLastName("Paulo");

		return client;
	}

	private Professional makeProfessional() {
		Professional professional = new Professional();
		professional.setId(UUID.randomUUID());
		professional.setFirstName("Falchi");
		professional.setLastName("Justino");

		return professional;
	}

	private Service makeService(UUID id) {
		Service service = new Service();
		service.setId(id);
		service.setName("Massagem");

		return service;
	}
}
