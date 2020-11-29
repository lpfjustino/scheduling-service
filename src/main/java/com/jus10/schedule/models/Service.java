package com.jus10.schedule.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Service {
    @Id
    UUID id;

    String name;
    String category;
}
