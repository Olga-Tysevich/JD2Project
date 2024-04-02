package it.academy.services.impl;

import it.academy.dao.repair.SparePartsOrderDAO;
import it.academy.dao.repair.impl.SparePartsOrderDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair.SparePartsOrderDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.repair.spare_parts_order.SparePartsOrder;
import it.academy.services.SparePartsOrderService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.repair.SparePartsOrderConverter;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class SparePartsOrderServiceImpl implements SparePartsOrderService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private SparePartsOrderDAO orderDAO = new SparePartsOrderDAOImpl();

    @Override
    public RespDTO<SparePartsOrderDTOReq> addSparePartsOrder(SparePartsOrderDTOReq req) {
        SparePartsOrder order = ExceptionManager.tryExecute(() -> SparePartsOrderConverter.convertToEntity(req));
        Supplier<SparePartsOrder> save = () -> orderDAO.create(order);
        RespDTO<SparePartsOrderDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> SparePartsOrderConverter.convertToDTOReq(transactionManger.execute(save)));

        if (order != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, order));
        }
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<SparePartsOrderDTOReq> changeSparePartsOrder(SparePartsOrderDTOReq req) {
        SparePartsOrder order = ExceptionManager.tryExecute(() -> SparePartsOrderConverter.convertToEntity(req));
        Supplier<SparePartsOrder> update = () -> orderDAO.update(order);
        RespDTO<SparePartsOrderDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> SparePartsOrderConverter.convertToDTOReq(transactionManger.execute(update)));

        if (order != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, order));
        }
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<SparePartsOrderDTOReq> findSparePartsOrder() {
        List<SparePartsOrder> result = transactionManger.execute(orderDAO::findAll);

        RespListDTO<SparePartsOrderDTOReq> resp = ExceptionManager.getListSearchResult(() -> SparePartsOrderConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<SparePartsOrderDTOReq> findSparePartsOrder(int pageNumber, int listSize) {
        List<SparePartsOrder> result = transactionManger.execute(() -> orderDAO.findForPage(pageNumber, listSize));

        RespListDTO<SparePartsOrderDTOReq> resp = ExceptionManager.getListSearchResult(() -> SparePartsOrderConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<SparePartsOrderDTOReq> findSparePartsOrder(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<SparePartsOrder> result = transactionManger.execute(() ->
                orderDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<SparePartsOrderDTOReq> resp = ExceptionManager.getListSearchResult(() -> SparePartsOrderConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
