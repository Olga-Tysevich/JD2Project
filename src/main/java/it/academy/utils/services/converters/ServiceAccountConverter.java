package it.academy.utils.services.converters;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.ServiceAccount;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceAccountConverter {

    public static List<AccountDTO> convertListToDTO(List<ServiceAccount> serviceAccounts) {
        return serviceAccounts.stream()
                .map(AccountConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static ServiceAccount convertAccountDTOReqToEntity(AccountDTOReq req) {
        return ServiceAccount.builder()
                .isActive(req.getIsActive())
                .email(req.getEmail())
                .userName(req.getUserName())
                .userSurname(req.getUserSurname())
                .password(req.getPassword())
                .role(req.getRole())
                .serviceCenter(req.getServiceCenter())
                .build();
    }

    public static AccountDTOReq convertToDTOReq(ServiceAccount account) {
        return AccountDTOReq.builder()
                .id(account.getId())
                .isActive(account.getIsActive())
                .email(account.getEmail())
                .userName(account.getUserName())
                .userSurname(account.getUserSurname())
                .password(account.getPassword())
                .role(account.getRole())
                .serviceCenter(account.getServiceCenter())
                .build();
    }
}
