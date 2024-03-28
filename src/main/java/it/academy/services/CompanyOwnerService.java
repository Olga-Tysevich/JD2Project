package it.academy.services;

import it.academy.dto.req.AccountDTOReq;
import it.academy.dto.resp.DTOResp;

public interface CompanyOwnerService {

    DTOResp addAdminAccount(AccountDTOReq req);

    DTOResp changeAdminAccount(AccountDTOReq req);

    DTOResp deactivateAdminAccount(AccountDTOReq req);

}
