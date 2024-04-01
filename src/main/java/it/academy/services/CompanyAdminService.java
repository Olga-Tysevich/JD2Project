package it.academy.services;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.account.RoleDTOReq;
import it.academy.dto.req.service_center.ServiceCenterDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface CompanyAdminService extends UserService {

    RespDTO<RoleDTOReq> createRole(RoleDTOReq req);

    RespListDTO<RoleDTOReq> findRoles();

    RespListDTO<RoleDTOReq> findRoles(int pageNumber, int listSize);

    RespListDTO<RoleDTOReq> findRoles(ParametersForSearchDTO parameters);

    RespListDTO<AccountDTO> findAccounts();

    RespListDTO<AccountDTO> findAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTO> findAccounts(ParametersForSearchDTO parameters);

    RespListDTO<AccountDTO> findBlockedAccounts();

    RespListDTO<AccountDTO> findBlockedAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTO> findBlockedAccounts(ParametersForSearchDTO parameters);

    RespListDTO<AccountDTO> findServiceAccounts();

    RespListDTO<AccountDTO> findServiceAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTO> findServiceAccounts(ParametersForSearchDTO parameters);

    RespDTO<AccountDTO> addServiceAccount(AccountDTOReq req);

    RespDTO<ServiceCenterDTOReq> addServiceCenter(ServiceCenterDTOReq req);

    RespDTO<ServiceCenterDTOReq> changeServiceCenter(ServiceCenterDTOReq req);

    RespListDTO<ServiceCenterDTOReq> findServiceCenters();

    RespListDTO<ServiceCenterDTOReq> findServiceCenters(int pageNumber, int listSize);

    RespListDTO<ServiceCenterDTOReq> findServiceCenters(ParametersForSearchDTO parameters);
}
