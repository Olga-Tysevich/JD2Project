package it.academy.utils.converters;

import it.academy.dto.req.ChangeAccountDTO;
import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.req.ServiceCenterDTO;
import it.academy.entities.Account;
import it.academy.entities.ServiceCenter;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AccountConverter {

    public static AccountDTO convertToDTO(Account account) {
        ServiceCenter serviceCenter = account.getServiceCenter() ;
        ServiceCenterDTO serviceCenterDTO = serviceCenter != null?
                ServiceCenterConverter.convertToDTO(serviceCenter) : null;
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .role(account.getRole())
                .serviceCenter(serviceCenterDTO)
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
