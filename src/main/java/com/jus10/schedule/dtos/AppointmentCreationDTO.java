package com.jus10.schedule.dtos;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AppointmentCreationDTO {
    private UUID clientId;
    private UUID serviceId;
    private Date date;
}
