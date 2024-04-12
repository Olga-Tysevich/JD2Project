package it.academy.services.impl;

import it.academy.dao.ServiceCenterDAO;
import it.academy.dao.impl.ServiceCenterDAOImpl;
import it.academy.dto.ListForPage;
import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.services.ServiceCenterService;
import it.academy.utils.Builder;
import it.academy.utils.EntityFilter;
import it.academy.utils.converters.service_center.ServiceCenterConverter;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.fiterForSearch.FilterManager;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.*;

public class ServiceCenterServiceImpl implements ServiceCenterService {
    private TransactionManger transactionManger = new TransactionManger();
    private ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();

    @Override
    public void addServiceCenter(ServiceCenterDTO serviceCenterDTO) {
        ServiceCenter result = ServiceCenterConverter.convertDTOToEntity(serviceCenterDTO);
        transactionManger.execute(() -> serviceCenterDAO.create(result));
    }

    @Override
    public void updateServiceCenter(ServiceCenterDTO serviceCenterDTO) {
        ServiceCenter result = ServiceCenterConverter.convertDTOToEntity(serviceCenterDTO);
        transactionManger.execute(() -> serviceCenterDAO.update(result));
    }

    @Override
    public ServiceCenterDTO findServiceCenter(long id) {
        ServiceCenter result = transactionManger.execute(() -> serviceCenterDAO.find(id));
        return ServiceCenterConverter.convertToDTO(result);
    }

    @Override
    public List<ServiceCenterDTO> findServiceCenter() {
        List<ServiceCenter> repairs = transactionManger.execute(() -> serviceCenterDAO.findAll());
        return ServiceCenterConverter.convertListToDTO(repairs);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenter(int pageNumber) {
        List<EntityFilter> filters = FilterManager.getFiltersForRepairWorkshop();

        Supplier<ListForPage<ServiceCenterDTO>> find = () -> {
            List<ServiceCenter> repairs = serviceCenterDAO.findForPage(pageNumber, LIST_SIZE);
            int maxPageNumber = (int) Math.ceil(((double) serviceCenterDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<ServiceCenterDTO> list = ServiceCenterConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }

    @Override
    public ListForPage<ServiceCenterDTO> findServiceCenter(int pageNumber, String filter, String input) {
        List<EntityFilter> filters = FilterManager.getFiltersForRepairWorkshop();

        Supplier<ListForPage<ServiceCenterDTO>> find = () -> {
            List<ServiceCenter> repairs = serviceCenterDAO.findForPageByAnyMatch(pageNumber, LIST_SIZE, filter, input);
            int maxPageNumber = (int) Math.ceil(((double) serviceCenterDAO.getNumberOfEntries().intValue()) / LIST_SIZE);
            List<ServiceCenterDTO> list = ServiceCenterConverter.convertListToDTO(repairs);
            return Builder.buildListForPage(list, pageNumber, maxPageNumber, filters);
        };

        return transactionManger.execute(find);
    }



}
