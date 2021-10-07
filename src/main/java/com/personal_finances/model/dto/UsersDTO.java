package com.personal_finances.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal_finances.model.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 500)
    private String name;
    @NotNull
    private String cpf;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private LoginUser login;
}
