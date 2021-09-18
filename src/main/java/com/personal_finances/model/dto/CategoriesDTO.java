package com.personal_finances.model.dto;

import com.personal_finances.model.Expenditures;
import com.personal_finances.model.Revenues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDTO {
    private Long id;
    @NotNull
    private String name;
}
