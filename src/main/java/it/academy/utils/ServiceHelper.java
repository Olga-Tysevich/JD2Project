package it.academy.utils;

import it.academy.dao.DAO;
import it.academy.dto.ListForPage;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.utils.constants.LoggerConstants;
import it.academy.utils.converters.EntityConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;

@Slf4j
public class ServiceHelper<T, R> {
    private DAO<T, Long> dao;
    private Class<T> entityClass;
    private EntityConverter<R, T> converter;
    private TransactionManger transactionManger;

    public ServiceHelper(DAO<T, Long> dao, Class<T> entityClass,
                         EntityConverter<R, T> converter, TransactionManger transactionManger) {
        this.dao = dao;
        this.entityClass = entityClass;
        this.converter = converter;
        this.transactionManger = transactionManger;
    }

    public T create(R dto, Supplier<Boolean> methodForCheck) {
        T entity = converter.convertToEntity(dto);
        transactionManger.execute(() -> {
            checkIfExist(entity, methodForCheck);
            return dao.create(entity);
        });
        log.info(LoggerConstants.OBJECT_CREATED_PATTERN, dto);
        return entity;
    }

    public T update(R dto, Supplier<Boolean> methodForCheck) {
        T entity = converter.convertToEntity(dto);
        transactionManger.execute(() -> {
            checkIfExist(entity, methodForCheck);
            return dao.update(entity);
        });
        log.info(LoggerConstants.OBJECT_UPDATED_PATTERN, dto);
        return entity;
    }

    public void delete(Long id) {
        transactionManger.execute(() -> {
            T entity = dao.find(id);
            if (entity == null) {
                throw new ObjectNotFound();
            }
            dao.delete(id);
            return entity;
        });
    }

    private void checkIfExist(T entity, Supplier<Boolean> methodForCheck) {
        boolean isExist = methodForCheck.get();
        if (isExist) {
            log.warn(OBJECT_ALREADY_EXIST, entity);
            throw new ObjectAlreadyExist();
        }
    }

    public R find(Long id) {
        return transactionManger.execute(() -> {
            T entity = dao.find(id);
            if (entity == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, entityClass);
                throw new ObjectNotFound();
            }
            return converter.convertToDTO(entity);
        });
    }

    public List<R> findAll() {
        return transactionManger.execute(() -> {
            List<T> result = dao.findAll();
            if (result.isEmpty()) {
                log.warn(OBJECTS_NOT_FOUND_PATTERN, entityClass);
                throw new ObjectNotFound(OBJECTS_NOT_FOUND_MESSAGE);
            }
            return converter.convertToDTOList(result);
        });
    }


    public ListForPage<R> find(int pageNumber, String filter, String input) {
        return transactionManger.execute(() -> {

            if (filter == null || input == null || filter.isBlank() || input.isBlank()) {
                long numberOfEntries = dao.getNumberOfEntries();
                int maxPageNumber = countMaxPageNumber(numberOfEntries);
                return find(() -> dao.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
            } else {
                long numberOfEntries = dao.getNumberOfEntriesByFilter(filter, input);
                int maxPageNumber = countMaxPageNumber(numberOfEntries);
                return find(() -> dao.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
            }
        });
    }

    private ListForPage<R> find(Supplier<List<T>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<T> result = getList(methodForSearch, entityClass);
        List<EntityFilter> filters = FilterManager.getFilters(entityClass);
        List<R> listDTO = converter.convertToDTOList(result);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

    public List<T> getList(Supplier<List<T>> methodForSearch,
                            Class<T> objectClass) {
        try {
            List<T> list = methodForSearch.get();
            log.info(OBJECTS_FOUND_PATTERN, list.size(), objectClass);
            return list;
        } catch (Exception e) {
            log.error(OBJECTS_NOT_FOUND_PATTERN, objectClass);
            throw new ObjectNotFound(OBJECTS_NOT_FOUND_MESSAGE);
        }
    }

    private int countMaxPageNumber(long listSize) {
        return (int) Math.ceil(((double) listSize) / LIST_SIZE);
    }

}
