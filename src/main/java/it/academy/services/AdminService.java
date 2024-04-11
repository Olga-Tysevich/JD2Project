package it.academy.services;

import it.academy.dto.AccountDTO;
import it.academy.dto.ListForPage;

import java.util.List;

public interface AdminService {

    void addAccount(AccountDTO account);

    void updateAccount(AccountDTO account);

    AccountDTO findAccount(long id);

    List<AccountDTO> findAccount();

    ListForPage<AccountDTO> findAccount(int pageNumber);

    ListForPage<AccountDTO> findAccount(int pageNumber, String filter, String input);


}
