package it.academy.services.admin;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.services.UserService;

public interface AdminService extends UserService {

    RespDTO<AccountDTO> addAdminAccount(AccountDTOReq req);

    RespDTO<AccountDTO> changeAdminAccount(AccountDTOReq req);

    RespDTO<AccountDTO> addRepairWorkshopAccount(AccountDTOReq req);

    RespDTO<AccountDTO> changeRepairWorkshopAccount(AccountDTOReq req);

    RespDTO<AccountDTO> findAdminAccountByEmail(AccountDTOReq req);

    RespListDTO<AccountDTO> findAccounts();

    RespListDTO<AccountDTO> findAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTO> findAccounts(ParametersForSearchDTO parameters);

    RespListDTO<AccountDTO> findBlockedAccounts();

    RespListDTO<AccountDTO> findBlockedAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTO> findBlockedAccounts(ParametersForSearchDTO parameters);

    RespListDTO<AccountDTO> findServiceAccounts();

    RespListDTO<AccountDTO> findServiceAccounts(int pageNumber, int listSize);

    RespListDTO<AccountDTO> findServiceAccounts(ParametersForSearchDTO parameters);
}
