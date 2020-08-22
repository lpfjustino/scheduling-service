package src.main.java;

import lombok.Data;

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
    private final UUID id;

    private final String firstName;
    private final String lastName;
    private String rg;
    private String cpf;
    private Date birthDate;

}
