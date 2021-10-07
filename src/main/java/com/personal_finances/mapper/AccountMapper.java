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

    public AccountsDTO optionalToDto(Optional<Accounts> account) {
        AccountsDTO dto = new AccountsDTO();

        dto.setId(account.get().getId());
        dto.setAccountNumber(account.get().getAccountNumber());
        dto.setBalance(account.get().getBalance());
        dto.setCreateDate(account.get().getCreateDate());
        dto.setUser(account.get().getUser());

        return dto;
    }

    public Accounts toAccounts(AccountsDTO dto) {
        Accounts account = new Accounts();

        account.setId(dto.getId());
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        account.setCreateDate(dto.getCreateDate());
        account.setUser(dto.getUser());

        return account;
    }

    public List<AccountsDTO> toListDTO(List<Accounts> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
