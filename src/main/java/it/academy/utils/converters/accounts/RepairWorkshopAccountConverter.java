package it.academy.utils.converters.accounts;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.RepairWorkshopAccount;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairWorkshopAccountConverter {

    public static AccountDTOReq convertToDTOReq(RepairWorkshopAccount account) {
        return AccountDTOReq.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .password(account.getPassword())
                .role(account.getRole())
                .repairWorkshop(account.getRepairWorkshop())
                .build();
    }

    public static RepairWorkshopAccount convertDTOReqToEntity(AccountDTOReq req) {
        return RepairWorkshopAccount.builder()
                .id(req.getId())
                .isActive(req.getIsActive())
                .email(req.getEmail())
                .userName(req.getUserName())
                .userSurname(req.getUserSurname())
                .password(req.getPassword())
                .role(req.getRole())
                .repairWorkshop(req.getRepairWorkshop())
                .build();
    }

    public static AccountDTO convertToDTO(RepairWorkshopAccount account) {
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .repairWorkshop(account.getRepairWorkshop())
                .build();
    }

    public static RepairWorkshopAccount convertAccountDTOToEntity(AccountDTO accountDTO) {
        return RepairWorkshopAccount.builder()
                .id(accountDTO.getId())
                .isActive(accountDTO.getIsActive())
                .email(accountDTO.getEmail())
                .userName(accountDTO.getUserName())
                .userSurname(accountDTO.getUserSurname())
                .repairWorkshop(accountDTO.getRepairWorkshop())
                .build();
    }

    public static List<AccountDTO> convertListToDTO(List<RepairWorkshopAccount> accounts) {
        return accounts.stream()
                .map(RepairWorkshopAccountConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<RepairWorkshopAccount> convertListAccountDTOToEntity(List<AccountDTO> accountDTOList) {
        return accountDTOList.stream()
                .map(RepairWorkshopAccountConverter::convertAccountDTOToEntity)
                .collect(Collectors.toList());
    }

    public static List<AccountDTOReq> convertListToDTOReq(List<RepairWorkshopAccount> accounts) {
        return accounts.stream()
                .map(RepairWorkshopAccountConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<RepairWorkshopAccount> convertDTOReqListToEntityList(List<AccountDTOReq> reqList) {
        return reqList.stream()
                .map(RepairWorkshopAccountConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }


}
