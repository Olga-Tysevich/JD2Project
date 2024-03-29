package it.academy.services;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.DTOResp;
import it.academy.dto.resp.DTORespList;
import it.academy.entities.account.role.Role;

public interface CompanyAdminService {

    DTOResp createRole(RoleDTOReq req);

    DTORespList<RoleDTOReq> findAllRoles();

    DTORespList<RoleDTOReq> findAllRolesForPage(int pageNumber, int listSize);

    DTORespList<AccountDTOReq> findAllAccounts();

    DTORespList<AccountDTOReq> findAllAccounts(int pageNumber, int listSize);

    DTORespList<AccountDTOReq> findAllBlockedAccounts();

    DTORespList<AccountDTOReq> findAllBlockedAccounts(int pageNumber, int listSize);

}
