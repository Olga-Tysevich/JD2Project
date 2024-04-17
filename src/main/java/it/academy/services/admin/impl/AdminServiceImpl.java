package it.academy.services.admin.impl;

import it.academy.dao.AccountDAO;
import it.academy.dao.ServiceCenterDAO;
import it.academy.dao.impl.AccountDAOImpl;
import it.academy.dao.impl.ServiceCenterDAOImpl;
import it.academy.dto.req.ChangeAccountDTO;
import it.academy.dto.req.CreateAccountDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.Account;
import it.academy.entities.ServiceCenter;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectCreationFailed;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.admin.AdminService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.AccountConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.EntityValidator.validateEntity;
import static it.academy.utils.constants.Constants.EMAIL;
import static it.academy.utils.constants.Constants.LIST_SIZE;
import static it.academy.utils.constants.MessageConstants.*;

@Slf4j
public class AdminServiceImpl implements AdminService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);

    @Override
    public void createAccount(CreateAccountDTO createAccountDTO) throws EnteredPasswordsNotMatch,
            EmailAlreadyRegistered, ValidationException, ObjectCreationFailed, ObjectNotFound {

        checkPassword(createAccountDTO.getPassword(), createAccountDTO.getConfirmPassword());
        checkEmail(createAccountDTO.getEmail());
        Account account = AccountConverter.convertToEntity(createAccountDTO);

        transactionManger.beginTransaction();

        long serviceCenterId = createAccountDTO.getServiceCenterId();
        setServiceCenter(account, serviceCenterId);

        validateAccount(account);

        try {
            encodePassword(account);
            accountDAO.create(account);
            log.info(OBJECT_CREATED_PATTERN, account);
        } catch (Exception e) {
            log.error(OBJECT_CREATION_FAILED_PATTERN, account, e.getMessage());
            transactionManger.rollback();
            throw new ObjectCreationFailed();
        }

        transactionManger.commit();
    }

    @Override
    public void updateAccount(ChangeAccountDTO changeAccount) throws EmailAlreadyRegistered,
            ObjectNotFound, ValidationException {

        Account result = AccountConverter.convertToEntity(changeAccount);
        transactionManger.beginTransaction();
        Account temp = accountDAO.find(changeAccount.getId());

        if (temp == null) {
            transactionManger.rollback();
            throw new ObjectNotFound();
        }

        if (accountDAO.checkIfExist(result)) {
            transactionManger.rollback();
            throw new EmailAlreadyRegistered(changeAccount.getEmail());
        }

        long serviceCenterId = changeAccount.getServiceCenterId();
        setServiceCenter(result, serviceCenterId);

        if (changeAccount.getPassword() == null || changeAccount.getPassword().isBlank()) {
            result.setPassword(temp.getPassword());
        }

        validateAccount(result);

        try {
            accountDAO.update(result);
            log.info(OBJECT_UPDATED_PATTERN, changeAccount);
        } catch (Exception e) {
            transactionManger.rollback();
            log.error(OBJECT_UPDATE_FAILED_PATTERN, result, e.getMessage());
            throw e;
        }

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
                throw new ObjectNotFound();
            }
        });
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(int pageNumber) {
        return find(accountDAO::findAll, pageNumber);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(int pageNumber, String filter, String input) {
        return find(() -> accountDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber);
    }

    @Override
    public ListForPage<AccountDTO> findServiceAccounts(long id, int pageNumber) {
        return find(() -> accountDAO.findServiceCenterAccounts(id, pageNumber, LIST_SIZE), pageNumber);
    }

    @Override
    public ListForPage<AccountDTO> findServiceAccounts(long id, int pageNumber, String filter, String input) {
        return find(() -> accountDAO.findServiceCenterAccounts(id, pageNumber, LIST_SIZE, filter, input), pageNumber);
    }

    private ListForPage<AccountDTO> find(Supplier<List<Account>> methodForSearch, int pageNumber) {
        List<Account> accounts = ServiceHelper.getList(transactionManger, methodForSearch, Account.class);
        List<EntityFilter> filters = FilterManager.getFiltersForAccount();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(accounts.size());
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

    private void setServiceCenter(Account account, long serviceCenterId) throws ObjectNotFound {
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
            throw new ObjectNotFound();
        }
    }

    private void checkPassword(String password, String passwordConfirm) throws EnteredPasswordsNotMatch {
        if (!password.equals(passwordConfirm)) {
            transactionManger.rollback();
            throw new EnteredPasswordsNotMatch();
        }
    }

    private void checkEmail(String email) throws EmailAlreadyRegistered {
        if (accountDAO.findByUniqueParameter(EMAIL, email) != null) {
            transactionManger.rollback();
            throw new EmailAlreadyRegistered(email);
        }
    }

}
