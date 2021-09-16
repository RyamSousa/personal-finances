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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer balance;
    private LocalDateTime createDate;

    @OneToOne
    private User userAccount;
    @ManyToMany
    private Set<Revenue> revenues = new HashSet<>();
    @ManyToMany
    private Set<Expenditure> expenditure = new HashSet<>();

}
