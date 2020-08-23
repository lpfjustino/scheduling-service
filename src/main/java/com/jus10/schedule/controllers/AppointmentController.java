package com.jus10.schedule.controllers;

import com.jus10.schedule.dtos.AppointmentCreationDTO;
import com.jus10.schedule.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public void createAppointment(@RequestBody AppointmentCreationDTO appointment) {
        appointmentService.createAppointment(appointment.getClientId(), appointment.getServiceId(),
                appointment.getDate());
    }
}
