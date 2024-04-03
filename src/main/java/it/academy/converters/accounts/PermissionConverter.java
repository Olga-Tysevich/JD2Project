package it.academy.converters.accounts;

import it.academy.converters.Converter;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.PermissionCategory;
import it.academy.entities.account.role.PermissionType;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionConverter implements Converter<Permission, PermissionDTOReq> {

    @Override
    public PermissionDTOReq convertToDTOReq(Permission req) {
        return PermissionDTOReq.builder()
                .id(req.getId())
                .category(req.getCategory().name())
                .type(req.getType().name())
                .build();
    }

    @Override
    public Permission convertDTOReqToEntity(PermissionDTOReq req) {
        return Permission.builder()
                .id(req.getId())
                .category(PermissionCategory.valueOf(req.getCategory()))
                .type(PermissionType.valueOf(req.getType()))
                .build();
    }

    @Override
    public List<PermissionDTOReq> convertListToDTOReq(List<Permission> permissions) {
        return permissions.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Permission> convertDTOReqListToEntityList(List<PermissionDTOReq> permissions) {
        return permissions.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
