package it.academy.services;

import it.academy.dto.req.ChangeAccountDTO;
import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.common.AccessDenied;

public interface AdminService {

    void createAccount(CreateAccountDTO account) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered, AccessDenied;

    void updateAccount(ChangeAccountDTO account) throws EmailAlreadyRegistered, AccessDenied;

    AccountDTO findAccount(long id);

    ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber);

    ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber, String filter, String input);


}
