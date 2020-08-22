package com.jus10.schedule.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String rg;
    private String cpf;
    private Date birthDate;

}
