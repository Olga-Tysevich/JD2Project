package it.academy.utils.services;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.entities.account.Account;
import it.academy.entities.account.role.Role;
import it.academy.exceptions.ConversionError;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.Constants.*;

@UtilityClass
public class Converter {

    public static <T, R> List<T> convertListToDTO(List<R> resultList, Class<T> resultClazz) throws ConversionError {
        List<T> result = new ArrayList<>();
        for (R t : resultList) {
            T dto = tryConvertToDTO(t, resultClazz);
            result.add(dto);
        }
        return result;
    }

    public static <T, R> T tryConvertToEntity(R req, Class<T> toClass) throws ConversionError {
        switch (req.getClass().getSimpleName()) {
            case ROLE_DTO_REQ_CLASS:
                return toClass.cast(convertToEntity((RoleDTOReq) req));
            case ACCOUNT_DTO_REQ_CLASS:
                return toClass.cast(convertToEntity((AccountDTOReq) req));
            default:
                throw new ConversionError(String.format(CONVERSION_ERROR, req.getClass()));
        }
    }

    public static <T, R> T tryConvertToDTO(R object, Class<T> toClass) throws ConversionError {
        switch (object.getClass().getSimpleName()) {
            case ROLE_CLASS:
                return toClass.cast(convertToDTO((Role) object));
            case ACCOUNT_CLASS:
                return toClass.cast(convertToDTO((Account) object));
            case EMAIL_DTO_CLASS:
                return toClass.cast(convertAccountToEmailDTO((Account) object));
            default:
                throw new ConversionError(String.format(CONVERSION_ERROR, object.getClass()));
        }
    }

    private static Role convertToEntity(RoleDTOReq req) {
        return Role.builder()
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

    private static Account convertToEntity(AccountDTOReq req) {
        return Account.builder()
                .isActive(req.getIsActive())
                .email(req.getEmail())
                .userName(req.getUserName())
                .userSurname(req.getUserSurname())
                .password(req.getPassword())
                .role(req.getRole())
                .build();
    }

    private static AccountDTOReq convertToDTO(Account account) {
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

    private static RoleDTOReq convertToDTO(Role req) {
        return RoleDTOReq.builder()
                .id(req.getId())
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

    private static<T extends Account> EmailDTO convertAccountToEmailDTO(T account) {
        return EmailDTO.builder()
                .email(account.getEmail())
                .build();
    }
}
