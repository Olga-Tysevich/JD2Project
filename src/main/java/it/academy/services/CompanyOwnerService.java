package it.academy.services;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;

public interface CompanyOwnerService extends CompanyAdminService {

    RespDTO addAdminAccount(AccountDTOReq req);

    RespDTO changeAdminAccount(AccountDTOReq req);
}
