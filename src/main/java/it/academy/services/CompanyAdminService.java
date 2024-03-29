package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface CompanyAdminService {

    RespDTO createRole(RoleDTOReq req);

    RespListDTO<RoleDTOReq> findAllRoles();

    RespListDTO<RoleDTOReq> findAllRoles(int pageNumber, int listSize);

    RespListDTO<RoleDTOReq> findAllRoles(ParametersForSearchDTO parameters);

    RespListDTO<AccountDTOReq> findAllAccounts();

    RespListDTO<AccountDTOReq> findAllAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTOReq> findAllAccounts(ParametersForSearchDTO parameters);

    RespListDTO<AccountDTOReq> findAllBlockedAccounts();

    RespListDTO<AccountDTOReq> findAllBlockedAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTOReq> findAllBlockedAccounts(ParametersForSearchDTO parameters);

}
