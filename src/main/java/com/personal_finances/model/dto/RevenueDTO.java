package com.personal_finances.model.dto;

import com.personal_finances.model.Account;
import com.personal_finances.model.Category;
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
public class RevenueDTO {
    private Long id;
    @NotNull
    private Integer value;
    private String description;
    @NotNull
    private LocalDate date;

    private Category category;
    @NotNull
    private Account account;
}
