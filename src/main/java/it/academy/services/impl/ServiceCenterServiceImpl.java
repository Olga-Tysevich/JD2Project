package it.academy.services.impl;

import it.academy.dao.service_center.ServiceCenterDAO;
import it.academy.dao.service_center.impl.ServiceCenterDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.service_center.ServiceCenterDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.services.ServiceCenterService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.service_center.ServiceCenterConverter;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class ServiceCenterServiceImpl implements ServiceCenterService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private ServiceCenterDAO serviceCenterDAO = new ServiceCenterDAOImpl();


    @Override
    public RespDTO<ServiceCenterDTOReq> addServiceCenter(ServiceCenterDTOReq req) {
        ServiceCenter center = ExceptionManager.tryExecute(() -> ServiceCenterConverter.convertToEntity(req));
        Supplier<ServiceCenter> save = () -> serviceCenterDAO.create(center);
        RespDTO<ServiceCenterDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> ServiceCenterConverter.convertToDTOReq(transactionManger.execute(save)));

        if (center != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, center.getServiceName()));
        }
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<ServiceCenterDTOReq> changeServiceCenter(ServiceCenterDTOReq req) {
        ServiceCenter center = ExceptionManager.tryExecute(() -> ServiceCenterConverter.convertToEntity(req));
        Supplier<ServiceCenter> update = () -> serviceCenterDAO.update(center);
        RespDTO<ServiceCenterDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> ServiceCenterConverter.convertToDTOReq(transactionManger.execute(update)));

        if (center != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, center.getServiceName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ServiceCenterDTOReq> findServiceCenters() {
        List<ServiceCenter> result = transactionManger.execute(serviceCenterDAO::findAll);

        RespListDTO<ServiceCenterDTOReq> resp = ExceptionManager.getListSearchResult(() -> ServiceCenterConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ServiceCenterDTOReq> findServiceCenters(int pageNumber, int listSize) {
        List<ServiceCenter> result = transactionManger.execute(() -> serviceCenterDAO.findForPage(pageNumber, listSize));

        RespListDTO<ServiceCenterDTOReq> resp = ExceptionManager.getListSearchResult(() -> ServiceCenterConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ServiceCenterDTOReq> findServiceCenters(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<ServiceCenter> result = transactionManger.execute(() ->
                serviceCenterDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<ServiceCenterDTOReq> resp = ExceptionManager.getListSearchResult(() -> ServiceCenterConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
