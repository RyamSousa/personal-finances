package com.personal_finances.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal_finances.model.Expenditures;
import com.personal_finances.model.Revenues;
import com.personal_finances.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDTO {
    private Long id;
    private Integer balance;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate createDate;

    @NotNull
    private Users user;
    private List<Revenues> revenues;
    private List<Expenditures> expenditure;
}
