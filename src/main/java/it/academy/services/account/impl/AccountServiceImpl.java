package it.academy.services.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.ServiceCenterDAOImpl;
import it.academy.dto.TablePage2;
import it.academy.dto.account.*;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import it.academy.exceptions.account.EmailAlreadyRegistered;
import it.academy.exceptions.account.EnteredPasswordsNotMatch;
import it.academy.exceptions.account.ValidationException;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.account.AccountService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.AccountConverter;
import it.academy.utils.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static it.academy.utils.EntityValidator.validateEntity;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_FOUND_PATTERN;

@Slf4j
public class AccountServiceImpl implements AccountService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);

    @Override
    public AccountDTO create(AccountDTO accountDTO) {

        try {
            Account account = AccountConverter.convertToEntity(accountDTO);
            checkPassword(accountDTO.getPassword(), accountDTO.getPasswordConfirm());
            validateAccount(account);
            encodePassword(account);

            Supplier<Account> create = () -> {
                checkEmail(ID_FOR_CHECK, account.getEmail());
                long serviceCenterId = accountDTO.getServiceCenterId();
                setServiceCenter(account, serviceCenterId);
                return accountDAO.create(account);
            };

            transactionManger.execute(create);
            log.info(OBJECT_CREATED_PATTERN, account);
        } catch (ValidationException | EmailAlreadyRegistered | EnteredPasswordsNotMatch e) {
            accountDTO.setErrorMessage(e.getMessage());
            return accountDTO;
        }

        return accountDTO;
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {

        try {
            Account account = AccountConverter.convertToEntity(accountDTO);

            if (!StringUtils.isBlank(accountDTO.getPassword())) {
                account.setPassword(accountDTO.getPassword());
                checkPassword(accountDTO.getPassword(), accountDTO.getPasswordConfirm());
                validateAccount(account);
                encodePassword(account);
            }

            Supplier<Account> update = () -> {
                Account temp = accountDAO.find(account.getId());
                if (StringUtils.isBlank(accountDTO.getPassword())) {
                    account.setPassword(temp.getPassword());
                }
                checkEmail(account.getId(), account.getEmail());
                Long serviceCenterId = accountDTO.getServiceCenterId();
                setServiceCenter(account, serviceCenterId);
                return accountDAO.update(account);
            };
            transactionManger.execute(update);
            log.info(OBJECT_CREATED_PATTERN, account);
        } catch (ValidationException | EmailAlreadyRegistered | EnteredPasswordsNotMatch e) {
            accountDTO.setErrorMessage(e.getMessage());
        }

        return accountDTO;
    }

    public Map<Long, String> getServiceCentersForAccountForm() {
        Supplier<List<ServiceCenter>> findAll = serviceCenterDAO::findAll;
        List<ServiceCenter> serviceCenters = transactionManger.execute(findAll);
        return serviceCenters.stream()
                .collect(Collectors.toMap(ServiceCenter::getId, ServiceCenter::getServiceName));
    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> accountDAO.delete(id));
    }

    @Override
    public AccountDTO find(long id) {
        Account result = transactionManger.execute(() -> {
            Account account = accountDAO.find(id);
            if (account == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, Account.class);
                throw new ObjectNotFound();
            }
            return account;
        });
        return AccountConverter.convertToDTO(result);
    }

    @Override
    public TablePage2<AccountDTO> findForPage(int pageNumber) {
        Supplier<TablePage2<AccountDTO>> find = () -> {
            long numberOfEntries = accountDAO.getNumberOfEntries();
            List<Account> accounts = accountDAO.findForPage(pageNumber, LIST_SIZE);
            List<AccountDTO> dtoList = AccountConverter.convertToDTOList(accounts);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    @Override
    public TablePage2<AccountDTO> findForPageByFilter(int pageNumber, String filter, String input) {
        Supplier<TablePage2<AccountDTO>> find = () -> {
            long numberOfEntries = accountDAO.getNumberOfEntriesByFilter(filter, input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Account> accounts = accountDAO.findForPageByAnyMatch(pageNumberForSearch, LIST_SIZE, filter, input);
            List<AccountDTO> dtoList = AccountConverter.convertToDTOList(accounts);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return StringUtils.isBlank(input) ? findForPage(pageNumber) : transactionManger.execute(find);
    }

    @Override
    public TablePage2<AccountDTO> findForPageByServiceCenter(int pageNumber, String input) {
        Supplier<TablePage2<AccountDTO>> find = () -> {
            long numberOfEntries = accountDAO.getNumberOfEntriesByServiceCenter(input);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<Account> accounts = accountDAO.findAccountsByServiceCenter(input, pageNumberForSearch, LIST_SIZE);
            List<AccountDTO> dtoList = AccountConverter.convertToDTOList(accounts);
            return new TablePage2<>(dtoList, numberOfEntries);
        };
        return StringUtils.isBlank(input) ? findForPage(pageNumber) : transactionManger.execute(find);
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
