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
import it.academy.services.account.ServiceCenterService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.impl.ServiceCenterConverter;
import it.academy.utils.dao.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.constants.Constants.*;
import static it.academy.utils.constants.LoggerConstants.*;
import static it.academy.utils.constants.LoggerConstants.OBJECT_UPDATED_PATTERN;

@Slf4j
public class ServiceCenterServiceImpl implements ServiceCenterService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);
    private final ServiceCenterConverter serviceCenterConverter = new ServiceCenterConverter();
    private final ServiceHelper<ServiceCenter, ServiceCenterDTO> serviceHelper =
            new ServiceHelper<>(serviceCenterDAO, ServiceCenter.class, serviceCenterConverter, transactionManger);

    @Override
    public void createServiceCenter(ServiceCenterDTO serviceCenterDTO) {

        ServiceCenter serviceCenter = serviceCenterConverter.convertToEntity(serviceCenterDTO);
        Supplier<ServiceCenter> add = () -> {
            checkServiceCenter(ID_FOR_CHECK, serviceCenter.getServiceName());
            serviceCenter.setIsActive(true);
            log.info(OBJECT_FOR_SAVE_PATTERN, serviceCenter);
            return serviceCenterDAO.create(serviceCenter);
        };
        transactionManger.execute(add);
        log.info(OBJECT_CREATED_PATTERN, serviceCenter);
    }

    @Override
    public void updateServiceCenter(ServiceCenterDTO serviceCenterDTO) {
        ServiceCenter serviceCenter = serviceCenterConverter.convertToEntity(serviceCenterDTO);
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
    public void deleteServiceCenter(long id) {
        serviceHelper.delete(id);
    }

    @Override
    public ServiceCenterDTO findServiceCenter(long id) {
        return serviceHelper.find(id);
    }

    @Override
    public List<ServiceCenterDTO> findServiceCenters() {
        return serviceHelper.findAll();
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(int pageNumber, String filter, String input) {
        return serviceHelper.find(pageNumber, filter, input);
    }

    private void checkServiceCenter(long id, String serviceName) {
        if (serviceCenterDAO.checkIfServiceCenterExist(id, serviceName)) {
            log.warn(OBJECT_ALREADY_EXIST, serviceName);
            throw new ObjectAlreadyExist(SERVICE_CENTER_ALREADY_EXIST);
        }
    }

}
