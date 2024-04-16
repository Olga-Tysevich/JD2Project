package it.academy.utils;

import it.academy.dao.DAO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.exceptions.common.AccessDenied;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.EntityFilter;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static it.academy.utils.Constants.LIST_SIZE;


public class ServiceHelper {

    public static void checkCurrentAccount(AccountDTO account) throws AccessDenied {
        if (account == null || !RoleEnum.ADMIN.equals(account.getRole())) {
            throw new AccessDenied();
        }
    }



    public static <T, R, L, S extends DAO<T, L>> ListForPage<R> getList(S dao, Supplier<List<T>> method, int pageNumber,
                                                                        Function<List<T>, List<R>> converter,
                                                                        Supplier<List<EntityFilter>> methodForGetFilters) {

        List<EntityFilter> filters = methodForGetFilters.get();
        List<T> list = method.get();
        int maxPageNumber = (int) Math.ceil(((double) dao.getNumberOfEntries().intValue()) / LIST_SIZE);
        List<R> listDTO = converter.apply(list);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

}
