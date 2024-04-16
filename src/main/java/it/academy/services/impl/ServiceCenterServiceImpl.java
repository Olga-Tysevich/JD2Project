package it.academy.services.impl;

import it.academy.dao.AccountDAO;
import it.academy.dao.ServiceCenterDAO;
import it.academy.dao.impl.AccountDAOImpl;
import it.academy.dao.impl.ServiceCenterDAOImpl;
import it.academy.dto.req.ServiceCenterDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.entities.Account;
import it.academy.entities.ServiceCenter;
import it.academy.exceptions.common.AccessDenied;
import it.academy.services.ServiceCenterService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.ServiceCenterConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.enums.RoleEnum;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class ServiceCenterServiceImpl implements ServiceCenterService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl(transactionManger);
    private final AccountDAO accountDAO = new AccountDAOImpl(transactionManger);

    @Override
    public void addServiceCenter(ServiceCenterDTO serviceCenterDTO) throws AccessDenied {

        ServiceHelper.checkCurrentAccount(serviceCenterDTO.getCurrentAccount());

        transactionManger.beginTransaction();
        ServiceCenter temp = serviceCenterDAO.findByUniqueParameter(SERVICE_CENTER_NAME,
                serviceCenterDTO.getServiceName());

        if (temp != null) {
            throw new IllegalArgumentException(SERVICE_CENTERS_ALREADY_EXIST);
        }

        ServiceCenter serviceCenter = ServiceCenterConverter.convertToEntity(serviceCenterDTO);
        serviceCenter.setIsActive(true);

        serviceCenterDAO.create(serviceCenter);
        transactionManger.commit();
    }

    @Override
    public void updateServiceCenter(ServiceCenterDTO serviceCenterDTO) throws AccessDenied {

        ServiceHelper.checkCurrentAccount(serviceCenterDTO.getCurrentAccount());

        ServiceCenter result = ServiceCenterConverter.convertToEntity(serviceCenterDTO);

        transactionManger.beginTransaction();

        boolean isActive = result.getIsActive();
        List<Account> serviceAccounts = accountDAO.findServiceCenterAccounts(result.getId());
        serviceAccounts.forEach(a -> {
            a.setIsActive(isActive);
            accountDAO.update(a);
        });

        ServiceCenter temp = serviceCenterDAO.findByUniqueParameter(SERVICE_CENTER_NAME,
                serviceCenterDTO.getServiceName());

        if (temp != null && !temp.getId().equals(serviceCenterDTO.getId())) {
            throw new IllegalArgumentException(SERVICE_NAME_ALREADY_TAKEN);
        }

        serviceCenterDAO.update(result);

        transactionManger.commit();
    }

    @Override
    public ServiceCenterDTO findServiceCenter(long id) {
        ServiceCenter result = transactionManger.execute(() -> serviceCenterDAO.find(id));
        return ServiceCenterConverter.convertToDTO(result);
    }

    @Override
    public List<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO) {

        if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            List<ServiceCenter> centers = transactionManger.execute(serviceCenterDAO::findAll);
            return ServiceCenterConverter.convertToDTOList(centers);
        }

        long serviceCenterId = accountDTO.getServiceCenter().getId();
        List<ServiceCenter> centers = transactionManger.execute(() -> List.of(serviceCenterDAO.find(serviceCenterId)));
        return ServiceCenterConverter.convertToDTOList(centers);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO, int pageNumber) {

        return find(accountDTO, pageNumber, null, null);

    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO, int pageNumber,
                                                            String filter, String input) {
        return find(accountDTO, pageNumber, filter, input);
    }

    public ListForPage<ServiceCenterDTO> find(AccountDTO accountDTO, int pageNumber, String filter, String input) {

        Supplier<List<ServiceCenter>> find;
        if (!RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            find = () -> serviceCenterDAO.findActiveObjectsForPage(true, pageNumber, LIST_SIZE, filter, input);
        } else {
            find = () -> serviceCenterDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
        }
        return ServiceHelper.getList(serviceCenterDAO,
                find,
                pageNumber,
                ServiceCenterConverter::convertToDTOList,
                FilterManager::getFiltersForServiceCenter);
    }

}
