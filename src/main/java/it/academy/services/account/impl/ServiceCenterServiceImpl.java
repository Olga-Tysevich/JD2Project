package it.academy.services.account.impl;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.ServiceCenterDAO;
import it.academy.dao.account.impl.AccountDAOImpl;
import it.academy.dao.account.impl.ServiceCenterDAOImpl;
import it.academy.dto.account.ServiceCenterDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.account.Account;
import it.academy.entities.account.ServiceCenter;
import it.academy.exceptions.common.ObjectAlreadyExist;
import it.academy.exceptions.common.ObjectNotFound;
import it.academy.services.account.ServiceCenterService;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.account.ServiceCenterConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_CREATED_PATTERN;
import static it.academy.utils.constants.LoggerConstants.OBJECT_UPDATED_PATTERN;

@Slf4j
public class ServiceCenterServiceImpl implements ServiceCenterService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);

    @Override
    public void addServiceCenter(ServiceCenterDTO serviceCenterDTO) {

        ServiceCenter serviceCenter = ServiceCenterConverter.convertToEntity(serviceCenterDTO);
        Supplier<ServiceCenter> add = () -> {
            checkServiceCenter(serviceCenter);
            serviceCenter.setIsActive(true);
            log.info(OBJECT_FOR_SAVE_PATTERN, serviceCenter);
            return serviceCenterDAO.create(serviceCenter);
        };
        transactionManger.execute(add);
        log.info(OBJECT_CREATED_PATTERN, serviceCenter);
    }

    @Override
    public void updateServiceCenter(ServiceCenterDTO serviceCenterDTO) {

        ServiceCenter serviceCenter = ServiceCenterConverter.convertToEntity(serviceCenterDTO);
        Supplier<ServiceCenter> update = () -> {
            checkServiceCenter(serviceCenter);
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
    public ServiceCenterDTO findServiceCenter(long id) {
        ServiceCenter result = transactionManger.execute(() -> serviceCenterDAO.find(id));
        if (result == null) {
            log.warn(OBJECT_NOT_FOUND_PATTERN, id, ServiceCenter.class);
            throw new ObjectNotFound(SERVICE_CENTERS_NOT_FOUND);
        }
        return ServiceCenterConverter.convertToDTO(result);
    }

    @Override
    public List<ServiceCenterDTO> findServiceCenters() {
        List<ServiceCenter> centers = transactionManger.execute(serviceCenterDAO::findAll);
        return ServiceCenterConverter.convertToDTOList(centers);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber) {
        long numberOfEntries = serviceCenterDAO.getNumberOfEntries();
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> serviceCenterDAO.findForPage(pageNumber, LIST_SIZE), pageNumber, maxPageNumber);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber, String filter, String input) {
        long numberOfEntries = serviceCenterDAO.getNumberOfEntriesByFilter(filter, input);
        int maxPageNumber = ServiceHelper.countMaxPageNumber(numberOfEntries);
        return find(() -> serviceCenterDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input), pageNumber, maxPageNumber);
    }

    private ListForPage<ServiceCenterDTO> find(Supplier<List<ServiceCenter>> methodForSearch, int pageNumber, int maxPageNumber) {
        List<ServiceCenter> serviceCenters = ServiceHelper.getList(transactionManger, methodForSearch, ServiceCenter.class);
        List<EntityFilter> filters = FilterManager.getFiltersForServiceCenter();
        List<ServiceCenterDTO> listDTO = ServiceCenterConverter.convertToDTOList(serviceCenters);
        return Builder.buildListForPage(listDTO, pageNumber, maxPageNumber, filters);
    }

    private void checkServiceCenter(ServiceCenter serviceCenter) {
        long id = serviceCenter.getId() != null ? serviceCenter.getId() : 0L;
        String serviceName = serviceCenter.getServiceName();
        if (serviceCenterDAO.checkIfServiceCenterExist(id, serviceName)) {
            transactionManger.rollback();
            log.warn(OBJECT_ALREADY_EXIST, serviceCenter);
            throw new ObjectAlreadyExist(SERVICE_CENTER_ALREADY_EXIST);
        }
    }

}
