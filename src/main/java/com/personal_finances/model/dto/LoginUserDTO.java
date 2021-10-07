package com.personal_finances.model.dto;


import com.personal_finances.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO {

    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotNull
    private List<Role> roles = new ArrayList<>();
}
