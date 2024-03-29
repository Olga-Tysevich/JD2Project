package it.academy.utils;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.entities.account.Account;
import it.academy.entities.account.role.Role;
import it.academy.exceptions.ConversionError;
import lombok.experimental.UtilityClass;

import static it.academy.utils.Constants.CONVERSION_ERROR;

@UtilityClass
public class Converter {

    public static<T, R> T convertToEntity(R req) throws ConversionError {
        if (req instanceof RoleDTOReq) {
            return convertToEntity(req);
        } else if (req instanceof AccountDTOReq) {
            return convertToEntity(req);
        } else {
            throw new ConversionError(String.format(CONVERSION_ERROR, req.getClass()));
        }
    }

    public static<T, R> T convertToDTO(R object) throws ConversionError {
        if (object instanceof RoleDTOReq) {
            return convertToDTO(object);
        } else if (object instanceof AccountDTOReq) {
            return convertToDTO(object);
        } else {
            throw new ConversionError(String.format(CONVERSION_ERROR, object.getClass()));
        }
    }

    public static Role convertToEntity(RoleDTOReq req) {
        return Role.builder()
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

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

    public static AccountDTOReq convertToDTO(Account account) {
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

    public static RoleDTOReq convertToDTO(Role req) {
        return RoleDTOReq.builder()
                .id(req.getId())
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }
}
