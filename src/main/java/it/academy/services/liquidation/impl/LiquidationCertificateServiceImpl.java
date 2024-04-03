package it.academy.services.liquidation.impl;

import it.academy.dao.liqidation.LiquidationCauseDAO;
import it.academy.dao.liqidation.LiquidationCertificateDAO;
import it.academy.dao.liqidation.impl.LiquidationCauseDAOImpl;
import it.academy.dao.liqidation.impl.LiquidationCertificateDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.repair.LiquidationCauseDTOReq;
import it.academy.dto.req.repair.LiquidationCertificateDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.liquidation.LiquidationCause;
import it.academy.entities.liquidation.LiquidationCertificate;
import it.academy.services.liquidation.LiquidationCertificateService;
import it.academy.utils.ExceptionManager;
import it.academy.utils.MessageManager;
import it.academy.utils.converters.repair.LiquidationCauseConverter;
import it.academy.utils.converters.repair.LiquidationCertificateConverter;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class LiquidationCertificateServiceImpl implements LiquidationCertificateService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private LiquidationCauseDAO liquidationCauseDAO = new LiquidationCauseDAOImpl();
    private LiquidationCertificateDAO liquidationCertificateDAO = new LiquidationCertificateDAOImpl();

    @Override
    public RespDTO<LiquidationCauseDTOReq> addLiquidationCause(LiquidationCauseDTOReq req) {
        LiquidationCause cause = ExceptionManager.tryExecute(() -> LiquidationCauseConverter.convertDTOReqToEntity(req));
        Supplier<LiquidationCause> save = () -> liquidationCauseDAO.create(cause);
        RespDTO<LiquidationCauseDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> LiquidationCauseConverter.convertToDTOReq(transactionManger.execute(save)));

        if (cause != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, cause));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<LiquidationCauseDTOReq> changeLiquidationCause(LiquidationCauseDTOReq req) {
        LiquidationCause cause = ExceptionManager.tryExecute(() -> LiquidationCauseConverter.convertDTOReqToEntity(req));
        Supplier<LiquidationCause> update = () -> liquidationCauseDAO.update(cause);
        RespDTO<LiquidationCauseDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> LiquidationCauseConverter.convertToDTOReq(transactionManger.execute(update)));

        if (cause != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, cause));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<LiquidationCauseDTOReq> findLiquidationCause() {
        List<LiquidationCause> result = transactionManger.execute(liquidationCauseDAO::findAll);

        RespListDTO<LiquidationCauseDTOReq> resp = ExceptionManager.getListSearchResult(() -> LiquidationCauseConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<LiquidationCauseDTOReq> findLiquidationCause(int pageNumber, int listSize) {
        List<LiquidationCause> result = transactionManger.execute(() -> liquidationCauseDAO.findForPage(pageNumber, listSize));

        RespListDTO<LiquidationCauseDTOReq> resp = ExceptionManager.getListSearchResult(() -> LiquidationCauseConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<LiquidationCauseDTOReq> findLiquidationCause(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<LiquidationCause> result = transactionManger.execute(() ->
                liquidationCauseDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<LiquidationCauseDTOReq> resp = ExceptionManager.getListSearchResult(() -> LiquidationCauseConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<LiquidationCertificateDTOReq> addLiquidationCertificate(LiquidationCertificateDTOReq req) {
        LiquidationCertificate certificate = ExceptionManager.tryExecute(() -> LiquidationCertificateConverter.convertDTOReqToEntity(req));
        Supplier<LiquidationCertificate> save = () -> liquidationCertificateDAO.create(certificate);
        RespDTO<LiquidationCertificateDTOReq> resp = ExceptionManager.getObjectSaveResult(() ->
                LiquidationCertificateConverter.convertToDTOReq(transactionManger.execute(save)));

        if (certificate != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, certificate));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<LiquidationCertificateDTOReq> changeLiquidationCertificate(LiquidationCertificateDTOReq req) {
        LiquidationCertificate certificate = ExceptionManager.tryExecute(() -> LiquidationCertificateConverter.convertDTOReqToEntity(req));
        Supplier<LiquidationCertificate> update = () -> liquidationCertificateDAO.update(certificate);
        RespDTO<LiquidationCertificateDTOReq> resp = ExceptionManager.getObjectSaveResult(() ->
                LiquidationCertificateConverter.convertToDTOReq(transactionManger.execute(update)));

        if (certificate != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, certificate));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<LiquidationCertificateDTOReq> findLiquidationCertificate() {
        List<LiquidationCertificate> result = transactionManger.execute(liquidationCertificateDAO::findAll);

        RespListDTO<LiquidationCertificateDTOReq> resp = ExceptionManager.getListSearchResult(() -> LiquidationCertificateConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<LiquidationCertificateDTOReq> findLiquidationCertificate(int pageNumber, int listSize) {
        List<LiquidationCertificate> result = transactionManger.execute(() -> liquidationCertificateDAO.findForPage(pageNumber, listSize));

        RespListDTO<LiquidationCertificateDTOReq> resp = ExceptionManager.getListSearchResult(() -> LiquidationCertificateConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<LiquidationCertificateDTOReq> findLiquidationCertificate(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<LiquidationCertificate> result = transactionManger.execute(() ->
                liquidationCertificateDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<LiquidationCertificateDTOReq> resp = ExceptionManager.getListSearchResult(() -> LiquidationCertificateConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
