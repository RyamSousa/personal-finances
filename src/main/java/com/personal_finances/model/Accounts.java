package com.personal_finances.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Long accountNumber;

    @NotNull
    private String financialInstitution;

    private Double balance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime createDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
