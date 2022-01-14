package com.personal_finances.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expenses {
    @Id
    @GeneratedValue(strategy = AUTO, generator = "id_expenditure")
    private Long id;

    @NotNull
    private Double value;

    private String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;
}
