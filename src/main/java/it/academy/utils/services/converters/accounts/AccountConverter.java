package it.academy.utils.services.converters.accounts;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceAccount;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AccountConverter {

    public static List<AccountDTO> convertListToDTO(List<Account> accounts) {
        return accounts.stream()
                .map(AccountConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<AccountDTOReq> convertListToDTOReq(List<Account> roles) {
        return roles.stream()
                .map(AccountConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }


    public static Account convertAccountDTOReqToEntity(AccountDTOReq req) {
        return Account.builder()
                .isActive(req.getIsActive())
                .email(req.getEmail())
                .userName(req.getUserName())
                .userSurname(req.getUserSurname())
                .password(req.getPassword())
                .role(req.getRole())
                .build();
    }

    public static AccountDTOReq convertToDTOReq(Account account) {
        return AccountDTOReq.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .password(account.getPassword())
                .role(account.getRole())
                .build();
    }


    public static AccountDTO convertToDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .build();
    }

    public static AccountDTO convertToDTO(ServiceAccount account) {
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .serviceCenter(account.getServiceCenter())
                .build();
    }

}
