package it.academy.services;

import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.resp.DTOResp;

public interface CompanyAdminService {

    DTOResp createRole(RoleDTOReq req);

}
