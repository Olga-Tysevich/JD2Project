package it.academy.utils.converters;

import it.academy.dto.account.AccountDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class AccountConverter{

    public AccountDTO convertToDTO(Account account) {
        ServiceCenter serviceCenter = account.getServiceCenter();
        Long serviceCenterId = serviceCenter != null ? serviceCenter.getId() : null;
        String serviceCenterName = serviceCenter != null ? serviceCenter.getServiceName() : StringUtils.EMPTY;
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

    public Account convertToEntity(AccountDTO accountDTO) {
        return Account.builder()
                .id(accountDTO.getId())
                .isActive(Objects.requireNonNullElse(accountDTO.getIsActive(), true))
                .email(accountDTO.getEmail())
                .userName(accountDTO.getUserName())
                .userSurname(accountDTO.getUserSurname())
                .password(accountDTO.getPassword())
                .role(accountDTO.getRole())
                .build();
    }

    public List<AccountDTO> convertToDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
