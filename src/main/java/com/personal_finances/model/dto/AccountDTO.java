package com.personal_finances.model.dto;

import com.personal_finances.model.Expenditure;
import com.personal_finances.model.Revenue;
import com.personal_finances.model.User;
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
public class AccountDTO {
    private Long id;
    private Integer balance;
    private LocalDate createDate;
    @NotNull
    private User userAccount;
    private List<Revenue> revenues;
    private List<Expenditure> expenditure;
}
