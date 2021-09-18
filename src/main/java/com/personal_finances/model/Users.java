package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_user")
    private Long id;
    private String name;
    private String cpf;
    private LocalDateTime birthdate;

    @OneToMany(mappedBy = "user")
    private List<Accounts> accounts = new ArrayList<>();
}
