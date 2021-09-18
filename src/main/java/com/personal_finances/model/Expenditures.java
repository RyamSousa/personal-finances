package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expenditures {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_expenditure")
    private Long id;
    private Double value;
    private String description;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;
}
