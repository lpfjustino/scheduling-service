package com.jus10.schedule.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Client {
    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private String rg;
    private String cpf;
    private Date birthDate;

    @OneToMany(mappedBy = "client")
    Set<Scheduling> appointments;

}
