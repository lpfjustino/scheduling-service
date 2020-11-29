package com.jus10.schedule.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class AppointmentCreationDTO {
    private UUID clientId;
    private UUID serviceId;
    private Date date;
}
