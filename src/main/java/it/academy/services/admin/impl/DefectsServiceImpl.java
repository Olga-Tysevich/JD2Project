package it.academy.services.admin.impl;

import it.academy.dao.device.DefectDAO;
import it.academy.dao.device.impl.DefectDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.DefectDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.components.Defect;
import it.academy.services.device.DefectService;
import it.academy.utils.ExceptionManager;
import it.academy.utils.MessageManager;
import it.academy.utils.converters.device.DefectConverter;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class DefectsServiceImpl implements DefectService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private DefectDAO defectDAO = new DefectDAOImpl();

    @Override
    public RespDTO<DefectDTOReq> addDefect(DefectDTOReq req) {
        Defect defect = ExceptionManager.tryExecute(() -> DefectConverter.convertDTOReqToEntity(req));
        Supplier<Defect> save = () -> defectDAO.create(defect);
        RespDTO<DefectDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> DefectConverter.convertToDTOReq(transactionManger.execute(save)));

        if (defect != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, defect));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<DefectDTOReq> changeDefect(DefectDTOReq req) {
        Defect defect = ExceptionManager.tryExecute(() -> DefectConverter.convertDTOReqToEntity(req));
        Supplier<Defect> save = () -> defectDAO.update(defect);
        RespDTO<DefectDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> DefectConverter.convertToDTOReq(transactionManger.execute(save)));

        if (defect != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, defect));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DefectDTOReq> findDefect() {
        List<Defect> result = transactionManger.execute(() -> defectDAO.findAll());

        RespListDTO<DefectDTOReq> resp = ExceptionManager.getListSearchResult(() -> DefectConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DefectDTOReq> findDefect(int pageNumber, int listSize) {
        List<Defect> result = transactionManger.execute(() -> defectDAO.findForPage(pageNumber, listSize));

        RespListDTO<DefectDTOReq> resp = ExceptionManager.getListSearchResult(() -> DefectConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DefectDTOReq> findDefect(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Defect> result = transactionManger.execute(() ->
                defectDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<DefectDTOReq> resp = ExceptionManager.getListSearchResult(() -> DefectConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

}
