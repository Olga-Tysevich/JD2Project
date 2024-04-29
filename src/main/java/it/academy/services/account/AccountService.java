package it.academy.services.account;

import it.academy.dto.account.AccountFormDTO;
import it.academy.dto.account.ChangeAccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.ListForPage;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectCreationFailed;
import it.academy.exceptions.common.ObjectNotFound;

public interface AccountService {

    AccountFormDTO createAccount(CreateAccountDTO account) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered,
            ValidationException, ObjectCreationFailed, ObjectNotFound;

    void updateAccount(ChangeAccountDTO account) throws EmailAlreadyRegistered, ObjectNotFound, ValidationException;

    void deleteAccount(long id);

    AccountDTO findAccount(long id);

    ListForPage<AccountDTO> findAccounts(int pageNumber, String filter, String input);

}
