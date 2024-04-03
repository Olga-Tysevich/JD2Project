package it.academy.converters.accounts;

import it.academy.converters.AccountConverter;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.Account;
import java.util.List;
import java.util.stream.Collectors;

public class AccountConverterImpl implements AccountConverter<Account, AccountDTOReq> {

    @Override
    public AccountDTOReq convertToDTOReq(Account account) {
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

    @Override
    public Account convertDTOReqToEntity(AccountDTOReq req) {
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

    @Override
    public AccountDTO convertToDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .build();
    }

    @Override
    public Account convertAccountDTOToEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .isActive(accountDTO.getIsActive())
                .email(accountDTO.getEmail())
                .userName(accountDTO.getUserName())
                .userSurname(accountDTO.getUserSurname())
                .build();
    }

    @Override
    public List<AccountDTO> convertListToDTO(List<Account> accounts) {
        return accounts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> convertListAccountDTOToEntity(List<AccountDTO> accountDTOList) {
        return accountDTOList.stream()
                .map(this::convertAccountDTOToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTOReq> convertListToDTOReq(List<Account> accounts) {
        return accounts.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> convertDTOReqListToEntityList(List<AccountDTOReq> reqList) {
        return reqList.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }


}
