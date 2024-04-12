package it.academy.utils.converters.account;

import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.entities.account.Account;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.utils.converters.service_center.ServiceCenterConverter;
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
