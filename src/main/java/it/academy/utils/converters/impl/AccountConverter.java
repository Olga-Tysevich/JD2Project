package it.academy.utils.converters.impl;

import it.academy.dto.account.ChangeAccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import it.academy.utils.converters.EntityConverter;
import java.util.List;
import java.util.stream.Collectors;

public class AccountConverter implements EntityConverter<AccountDTO, Account> {

    public AccountDTO convertToDTO(Account account) {
        ServiceCenter serviceCenter = account.getServiceCenter();
        Long serviceCenterId = serviceCenter != null ? serviceCenter.getId() : null;
        String serviceCenterName = serviceCenter != null ? serviceCenter.getServiceName() : "";
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .role(account.getRole())
                .serviceCenterId(serviceCenterId)
                .serviceCenterName(serviceCenterName)
                .build();
    }

    @Override
    public Account convertToEntity(AccountDTO dto) {
        return Account.builder()
                .isActive(true)
                .email(dto.getEmail())
                .userName(dto.getUserName())
                .userSurname(dto.getUserSurname())
                .role(dto.getRole())
                .build();
    }

    public Account convertToEntity(CreateAccountDTO account) {
        return Account.builder()
                .isActive(true)
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .password(account.getPassword())
                .role(account.getRole())
                .build();
    }

    public Account convertToEntity(ChangeAccountDTO account) {
        return Account.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .password(account.getPassword())
                .role(account.getRole())
                .build();
    }


    public List<AccountDTO> convertToDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
