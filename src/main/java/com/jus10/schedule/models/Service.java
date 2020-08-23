package com.jus10.schedule.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    UUID id;

    String name;
    String category;
}
