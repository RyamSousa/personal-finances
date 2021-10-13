package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "id_user")
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthdate;

    @OneToOne(cascade = ALL)
    private Logins login;
}
