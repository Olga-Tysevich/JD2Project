package it.academy.services.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.ServiceCenterDAOImpl;
import it.academy.dto.TablePage;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.account.ServiceCenterService;
import it.academy.utils.PageCounter;
import it.academy.utils.converters.ServiceCenterConverter;
import it.academy.utils.TransactionManger;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_UPDATED_PATTERN;

@Slf4j
public class ServiceCenterServiceImpl implements ServiceCenterService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);

    @Override
    public void create(ServiceCenterDTO serviceCenterDTO) {
        ServiceCenter serviceCenter = ServiceCenterConverter.convertToEntity(serviceCenterDTO);
        Supplier<ServiceCenter> add = () -> {
            checkServiceCenter(ID_FOR_CHECK, serviceCenter.getServiceName());
            log.info(OBJECT_FOR_SAVE_PATTERN, serviceCenter);
            return serviceCenterDAO.create(serviceCenter);
        };
        transactionManger.execute(add);
        log.info(OBJECT_CREATED_PATTERN, serviceCenter);
    }

    @Override
    public void update(ServiceCenterDTO serviceCenterDTO) {
        ServiceCenter serviceCenter = ServiceCenterConverter.convertToEntity(serviceCenterDTO);
        Supplier<ServiceCenter> update = () -> {
            checkServiceCenter(serviceCenter.getId(), serviceCenter.getServiceName());
            boolean isActive = serviceCenter.getIsActive();
            List<Account> serviceAccounts = accountDAO.findServiceCenterAccounts(serviceCenter.getId());
            serviceAccounts.forEach(a -> {
                a.setIsActive(isActive);
                log.info(OBJECT_FOR_UPDATE_PATTERN, serviceCenter);
                accountDAO.update(a);
            });
            return serviceCenterDAO.update(serviceCenter);
        };
        transactionManger.execute(update);
        log.info(OBJECT_UPDATED_PATTERN, serviceCenter);

    }

    @Override
    public void delete(long id) {
        transactionManger.execute(() -> serviceCenterDAO.delete(id));
    }

    @Override
    public ServiceCenterDTO find(long id) {
        ServiceCenter result = transactionManger.execute(() -> {
            ServiceCenter serviceCenter = serviceCenterDAO.find(id);
            if (serviceCenter == null) {
                log.warn(OBJECT_NOT_FOUND_PATTERN, id, Account.class);
                throw new ObjectNotFound();
            }
            return serviceCenter;
        });
        return ServiceCenterConverter.convertToDTO(result);
    }

    @Override
    public List<ServiceCenterDTO> findAll() {
        List<ServiceCenter> result = transactionManger.execute(serviceCenterDAO::findAll);
        return ServiceCenterConverter.convertToDTOList(result);
    }
//
//    @Override
//    public TablePage<ServiceCenterDTO> findForPage(int pageNumber) {
//        Supplier<TablePage<ServiceCenterDTO>> find = () -> {
//            long numberOfEntries = serviceCenterDAO.getNumberOfEntries();
//            List<ServiceCenter> serviceCenters = serviceCenterDAO.findForPage(pageNumber, LIST_SIZE);
//            List<ServiceCenterDTO> result = ServiceCenterConverter.convertToDTOList(serviceCenters);
//            return new TablePage<>(result, numberOfEntries);
//        };
//        return transactionManger.execute(find);
//    }
//
//    @Override
//    public TablePage<ServiceCenterDTO> findForPageByFilter(int pageNumber, String filter, String input) {
//        Supplier<TablePage<ServiceCenterDTO>> find = () -> {
//            long numberOfEntries = serviceCenterDAO.getNumberOfEntriesByFilter(filter, input);
//            int pageForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
//            List<ServiceCenter> serviceCenters = serviceCenterDAO.findForPageByAnyMatch(pageForSearch, LIST_SIZE, filter, input);
//            List<ServiceCenterDTO> result = ServiceCenterConverter.convertToDTOList(serviceCenters);
//            return new TablePage<>(result, numberOfEntries);
//        };
//        return transactionManger.execute(find);
//    }

    @Override
    public TablePage<ServiceCenterDTO> findForPage(int pageNumber, Map<String, String> userInput) {
        Supplier<TablePage<ServiceCenterDTO>> find = () -> {
            long numberOfEntries = serviceCenterDAO.getNumberOfEntries(userInput);
            int pageNumberForSearch = PageCounter.countPageNumber(pageNumber, numberOfEntries);
            List<ServiceCenter> serviceCenters = serviceCenterDAO.findAllByPageAndFilter(pageNumberForSearch, LIST_SIZE, userInput);
            List<ServiceCenterDTO> dtoList = ServiceCenterConverter.convertToDTOList(serviceCenters);
            return new TablePage<>(dtoList, numberOfEntries);
        };
        return transactionManger.execute(find);
    }

    private void checkServiceCenter(long id, String serviceName) {
        if (serviceCenterDAO.checkIfServiceCenterExist(id, serviceName)) {
            log.warn(OBJECT_ALREADY_EXIST, serviceName);
            throw new ObjectAlreadyExist(SERVICE_CENTER_ALREADY_EXIST);
        }
    }

}
