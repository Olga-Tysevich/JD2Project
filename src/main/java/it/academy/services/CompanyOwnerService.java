package it.academy.services;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.DTOResp;

public interface CompanyOwnerService extends CompanyAdminService {

    DTOResp addAdminAccount(AccountDTOReq req);

    DTOResp changeAdminAccount(AccountDTOReq req);

    DTOResp deactivateAdminAccount(AccountDTOReq req);

}
