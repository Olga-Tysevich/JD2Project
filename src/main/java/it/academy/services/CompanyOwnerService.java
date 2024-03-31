package it.academy.services;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;

public interface CompanyOwnerService extends CompanyAdminService {

    RespDTO<AccountDTO> addAdminAccount(AccountDTOReq req);

    RespDTO<AccountDTO> changeAdminAccount(AccountDTOReq req);
}
