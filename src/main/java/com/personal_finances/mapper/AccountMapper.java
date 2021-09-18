package com.personal_finances.mapper;

import com.personal_finances.model.Accounts;
import com.personal_finances.model.dto.AccountsDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    public AccountsDTO toDto(Accounts account) {
        AccountsDTO dto = new AccountsDTO();

        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        dto.setCreateDate(account.getCreateDate());
        dto.setUser(account.getUser());

        return dto;
    }

    public AccountsDTO optionaltoDto(Optional<Accounts> accounts) {
        AccountsDTO dto = new AccountsDTO();

        dto.setId(accounts.get().getId());
        dto.setAccountNumber(accounts.get().getAccountNumber());
        dto.setBalance(accounts.get().getBalance());
        dto.setCreateDate(accounts.get().getCreateDate());
        dto.setUser(accounts.get().getUser());

        return dto;
    }

    public Accounts toAccounts(AccountsDTO dto) {
        Accounts accounts = new Accounts();

        accounts.setId(dto.getId());
        accounts.setAccountNumber(dto.getAccountNumber());
        accounts.setBalance(dto.getBalance());
        accounts.setCreateDate(dto.getCreateDate());
        accounts.setUser(dto.getUser());

        return accounts;
    }

    public List<AccountsDTO> toListDTO(List<Accounts> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
