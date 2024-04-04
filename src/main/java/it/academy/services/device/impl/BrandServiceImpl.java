package it.academy.services.device.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.BrandDTO;
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
    public List<BrandDTO> findBrands() {
        List<Brand> result = transactionManger.execute(brandDAO::findAll);

        List<BrandDTO> resp = BrandConverter.convertListToDTO(result);
        transactionManger.closeManager();
        return resp;
    }




    @Override
    public RespDTO<BrandDTO> addBrand(BrandDTO req) {
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertDTOToEntity(req));
        Supplier<Brand> save = () -> brandDAO.create(brand);
        RespDTO<BrandDTO> resp = ExceptionManager.getObjectSaveResult(() -> BrandConverter.convertToDTO(transactionManger.execute(save)));

        if (brand != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, brand.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<BrandDTO> changeBrand(BrandDTO req) {
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertDTOToEntity(req));
        Supplier<Brand> update = () -> brandDAO.update(brand);
        RespDTO<BrandDTO> resp = ExceptionManager.getObjectUpdateResult(() -> BrandConverter.convertToDTO(transactionManger.execute(update)));

        if (brand != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, brand.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public BrandDTO findBrand(long id) {

        Brand brand = transactionManger.execute(() -> brandDAO.find(id));
        BrandDTO brandDTO = null;

        if (brand != null) {
            brandDTO = BrandConverter.convertToDTO(brand);
        }
        transactionManger.closeManager();

        return brandDTO;
    }

    @Override
    public RespListDTO<BrandDTO> findBrands(int pageNumber, int listSize) {
        List<Brand> result = transactionManger.execute(() -> brandDAO.findForPage(pageNumber, listSize));

        RespListDTO<BrandDTO> resp = ExceptionManager.getListSearchResult(() -> BrandConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<BrandDTO> findBrands(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Brand> result = transactionManger.execute(() ->
                brandDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<BrandDTO> resp = ExceptionManager.getListSearchResult(() -> BrandConverter.convertListToDTO(result));
        transactionManger.closeManager();
        return resp;
    }
}
