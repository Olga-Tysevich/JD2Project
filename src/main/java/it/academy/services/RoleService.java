package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.PermissionDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface RoleService {

    RespListDTO<PermissionDTOReq> findPermissions();

    RespListDTO<PermissionDTOReq> findPermissions(int pageNumber, int listSize);

    RespListDTO<PermissionDTOReq> findPermissions(ParametersForSearchDTO parameters);

    RespDTO<RoleDTOReq> createRole(RoleDTOReq req);

    RespListDTO<RoleDTOReq> findRoles();

    RespListDTO<RoleDTOReq> findRoles(int pageNumber, int listSize);

    RespListDTO<RoleDTOReq> findRoles(ParametersForSearchDTO parameters);
}
