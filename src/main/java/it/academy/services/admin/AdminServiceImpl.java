package it.academy.services.admin;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.AccountDAOImpl;
import it.academy.dao.service_center.ServiceCenterDAO;
import it.academy.dao.service_center.ServiceCenterDAOImpl;
import it.academy.dto.account.req.ChangeAccountDTO;
import it.academy.dto.account.req.CreateAccountDTO;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.entities.account.Account;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.common.AccessDenied;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.account.AccountConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static it.academy.utils.Constants.EMAIL;
import static it.academy.utils.Constants.LIST_SIZE;

public class AdminServiceImpl implements AdminService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private AccountDAO accountDAO = new AccountDAOImpl();
    private ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();

    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered, AccessDenied {

        ServiceHelper.checkCurrentAccount(createAccountDTO.getCurrentAccount());

        Account account = AccountConverter.convertToEntity(createAccountDTO);
        System.out.println("add adto " + createAccountDTO);
        System.out.println("add a " + account);

        transactionManger.beginTransaction();
        if (!createAccountDTO.getPassword().equals(createAccountDTO.getConfirmPassword())) {
            transactionManger.commit();
            throw new EnteredPasswordsNotMatch();
        }

        if (accountDAO.findByUniqueParameter(EMAIL, account.getEmail()) != null) {
            transactionManger.commit();
            throw new EmailAlreadyRegistered(account.getEmail());
        }

        if (RoleEnum.SERVICE_CENTER.equals(account.getRole())) {
            ServiceCenter serviceCenter = serviceCenterDAO.find(createAccountDTO.getServiceCenterId());
            System.out.println("add servicee center " + serviceCenter);
            account.setServiceCenter(serviceCenter);
        }

        Account result = accountDAO.create(account);

        System.out.println("after save " + result);

        transactionManger.commit();
    }

    @Override
    public void updateAccount(ChangeAccountDTO account) throws EmailAlreadyRegistered, AccessDenied {

        ServiceHelper.checkCurrentAccount(account.getCurrentAccount());

        Account result = AccountConverter.convertToEntity(account);
        System.out.println("update  account" + result);
        transactionManger.beginTransaction();
        Account temp = accountDAO.find(account.getId());

        if (accountDAO.findByUniqueParameter(EMAIL, account.getEmail()) != null
                && !accountDAO.findByUniqueParameter(EMAIL, account.getEmail()).getId().equals(temp.getId())) {
            transactionManger.commit();
            throw new EmailAlreadyRegistered(account.getEmail());
        }

        if (result.getIsActive() == null) {
            result.setIsActive(false);
        }

        System.out.println("update temp a " + temp);
        ServiceCenter serviceCenter = serviceCenterDAO.find(account.getServiceCenterId());
        result.setServiceCenter(serviceCenter);

        if (account.getPassword() == null || account.getPassword().isBlank()) {
            System.out.println("update service center " + serviceCenter);
            result.setPassword(temp.getPassword());

            System.out.println("update after set passw " + temp);
        }

        Account t = accountDAO.update(result);
        System.out.println("update after " + t);

        transactionManger.commit();
    }

    @Override
    public AccountDTO findAccount(long id) {
        Account result = transactionManger.execute(() -> accountDAO.find(id));
        return AccountConverter.convertToDTO(result);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber) {

        if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return getServiceCenterList(() -> accountDAO.findForPage(pageNumber, LIST_SIZE), pageNumber,
                    AccountConverter::convertToDTOList);
        }

        long serviceCenterId = accountDTO.getServiceCenter().getId();

        return getServiceCenterList(() -> accountDAO.findServiceCenterAccounts(serviceCenterId, pageNumber, LIST_SIZE),
                pageNumber, AccountConverter::convertToDTOList);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber, String filter, String input) {

        if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return getServiceCenterList(() -> accountDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber,
                    AccountConverter::convertToDTOList);
        }

        long serviceCenterId = accountDTO.getServiceCenter().getId();

        return getServiceCenterList(() -> accountDAO.findServiceCenterAccounts(serviceCenterId, pageNumber, LIST_SIZE, filter, input),
                pageNumber, AccountConverter::convertToDTOList);
    }

    private ListForPage<AccountDTO> getServiceCenterList(Supplier<List<Account>> method, int pageNumber,
                                                         Function<List<Account>, List<AccountDTO>> converter) {
        List<EntityFilter> filters = FilterManager.getFiltersForServiceCenter();

        Supplier<ListForPage<AccountDTO>> find = () -> {
            List<Account> accounts = method.get();
            int maxPageNumber = (int) Math.ceil(((double) accountDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<AccountDTO> list = converter.apply(accounts);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

}
