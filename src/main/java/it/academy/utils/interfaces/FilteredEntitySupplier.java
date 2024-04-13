package it.academy.utils.interfaces;

import it.academy.dto.account.resp.AccountDTO;

public interface FilteredEntitySupplier<T> {

    T get(AccountDTO accountDTO, int pageNumber, String filter, String input);

}
