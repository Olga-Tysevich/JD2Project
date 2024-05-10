package it.academy.services.account;

import it.academy.dto.TablePage;
import it.academy.dto.account.AccountDTO;
import java.util.Map;

public interface AccountService {

    AccountDTO create(AccountDTO account);

    AccountDTO update(AccountDTO account);

    Map<Long, String> getServiceCentersForAccountForm();

    void delete(long id);

    AccountDTO find(long id);

    TablePage<AccountDTO> findForPage(int pageNumber, Map<String, String> userInput);

}
