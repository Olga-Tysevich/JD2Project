package it.academy.utils.services.converters.accounts;

import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.entities.account.role.Permission;
import it.academy.entities.account.role.PermissionCategory;
import it.academy.entities.account.role.PermissionType;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionConverter {

    public static PermissionDTOReq convertToDTOReq(Permission req) {
        return PermissionDTOReq.builder()
                .id(req.getId())
                .category(req.getCategory().name())
                .type(req.getType().name())
                .build();
    }

    public static List<PermissionDTOReq> convertListToDTOReq(List<Permission> permissions) {
        return permissions.stream()
                .map(PermissionConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static Permission convertToEntity(PermissionDTOReq req) {
        return Permission.builder()
                .id(req.getId())
                .category(PermissionCategory.valueOf(req.getCategory()))
                .type(PermissionType.valueOf(req.getType()))
                .build();
    }
}
