package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_account")
    private Long id;
    private Long accountNumber;
    private Double balance;
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
