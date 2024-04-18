package it.academy.utils;

import it.academy.dao.DAO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.exceptions.common.AccessDenied;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.EntityFilter;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.LIST_SIZE;
import static it.academy.utils.constants.MessageConstants.OBJECTS_FOUND_PATTERN;
import static it.academy.utils.constants.MessageConstants.OBJECTS_NOT_FOUND_PATTERN;

@Slf4j
public class ServiceHelper {

    public static int countMaxPageNumber(long listSize) {
        return (int) Math.ceil(((double) listSize) / LIST_SIZE);
    }

    public static <T> List<T> getList(TransactionManger transactionManger,
                                      Supplier<List<T>> methodForSearch,
                                      Class<T> objectClass) {
        try {
            transactionManger.beginTransaction();
            List<T> list = methodForSearch.get();
            transactionManger.commit();
            log.info(OBJECTS_FOUND_PATTERN, list.size(), objectClass);
            transactionManger.commit();
            return list;
        } catch (Exception e) {
            log.error(OBJECTS_NOT_FOUND_PATTERN, objectClass);
            transactionManger.rollback();
            throw new ObjectNotFound();
        }
    }

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
        int maxPageNumber = (int) Math.ceil(((double) 2) / LIST_SIZE);
        List<R> listDTO = converter.apply(list);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }
}
