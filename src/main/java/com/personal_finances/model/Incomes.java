package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incomes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_revenue")
    private Long id;
    private Double value;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;
}
