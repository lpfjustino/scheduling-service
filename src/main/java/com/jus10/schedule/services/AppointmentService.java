package com.jus10.schedule.services;

import com.jus10.schedule.models.Client;
import com.jus10.schedule.models.Scheduling;
import com.jus10.schedule.models.Service;
import com.jus10.schedule.repositories.ClientRepository;
import com.jus10.schedule.repositories.SchedulingRepository;
import com.jus10.schedule.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Date;
import java.util.UUID;

@org.springframework.stereotype.Service
public class AppointmentService {
    private final SchedulingRepository schedulingRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public AppointmentService(SchedulingRepository schedulingRepository, ClientRepository clientRepository,
                              ServiceRepository serviceRepository) {
        this.schedulingRepository = schedulingRepository;
        this.clientRepository = clientRepository;
        this.serviceRepository = serviceRepository;
    }

    public void createAppointment(UUID clientId, UUID serviceId, Date date) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("No such client %s", clientId)));
        Service service = serviceRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("No such service %s", serviceId)));

        Scheduling scheduling = new Scheduling();
        scheduling.setClient(client);
        scheduling.setService(service);
        scheduling.setDate(date);

        schedulingRepository.save(scheduling);
    }
}
