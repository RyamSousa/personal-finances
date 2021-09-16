package com.personal_finances.model.dto;

import com.personal_finances.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 500)
    private String name;

    @NotNull
    private String cpf;

    @NotNull
    private LocalDate birthdate;

    @NotNull
    private List<Account> accounts;
}
