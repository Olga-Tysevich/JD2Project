package it.academy.utils.services.converters;

import it.academy.dto.req.account.RoleDTOReq;
import it.academy.entities.account.role.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleConverter {

    public static RoleDTOReq convertToDTOReq(it.academy.entities.account.role.Role req) {
        return RoleDTOReq.builder()
                .id(req.getId())
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

    public static List<RoleDTOReq> convertListToDTOReq(List<it.academy.entities.account.role.Role> roles) {
        return roles.stream()
                .map(RoleConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static Role convertToEntity(RoleDTOReq req) {
        return Role.builder()
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

}
