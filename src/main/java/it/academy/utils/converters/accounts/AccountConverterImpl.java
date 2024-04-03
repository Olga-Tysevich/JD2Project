package it.academy.utils.converters.accounts;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.Account;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AccountConverterImpl {

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

    public static Account convertDTOReqToEntity(AccountDTOReq req) {
        return Account.builder()
                .id(req.getId())
                .isActive(req.getIsActive())
                .email(req.getEmail())
                .userName(req.getUserName())
                .userSurname(req.getUserSurname())
                .password(req.getPassword())
                .role(req.getRole())
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

    public static Account convertAccountDTOToEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .isActive(accountDTO.getIsActive())
                .email(accountDTO.getEmail())
                .userName(accountDTO.getUserName())
                .userSurname(accountDTO.getUserSurname())
                .build();
    }

    public static List<AccountDTO> convertListToDTO(List<Account> accounts) {
        return accounts.stream()
                .map(AccountConverterImpl::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Account> convertListAccountDTOToEntity(List<AccountDTO> accountDTOList) {
        return accountDTOList.stream()
                .map(AccountConverterImpl::convertAccountDTOToEntity)
                .collect(Collectors.toList());
    }

    public static List<AccountDTOReq> convertListToDTOReq(List<Account> accounts) {
        return accounts.stream()
                .map(AccountConverterImpl::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<Account> convertDTOReqListToEntityList(List<AccountDTOReq> reqList) {
        return reqList.stream()
                .map(AccountConverterImpl::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }


}
