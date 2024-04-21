package it.academy.utils.converters.account;

import it.academy.dto.account.ChangeAccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AccountConverter {

    public static AccountDTO convertToDTO(Account account) {
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

    public static Account convertToEntity(CreateAccountDTO account) {
        return Account.builder()
                .isActive(true)
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .password(account.getPassword())
                .role(account.getRole())
                .build();
    }

    public static Account convertToEntity(ChangeAccountDTO account) {
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


    public static List<AccountDTO> convertToDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
