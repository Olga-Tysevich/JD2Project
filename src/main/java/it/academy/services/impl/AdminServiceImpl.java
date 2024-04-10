package it.academy.services.impl;

import it.academy.dao.AccountDAO;
import it.academy.dao.RepairWorkshopDAO;
import it.academy.dao.impl.AccountDAOImpl;
import it.academy.dao.impl.RepairWorkshopDAOImpl;
import it.academy.dto.AccountDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.Account;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.services.AdminService;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;
import it.academy.utils.converters.AccountConverter;
import it.academy.utils.dao.TransactionManger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class AdminServiceImpl implements AdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public void addAccount(AccountDTO account) {
        Account result = AccountConverter.convertDTOToEntity(account);
        transactionManger.execute(() -> accountDAO.create(result));
    }

    @Override
    public void updateAccount(AccountDTO account) {
        Account result = AccountConverter.convertDTOToEntity(account);
        transactionManger.execute(() -> accountDAO.update(result));
    }

    @Override
    public AccountDTO findAccount(long id) {
        Account result = transactionManger.execute(() -> accountDAO.find(id));
        return AccountConverter.convertToDTO(result);
    }

    @Override
    public List<AccountDTO> findAccount() {
        List<Account> repairs = transactionManger.execute(() -> accountDAO.findAll());
        return AccountConverter.convertListToDTO(repairs);
    }

    @Override
    public ListForPage<AccountDTO> findAccount(int pageNumber) {
        List<EntityFilter> filters = getFiltersForAccount();

        Supplier<ListForPage<AccountDTO>> find = () -> {
            List<Account> accounts = accountDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) accountDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<AccountDTO> list = AccountConverter.convertListToDTO(accounts);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<AccountDTO> findAccount(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = getFiltersForAccount();

        Supplier<ListForPage<AccountDTO>> find = () -> {
            List<Account> accounts = accountDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) accountDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<AccountDTO> list = AccountConverter.convertListToDTO(accounts);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    private List<EntityFilter> getFiltersForAccount() {
        List<EntityFilter> filters = new ArrayList<>();
        filters.add(new EntityFilter(OBJECT_NAME, BRAND_NAME_DESCRIPTION));
        filters.add(new EntityFilter(IS_ACTIVE, IS_BLOCKED));
        return filters;
    }

}
