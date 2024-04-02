package it.academy.utils.converters.accounts;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.RepairWorkshopAccount;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceAccountConverter {

    public static List<AccountDTO> convertListToDTO(List<RepairWorkshopAccount> repairWorkshopAccounts) {
        return repairWorkshopAccounts.stream()
                .map(AccountConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static RepairWorkshopAccount convertAccountDTOReqToEntity(AccountDTOReq req) {
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
}
