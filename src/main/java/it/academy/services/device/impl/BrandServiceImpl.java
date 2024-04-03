package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.components.Brand;
import it.academy.services.device.BrandService;
import it.academy.utils.ExceptionManager;
import it.academy.utils.MessageManager;
import it.academy.utils.converters.device.BrandConverter;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class BrandServiceImpl implements BrandService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private BrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public RespDTO<BrandDTOReq> addBrand(BrandDTOReq req) {
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertDTOReqToEntity(req));
        Supplier<Brand> save = () -> brandDAO.create(brand);
        RespDTO<BrandDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> BrandConverter.convertToDTOReq(transactionManger.execute(save)));

        if (brand != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, brand.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<BrandDTOReq> changeBrand(BrandDTOReq req) {
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertDTOReqToEntity(req));
        Supplier<Brand> update = () -> brandDAO.update(brand);
        RespDTO<BrandDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> BrandConverter.convertToDTOReq(transactionManger.execute(update)));

        if (brand != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, brand.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<BrandDTOReq> findBrands() {
        List<Brand> result = transactionManger.execute(brandDAO::findAll);

        RespListDTO<BrandDTOReq> resp = ExceptionManager.getListSearchResult(() -> BrandConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<BrandDTOReq> findBrands(int pageNumber, int listSize) {
        List<Brand> result = transactionManger.execute(() -> brandDAO.findForPage(pageNumber, listSize));

        RespListDTO<BrandDTOReq> resp = ExceptionManager.getListSearchResult(() -> BrandConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<BrandDTOReq> findBrands(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Brand> result = transactionManger.execute(() ->
                brandDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<BrandDTOReq> resp = ExceptionManager.getListSearchResult(() -> BrandConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
