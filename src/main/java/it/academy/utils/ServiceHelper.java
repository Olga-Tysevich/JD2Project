package it.academy.utils;

import it.academy.exceptions.common.ObjectNotFound;
import it.academy.utils.dao.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.constants.Constants.LIST_SIZE;
import static it.academy.utils.constants.Constants.OBJECTS_NOT_FOUND_MESSAGE;
import static it.academy.utils.constants.LoggerConstants.*;

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
            throw new ObjectNotFound(OBJECTS_NOT_FOUND_MESSAGE);
        }
    }

}
