package it.academy.services.impl;

import it.academy.dao.AccountDAO;
import it.academy.dao.ServiceCenterDAO;
import it.academy.dao.impl.AccountDAOImpl;
import it.academy.dao.impl.ServiceCenterDAOImpl;
import it.academy.dto.req.ChangeAccountDTO;
import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.Account;
import it.academy.utils.enums.RoleEnum;
import it.academy.entities.ServiceCenter;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.AdminService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.AccountConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;

import static it.academy.utils.Constants.EMAIL;
import static it.academy.utils.Constants.LIST_SIZE;

public class AdminServiceImpl implements AdminService {
    private final TransactionManger transactionManger = TransactionManger.getInstance();
    private final AccountDAO accountDAO = new AccountDAOImpl();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();

    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) throws EnteredPasswordsNotMatch, EmailAlreadyRegistered, AccessDenied {

        ServiceHelper.checkCurrentAccount(createAccountDTO.getCurrentAccount());

        Account account = AccountConverter.convertToEntity(createAccountDTO);

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
            account.setServiceCenter(serviceCenter);
        }

        accountDAO.create(account);

        transactionManger.commit();
    }

    @Override
    public void updateAccount(ChangeAccountDTO account) throws EmailAlreadyRegistered, AccessDenied {

        ServiceHelper.checkCurrentAccount(account.getCurrentAccount());

        Account result = AccountConverter.convertToEntity(account);
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

        ServiceCenter serviceCenter = serviceCenterDAO.find(account.getServiceCenterId());
        result.setServiceCenter(serviceCenter);

        if (account.getPassword() == null || account.getPassword().isBlank()) {
            result.setPassword(temp.getPassword());
        }

        accountDAO.update(result);

        transactionManger.commit();
    }

    @Override
    public AccountDTO findAccount(long id) {
        Account result = transactionManger.execute(() -> accountDAO.find(id));
        return AccountConverter.convertToDTO(result);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber) {
        return findAccounts(accountDTO, pageNumber, null, null);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(AccountDTO accountDTO, int pageNumber, String filter, String input) {

        if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {

            return ServiceHelper.getList(accountDAO,
                    () -> accountDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber,
                    AccountConverter::convertToDTOList,
                    FilterManager::getFiltersForServiceCenter);
        }

        long serviceCenterId = accountDTO.getServiceCenter().getId();

        return ServiceHelper.getList(accountDAO,
                () -> accountDAO.findServiceCenterAccounts(serviceCenterId, pageNumber, LIST_SIZE, filter, input),
                pageNumber,
                AccountConverter::convertToDTOList,
                FilterManager::getFiltersForServiceCenter);
    }

}
