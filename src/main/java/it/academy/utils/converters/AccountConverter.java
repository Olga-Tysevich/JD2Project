package it.academy.utils.converters;

import it.academy.dto.AccountDTO;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.entities.Account;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AccountConverter {

    public static AccountDTO convertToDTO(Account account) {
        RepairWorkshop repairWorkshop = account.getRepairWorkshop() ;
        RepairWorkshopDTO repairWorkshopDTO = repairWorkshop != null?
                RepairWorkshopConverter.convertToDTO(repairWorkshop) : null;
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .role(account.getRole())
                .repairWorkshop(repairWorkshopDTO)
                .build();
    }

    public static Account convertDTOToEntity(AccountDTO account) {
        RepairWorkshopDTO repairWorkshop = account.getRepairWorkshop() ;
        RepairWorkshop repairWorkshopDTO = repairWorkshop != null? RepairWorkshopConverter.convertDTOToEntity(repairWorkshop) : null;
        return Account.builder()
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .role(account.getRole())
                .repairWorkshop(repairWorkshopDTO)
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
