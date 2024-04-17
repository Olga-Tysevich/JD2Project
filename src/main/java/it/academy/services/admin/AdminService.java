package it.academy.services.admin;

import it.academy.dto.req.ChangeAccountDTO;
import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectCreationFailed;
import it.academy.exceptions.common.ObjectNotFound;

public interface AdminService {

    void createAccount(CreateAccountDTO account) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered,
            ValidationException, ObjectCreationFailed, ObjectNotFound;

    void updateAccount(ChangeAccountDTO account) throws EmailAlreadyRegistered, ObjectNotFound, ValidationException;

    AccountDTO findAccount(long id);

    ListForPage<AccountDTO> findAccounts(int pageNumber);

    ListForPage<AccountDTO> findAccounts(int pageNumber, String filter, String input);

    ListForPage<AccountDTO> findServiceAccounts(long id, int pageNumber);

    ListForPage<AccountDTO> findServiceAccounts(long id, int pageNumber, String filter, String input);


}
