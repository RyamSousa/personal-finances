package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "id_account")
    private Long id;
    private Long accountNumber;
    private String financialInstitution;
    private Double balance;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
