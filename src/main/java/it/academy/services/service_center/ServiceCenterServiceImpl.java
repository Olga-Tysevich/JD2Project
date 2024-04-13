package it.academy.services.service_center;

import it.academy.dao.account.AccountDAO;
import it.academy.dao.account.AccountDAOImpl;
import it.academy.dao.service_center.ServiceCenterDAO;
import it.academy.dao.service_center.ServiceCenterDAOImpl;
import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.dto.table.resp.ListForPage;
import it.academy.entities.account.Account;
import it.academy.entities.account.RoleEnum;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.exceptions.common.AccessDenied;
import it.academy.utils.Builder;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.service_center.ServiceCenterConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.EntityFilter;
import it.academy.utils.fiterForSearch.FilterManager;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class ServiceCenterServiceImpl implements ServiceCenterService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public void addServiceCenter(ServiceCenterDTO serviceCenterDTO) throws AccessDenied {

        ServiceHelper.checkCurrentAccount(serviceCenterDTO.getCurrentAccount());

        transactionManger.beginTransaction();
        ServiceCenter temp = serviceCenterDAO.findByUniqueParameter(SERVICE_CENTER_NAME,
                serviceCenterDTO.getServiceName());
        System.out.println(temp);

        if (temp != null) {
            throw new IllegalArgumentException(SERVICE_CENTERS_ALREADY_EXIST);
        }


        ServiceCenter serviceCenter = ServiceCenterConverter.convertToEntity(serviceCenterDTO);
        serviceCenter.setIsActive(true);

        ServiceCenter result = serviceCenterDAO.create(serviceCenter);
        System.out.println("Service center " + result);
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
            List<ServiceCenter> centers = transactionManger.execute(() -> serviceCenterDAO.findAll());
            return ServiceCenterConverter.convertToDTOList(centers);
        }

        long serviceCenterId = accountDTO.getServiceCenter().getId();
        List<ServiceCenter> centers = transactionManger.execute(() -> List.of(serviceCenterDAO.find(serviceCenterId)));
        return ServiceCenterConverter.convertToDTOList(centers);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO, int pageNumber) {

        if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return getServiceCenterList(() -> serviceCenterDAO.findForPage(pageNumber, LIST_SIZE), pageNumber,
                    ServiceCenterConverter::convertToDTOList);
        }

        long serviceCenterId = accountDTO.getServiceCenter().getId();
        return getServiceCenterList(() -> List.of(serviceCenterDAO.find(serviceCenterId)), pageNumber,
                ServiceCenterConverter::convertToDTOList);

    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenters(AccountDTO accountDTO, int pageNumber,
                                                            String filter, String input) {

        if (RoleEnum.ADMIN.equals(accountDTO.getRole())) {
            return getServiceCenterList(() -> serviceCenterDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input),
                    pageNumber, ServiceCenterConverter::convertToDTOList);
        }

        long serviceCenterId = accountDTO.getServiceCenter().getId();
        return getServiceCenterList(() -> List.of(serviceCenterDAO.find(serviceCenterId)), pageNumber,
                ServiceCenterConverter::convertToDTOList);
    }

    private ListForPage<ServiceCenterDTO> getServiceCenterList(Supplier<List<ServiceCenter>> method, int pageNumber,
                                                               Function<List<ServiceCenter>, List<ServiceCenterDTO>> converter) {
        List<EntityFilter> filters = FilterManager.getFiltersForServiceCenter();

        Supplier<ListForPage<ServiceCenterDTO>> find = () -> {
            List<ServiceCenter> centers = method.get();
            int maxPageNumber = (int) Math.ceil(((double) serviceCenterDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<ServiceCenterDTO> list = converter.apply(centers);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

}
