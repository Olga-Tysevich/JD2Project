package it.academy.services.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.ServiceCenterDAOImpl;
import it.academy.dto.account.ChangeAccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectCreationFailed;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.account.AccountService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.account.AccountConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.EntityValidator.validateEntity;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOUND_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_UPDATED_PATTERN;

@Slf4j
public class AccountServiceImpl implements AccountService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);

    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) throws EnteredPasswordsNotMatch,
            EmailAlreadyRegistered, ValidationException, ObjectCreationFailed, ObjectNotFound {

        checkPassword(createAccountDTO.getPassword(), createAccountDTO.getConfirmPassword());
        Account account = AccountConverter.convertToEntity(createAccountDTO);
        account.setIsActive(true);
        transactionManger.beginTransaction();

        checkEmail(account);

        long serviceCenterId = createAccountDTO.getServiceCenterId();
        setServiceCenter(account, serviceCenterId);
        log.info(OBJECT_FOR_SAVE_PATTERN, account);

        validateAccount(account);
        encodePassword(account);
        accountDAO.create(account);
        log.info(OBJECT_CREATED_PATTERN, account);

        transactionManger.commit();
    }

    @Override
    public void updateAccount(ChangeAccountDTO changeAccount) throws EmailAlreadyRegistered,
            ObjectNotFound, ValidationException {

        Account account = AccountConverter.convertToEntity(changeAccount);
        transactionManger.beginTransaction();
        Account temp = accountDAO.find(changeAccount.getId());

        if (temp == null) {
            transactionManger.rollback();
            throw new ObjectNotFound(USER_NOT_FOUND);
        }

        if (RoleEnum.ADMIN.equals(changeAccount.getRole())) {
            changeAccount.setServiceCenterId(null);
        }

        checkEmail(account);

        Long serviceCenterId = changeAccount.getServiceCenterId();
        setServiceCenter(account, serviceCenterId);
        log.info(OBJECT_FOR_UPDATE_PATTERN, account);

        if (changeAccount.getPassword() == null || changeAccount.getPassword().isBlank()) {
            account.setPassword(temp.getPassword());
        }

        validateAccount(account);
        accountDAO.update(account);
        log.info(OBJECT_UPDATED_PATTERN, changeAccount);
        transactionManger.commit();

    }

    @Override
    public AccountDTO findAccount(long id) {
        return transactionManger.execute(() -> {
            try {
                Account account = accountDAO.find(id);
                log.info(OBJECT_FOUND_PATTERN, account);
                return AccountConverter.convertToDTO(account);
            } catch (Exception e) {
                log.error(OBJECT_NOT_FOUND_PATTERN, id, Account.class);
                throw new ObjectNotFound(USER_NOT_FOUND);
            }
        });
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(int pageNumber) {
        long numberOfEntries = accountDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> accountDAO.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(int pageNumber, String filter, String input) {
        long numberOfEntries = accountDAO.getNumberOfEntriesByFilter(filter, input);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> accountDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
    }

    private ListForPage<AccountDTO> find(Supplier<List<Account>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<Account> accounts = ServiceHelper.getList(transactionManger, methodForSearch, Account.class);
        List<EntityFilter> filters = FilterManager.getFiltersForAccount();
        List<AccountDTO> listDTO = AccountConverter.convertToDTOList(accounts);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

    private void encodePassword(Account account) {
        String password = account.getPassword();
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        account.setPassword(encodedPassword);
    }

    private void validateAccount(Account account) throws ValidationException {
        String validationResult = validateEntity(account);
        if (!validationResult.isEmpty()) {
            transactionManger.rollback();
            log.warn(VALIDATION_ERROR, validationResult);
            throw new ValidationException(validationResult);
        }
    }

    private void setServiceCenter(Account account, Long serviceCenterId) throws ObjectNotFound {
        if (RoleEnum.ADMIN.equals(account.getRole())) {
            return;
        }

        ServiceCenter serviceCenter = serviceCenterDAO.find(serviceCenterId);
        if (serviceCenter != null) {
            log.info(OBJECT_FOUND_PATTERN, serviceCenter);
            account.setServiceCenter(serviceCenter);
        } else {
            transactionManger.rollback();
            log.warn(OBJECT_NOT_FOUND_PATTERN, serviceCenterId, Account.class);
            throw new ObjectNotFound(SERVICE_CENTER_NOT_FOUND);
        }
    }

    private void checkPassword(String password, String passwordConfirm) throws EnteredPasswordsNotMatch {
        if (!password.equals(passwordConfirm)) {
            transactionManger.rollback();
            throw new EnteredPasswordsNotMatch();
        }
    }

    private void checkEmail(Account account) throws EmailAlreadyRegistered {
        if (accountDAO.checkIfEmailExist(account)) {
            transactionManger.rollback();
            throw new EmailAlreadyRegistered(account.getEmail());
        }
    }

}
