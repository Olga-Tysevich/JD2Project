package it.academy.services.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.ServiceCenterDAOImpl;
import it.academy.dto.account.AccountFormDTO;
import it.academy.dto.account.ChangeAccountDTO;
import it.academy.dto.account.CreateAccountDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.account.AccountService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.impl.AccountConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.interfaces.ThrowingSupplier;
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
    private final AccountConverter accountConverter = new AccountConverter();
    private final ServiceHelper<Account, AccountDTO> accountHelper =
            new ServiceHelper<>(accountDAO, Account.class, accountConverter, transactionManger);

    @Override
    public AccountFormDTO createAccount(CreateAccountDTO createAccountDTO) throws Exception {

        try {
            checkPassword(createAccountDTO.getPassword(), createAccountDTO.getConfirmPassword());
            Account account = accountConverter.convertToEntity(createAccountDTO);
            validateAccount(account);
            encodePassword(account);

            Supplier<Account> create = () -> {
                checkEmail(ID_FOR_CHECK, account.getEmail());
                long serviceCenterId = createAccountDTO.getServiceCenterId();
                setServiceCenter(account, serviceCenterId);
                log.info(OBJECT_FOR_SAVE_PATTERN, account);
                return accountDAO.create(account);
            };

            transactionManger.execute(create);
        } catch (ValidationException | EmailAlreadyRegistered | EnteredPasswordsNotMatch e) {

        }

        log.info(OBJECT_CREATED_PATTERN, account);
        return new AccountFormDTO(createAccountDTO);
    }

    @Override
    public void updateAccount(ChangeAccountDTO changeAccount) throws Exception {

        Account account = accountConverter.convertToEntity(changeAccount);
        ThrowingSupplier<Account> update = () -> {
            Account temp = accountDAO.find(changeAccount.getId());
            if (temp == null) {
                throw new ObjectNotFound(USER_NOT_FOUND);
            }

            checkEmail(account.getId(), account.getEmail());

            Long serviceCenterId = changeAccount.getServiceCenterId();
            setServiceCenter(account, serviceCenterId);
            log.info(OBJECT_FOR_UPDATE_PATTERN, account);

            if (changeAccount.getPassword() == null || changeAccount.getPassword().isBlank()) {
                account.setPassword(temp.getPassword());
            }
            return accountDAO.update(account);
        };

        transactionManger.execute(update, "");
        validateAccount(account);
        log.info(OBJECT_UPDATED_PATTERN, changeAccount);
    }

    @Override
    public void deleteAccount(long id) {
        try {
            accountHelper.delete(id);
        } catch (Exception e) {
            log.warn(DELETE_FAILED, id, Account.class);
        }
    }

    @Override
    public AccountDTO findAccount(long id) {
        return accountHelper.find(id);
    }

    @Override
    public ListForPage<AccountDTO> findAccounts(int pageNumber, String filter, String input) {
        boolean findByFilters = filter != null && !filter.isBlank() && input != null && !input.isBlank();
        if (findByFilters && SERVICE_CENTER.equals(filter)) {
            transactionManger.beginTransaction();
            long numberOfEntries = accountDAO.getNumberOfEntriesByServiceCenter(input);
            Supplier<List<Account>> find = () -> accountDAO.findAccountsByServiceCenter(input, pageNumber, LIST_SIZE);
            List<Account> accounts = accountHelper.getList(find, Account.class);
            List<AccountDTO> listDTO = accountConverter.convertToDTOList(accounts);
            log.info(OBJECTS_FOUND_PATTERN, accounts.size(), ServiceCenter.class);
            transactionManger.commit();
            return Builder.buildListForPage(listDTO, pageNumber, numberOfEntries, Account.class);
        } else {
            return accountHelper.find(pageNumber, filter, input);
        }
    }

    private void encodePassword(Account account) {
        String password = account.getPassword();
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        account.setPassword(encodedPassword);
    }

    private void validateAccount(Account account) throws ValidationException {
        String validationResult = validateEntity(account);
        if (!validationResult.isEmpty()) {
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
            throw new EnteredPasswordsNotMatch();
        }
    }

    private void checkEmail(long id, String email) throws EmailAlreadyRegistered {
        if (accountDAO.checkIfEmailExist(id, email)) {
            throw new EmailAlreadyRegistered(email);
        }
    }

}
