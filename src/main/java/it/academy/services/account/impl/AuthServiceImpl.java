package it.academy.services.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dto.LoginDTO;
import it.academy.dto.account.AccountDTO;
import it.academy.entities.account.Account;
import it.academy.exceptions.auth.IncorrectPassword;
import it.academy.exceptions.auth.InvalidRole;
import it.academy.exceptions.auth.UserIsBlocked;
import it.academy.exceptions.auth.UserNotFound;
import it.academy.services.account.AuthService;
import it.academy.utils.converters.impl.AccountConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class AuthServiceImpl implements AuthService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);
    private final AccountConverter accountConverter = new AccountConverter();

    @Override
    public AccountDTO loginUser(LoginDTO loginDTO) throws UserNotFound, IncorrectPassword, UserIsBlocked, InvalidRole {
        transactionManger.beginTransaction();
        Account account = transactionManger.execute(() -> accountDAO.findByUniqueParameter(EMAIL, loginDTO.getEmail()));

        if (account == null) {
            log.warn(OBJECTS_NOT_FOUND_PATTERN, loginDTO.getEmail());
            throw new UserNotFound();
        }

        log.info(OBJECT_FOUND_PATTERN, account);

        if (!account.getIsActive()) {
            log.warn(USER_IS_BLOCKED_ERROR, account);
            throw new UserIsBlocked();
        }

        if (!BCrypt.checkpw(loginDTO.getPassword(), account.getPassword())) {
            log.warn(WRONG_PASSWORD_ERROR, loginDTO.getPassword(), account.getPassword());
            throw new IncorrectPassword();
        }

        try {
            RoleEnum.valueOf(account.getRole().name());
        } catch (Exception e) {
            log.warn(INVALID_ROLE, account.getRole());
            throw new InvalidRole();
        }

        AccountDTO accountDTO = accountConverter.convertToDTO(account);
        transactionManger.commit();

        return accountDTO;
    }

}
