package it.academy.utils.converters.accounts;

import it.academy.dto.req.account.RoleDTOReq;
import it.academy.entities.account.role.Role;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RoleConverter {

    public static RoleDTOReq convertToDTOReq(Role req) {
        return RoleDTOReq.builder()
                .id(req.getId())
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

    public static Role convertDTOReqToEntity(RoleDTOReq req) {
        return Role.builder()
                .id(req.getId())
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

    public static List<RoleDTOReq> convertListToDTOReq(List<Role> roles) {
        return roles.stream()
                .map(RoleConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<Role> convertDTOReqListToEntityList(List<RoleDTOReq> roles) {
        return roles.stream()
                .map(RoleConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
