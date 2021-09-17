package com.personal_finances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expenditures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_expenditure")
    private Long id;
    private Integer value;
    private String description;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
}
