package it.academy.utils.converters;

import it.academy.dto.AccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.entities.Account;
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

    public static Account convertDTOToEntity(AccountDTO account) {
        ServiceCenterDTO repairWorkshop = account.getServiceCenter() ;
        ServiceCenter serviceCenterDTO = repairWorkshop != null? ServiceCenterConverter.convertDTOToEntity(repairWorkshop) : null;
        return Account.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .role(account.getRole())
                .serviceCenter(serviceCenterDTO)
                .build();
    }

    public static List<AccountDTO> convertListToDTO(List<Account> accounts) {
        return accounts.stream()
                .map(AccountConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Account> convertDTOListToEntityList(List<AccountDTO> accounts) {
        return accounts.stream()
                .map(AccountConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }
}
