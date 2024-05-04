package it.academy.services.account;

import it.academy.dto.TablePage2;
import it.academy.dto.account.AccountFormDTO;
import it.academy.dto.account.ChangeAccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.AccountDTO;
import java.util.Map;

public interface AccountService {

    AccountFormDTO create(CreateAccountDTO account);

    String update(ChangeAccountDTO account);

    Map<Long, String> getServiceCentersForAccountForm();

    void delete(long id);

    AccountDTO find(long id);

    TablePage2<AccountDTO> findForPage(int pageNumber);

    TablePage2<AccountDTO> findForPageByFilter(int pageNumber, String filter, String input);

    TablePage2<AccountDTO> findForPageByServiceCenter(int pageNumber, String input);

}
