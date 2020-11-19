package com.jus10.schedule.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name="appointments")
public class Scheduling {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name = "service_id")
    Service service;

    Date date;
    String status;
}
