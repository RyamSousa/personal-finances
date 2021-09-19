package com.personal_finances.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevenuesDTO {
    private Long id;
    @NotNull
    private Double value;
    private String description;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull
    private Accounts account;
    private Categories category;

}