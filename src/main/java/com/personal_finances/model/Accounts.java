package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer balance;
    private LocalDateTime createDate;

    @OneToMany
    private Set<Revenues> revenues = new HashSet<>();
    @OneToMany
    private Set<Expenditures> expenditure = new HashSet<>();

}
