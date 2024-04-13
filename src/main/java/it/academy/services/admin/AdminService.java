package it.academy.services.admin;

import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.common.AccessDenied;

import java.util.List;

public interface AdminService {

    void createAccount(CreateAccountDTO account) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered, AccessDenied;

    void updateAccount(ChangeAccountDTO account) throws EmailAlreadyRegistered, AccessDenied;

    AccountDTO findAccount(long id);

    ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber);

    ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber, String filter, String input);


}
