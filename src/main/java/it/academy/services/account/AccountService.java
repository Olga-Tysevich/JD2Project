package it.academy.services.account;

import it.academy.dto.TablePage2;
import it.academy.dto.account.AccountDTO;
import java.util.Map;

public interface AccountService {

    AccountDTO create(AccountDTO account);

    AccountDTO update(AccountDTO account);

    Map<Long, String> getServiceCentersForAccountForm();

    void delete(long id);

    AccountDTO find(long id);

    TablePage2<AccountDTO> findForPage(int pageNumber);

    TablePage2<AccountDTO> findForPageByFilter(int pageNumber, String filter, String input);

    TablePage2<AccountDTO> findForPageByServiceCenter(int pageNumber, String input);

}
