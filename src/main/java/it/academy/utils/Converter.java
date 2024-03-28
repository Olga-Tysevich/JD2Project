package it.academy.utils;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Converter {

    public static Account convertToEntity(AccountDTOReq req) {
        return Account.builder()
                .isActive(req.getIsActive())
                .email(req.getEmail())
                .userName(req.getUserName())
                .userSurname(req.getUserSurname())
                .password(req.getPassword())
                .role(req.getRole())
                .build();
    }
}
