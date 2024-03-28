package it.academy.dto.req.account;

import it.academy.entities.account.role.Permission;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RoleDTOReq {

    private Long id;

    private String name;

    private Set<Permission> permissions;
}
