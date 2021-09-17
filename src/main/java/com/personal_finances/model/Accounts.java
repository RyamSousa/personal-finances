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
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_account")
    private Long id;
    private Integer balance;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @OneToMany
    private List<Revenues> revenues = new ArrayList<>();
    @OneToMany
    private List<Expenditures> expenditure = new ArrayList<>();

}
