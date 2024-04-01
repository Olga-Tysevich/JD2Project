package it.academy.services.impl;

import it.academy.dao.repair_workshop.RepairWorkshopDAO;
import it.academy.dao.repair_workshop.impl.RepairWorkshopDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair_workshop.RepairWorkshopDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.services.RepairWorkshopService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.repair_workshop.RepairWorkshopConverter;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class RepairWorkshopServiceImpl implements RepairWorkshopService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private RepairWorkshopDAO repairWorkshopDAO = new RepairWorkshopDAOImpl();


    @Override
    public RespDTO<RepairWorkshopDTOReq> addRepairWorkshop(RepairWorkshopDTOReq req) {
        RepairWorkshop center = ExceptionManager.tryExecute(() -> RepairWorkshopConverter.convertToEntity(req));
        Supplier<RepairWorkshop> save = () -> repairWorkshopDAO.create(center);
        RespDTO<RepairWorkshopDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> RepairWorkshopConverter.convertToDTOReq(transactionManger.execute(save)));

        if (center != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, center.getServiceName()));
        }
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<RepairWorkshopDTOReq> changeRepairWorkshop(RepairWorkshopDTOReq req) {
        RepairWorkshop center = ExceptionManager.tryExecute(() -> RepairWorkshopConverter.convertToEntity(req));
        Supplier<RepairWorkshop> update = () -> repairWorkshopDAO.update(center);
        RespDTO<RepairWorkshopDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> RepairWorkshopConverter.convertToDTOReq(transactionManger.execute(update)));

        if (center != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, center.getServiceName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairWorkshopDTOReq> findRepairWorkshop() {
        List<RepairWorkshop> result = transactionManger.execute(repairWorkshopDAO::findAll);

        RespListDTO<RepairWorkshopDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairWorkshopConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairWorkshopDTOReq> findRepairWorkshop(int pageNumber, int listSize) {
        List<RepairWorkshop> result = transactionManger.execute(() -> repairWorkshopDAO.findForPage(pageNumber, listSize));

        RespListDTO<RepairWorkshopDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairWorkshopConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<RepairWorkshopDTOReq> findRepairWorkshop(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<RepairWorkshop> result = transactionManger.execute(() ->
                repairWorkshopDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<RepairWorkshopDTOReq> resp = ExceptionManager.getListSearchResult(() -> RepairWorkshopConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
