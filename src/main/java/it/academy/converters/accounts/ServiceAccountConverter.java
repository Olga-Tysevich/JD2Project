package it.academy.converters.accounts;

import it.academy.converters.AccountConverter;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.RepairWorkshopAccount;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceAccountConverter implements AccountConverter<RepairWorkshopAccount, AccountDTOReq> {

    @Override
    public AccountDTOReq convertToDTOReq(RepairWorkshopAccount account) {
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

    @Override
    public RepairWorkshopAccount convertDTOReqToEntity(AccountDTOReq req) {
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

    @Override
    public AccountDTO convertToDTO(RepairWorkshopAccount account) {
        return AccountDTO.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .repairWorkshop(account.getRepairWorkshop())
                .build();
    }

    @Override
    public RepairWorkshopAccount convertAccountDTOToEntity(AccountDTO accountDTO) {
        return RepairWorkshopAccount.builder()
                .id(accountDTO.getId())
                .isActive(accountDTO.getIsActive())
                .email(accountDTO.getEmail())
                .userName(accountDTO.getUserName())
                .userSurname(accountDTO.getUserSurname())
                .repairWorkshop(accountDTO.getRepairWorkshop())
                .build();
    }

    @Override
    public List<AccountDTO> convertListToDTO(List<RepairWorkshopAccount> accounts) {
        return accounts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairWorkshopAccount> convertListAccountDTOToEntity(List<AccountDTO> accountDTOList) {
        return accountDTOList.stream()
                .map(this::convertAccountDTOToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTOReq> convertListToDTOReq(List<RepairWorkshopAccount> accounts) {
        return accounts.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairWorkshopAccount> convertDTOReqListToEntityList(List<AccountDTOReq> reqList) {
        return reqList.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }


}
