package it.academy.services.impl;

import it.academy.dao.repair.SparePartDAO;
import it.academy.dao.repair.impl.SparePartDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.components.SparePart;
import it.academy.services.SparePartService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.device.SparePartConverter;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class SparePartServiceImpl implements SparePartService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private SparePartDAO sparePartDAO = new SparePartDAOImpl();

    @Override
    public RespDTO<SparePartDTOReq> addSparePart(SparePartDTOReq req) {
        SparePart sparePart = ExceptionManager.tryExecute(() -> SparePartConverter.convertToEntity(req));
        Supplier<SparePart> save = () -> sparePartDAO.create(sparePart);
        RespDTO<SparePartDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> SparePartConverter.convertToDTOReq(transactionManger.execute(save)));

        if (sparePart != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, sparePart.getName()));
        }
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<SparePartDTOReq> changeSparePart(SparePartDTOReq req) {
        SparePart sparePart = ExceptionManager.tryExecute(() -> SparePartConverter.convertToEntity(req));
        Supplier<SparePart> update = () -> sparePartDAO.update(sparePart);
        RespDTO<SparePartDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> SparePartConverter.convertToDTOReq(transactionManger.execute(update)));

        if (sparePart != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, sparePart.getName()));
        }
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<SparePartDTOReq> findSparePart() {
        List<SparePart> result = transactionManger.execute(sparePartDAO::findAll);

        RespListDTO<SparePartDTOReq> resp = ExceptionManager.getListSearchResult(() -> SparePartConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<SparePartDTOReq> findSparePart(int pageNumber, int listSize) {
        List<SparePart> result = transactionManger.execute(() -> sparePartDAO.findForPage(pageNumber, listSize));

        RespListDTO<SparePartDTOReq> resp = ExceptionManager.getListSearchResult(() -> SparePartConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<SparePartDTOReq> findSparePart(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<SparePart> result = transactionManger.execute(() ->
                sparePartDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<SparePartDTOReq> resp = ExceptionManager.getListSearchResult(() -> SparePartConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

}
