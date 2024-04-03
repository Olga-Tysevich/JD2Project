package it.academy.converters.accounts;

import it.academy.converters.Converter;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.entities.account.role.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleConverter implements Converter<Role, RoleDTOReq> {

    @Override
    public RoleDTOReq convertToDTOReq(Role req) {
        return RoleDTOReq.builder()
                .id(req.getId())
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

    @Override
    public Role convertDTOReqToEntity(RoleDTOReq req) {
        return Role.builder()
                .id(req.getId())
                .name(req.getName())
                .permissions(req.getPermissions())
                .build();
    }

    @Override
    public List<RoleDTOReq> convertListToDTOReq(List<Role> roles) {
        return roles.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> convertDTOReqListToEntityList(List<RoleDTOReq> roles) {
        return roles.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
