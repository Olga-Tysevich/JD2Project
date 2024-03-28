package it.academy.services.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dto.req.AccountDTOReq;
import it.academy.dto.resp.DTOResp;
import it.academy.entities.account.Account;
import it.academy.services.CompanyOwnerService;

public class CompanyOwnerServiceImpl implements CompanyOwnerService {
    private AccountDAO<Account> accountDAO = new AccountDAOImpl<>(Account.class);

    @Override
    public DTOResp addAdminAccount(AccountDTOReq req) {
        return null;
    }

    @Override
    public DTOResp changeAdminAccount(AccountDTOReq req) {
        return null;
    }

    @Override
    public DTOResp deactivateAdminAccount(AccountDTOReq req) {
        return null;
    }

}
