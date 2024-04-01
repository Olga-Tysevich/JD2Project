package it.academy.services.impl;

import it.academy.dao.device.BrandDAO;
import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.BrandDAOImpl;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.req.device.DeviceTypeDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.DeviceType;
import it.academy.services.DeviceService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.device.BrandConverter;
import it.academy.utils.services.converters.device.DeviceTypeConverter;
import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class DeviceServiceImpl implements DeviceService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private BrandDAO brandDAO = new BrandDAOImpl();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();


    @Override
    public RespDTO<BrandDTOReq> addBrand(BrandDTOReq req) {
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertToEntity(req));
        Supplier<Brand> save = () -> brandDAO.create(brand);
        RespDTO<BrandDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> BrandConverter.convertToDTOReq(transactionManger.execute(save)));
        assert brand != null;
        resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, brand.getName()));

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<BrandDTOReq> changeBrand(BrandDTOReq req) {
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertToEntity(req));
        Supplier<Brand> update = () -> brandDAO.update(brand);
        RespDTO<BrandDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> BrandConverter.convertToDTOReq(transactionManger.execute(update)));
        assert brand != null;
        resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, brand.getName()));

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


    @Override
    public RespDTO<DeviceTypeDTOReq> addDeviceType(DeviceTypeDTOReq req) {
        DeviceType deviceType = ExceptionManager.tryExecute(() -> DeviceTypeConverter.convertToEntity(req));
        Supplier<DeviceType> save = () -> deviceTypeDAO.create(deviceType);
        RespDTO<DeviceTypeDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> DeviceTypeConverter.convertToDTOReq(transactionManger.execute(save)));
        assert deviceType != null;
        resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, deviceType.getName()));

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<DeviceTypeDTOReq> changeDeviceType(DeviceTypeDTOReq req) {
        DeviceType deviceType = ExceptionManager.tryExecute(() -> DeviceTypeConverter.convertToEntity(req));
        Supplier<DeviceType> update = () -> deviceTypeDAO.update(deviceType);
        RespDTO<DeviceTypeDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> DeviceTypeConverter.convertToDTOReq(transactionManger.execute(update)));
        assert deviceType != null;
        resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, deviceType.getName()));

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DeviceTypeDTOReq> findDeviceType() {
        List<DeviceType> result = transactionManger.execute(deviceTypeDAO::findAll);

        RespListDTO<DeviceTypeDTOReq> resp = ExceptionManager.getListSearchResult(() -> DeviceTypeConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DeviceTypeDTOReq> findDeviceType(int pageNumber, int listSize) {
        List<DeviceType> result = transactionManger.execute(() -> deviceTypeDAO.findForPage(pageNumber, listSize));

        RespListDTO<DeviceTypeDTOReq> resp = ExceptionManager.getListSearchResult(() -> DeviceTypeConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DeviceTypeDTOReq> findDeviceType(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<DeviceType> result = transactionManger.execute(() ->
                deviceTypeDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<DeviceTypeDTOReq> resp = ExceptionManager.getListSearchResult(() -> DeviceTypeConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }
}
