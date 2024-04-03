package it.academy.converters;

import it.academy.dto.req.account.AccountDTO;
import it.academy.dto.req.account.AccountDTOReq;
import it.academy.entities.account.Account;

import java.util.List;

public interface AccountConverter<T extends Account, R extends AccountDTOReq> extends Converter<T, R> {

    AccountDTO convertToDTO(T account);

    T convertAccountDTOToEntity(AccountDTO account);

    List<AccountDTO> convertListToDTO(List<T> accounts);

    List<T> convertListAccountDTOToEntity(List<AccountDTO> accounts);

}
