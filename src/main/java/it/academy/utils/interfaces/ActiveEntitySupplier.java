package it.academy.utils.interfaces;

import it.academy.dto.account.AccountDTO;

public interface ActiveEntitySupplier<T> {

    T get(AccountDTO accountDTO, int pageNumber);

}