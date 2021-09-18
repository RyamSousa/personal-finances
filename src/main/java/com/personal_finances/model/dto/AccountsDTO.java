package com.personal_finances.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal_finances.model.Users;
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
public class AccountsDTO {
    private Long id;
    @NotNull
    private Long accountNumber;
    private Double balance;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate createDate;

    //@NotNull
    private Users user;
}
