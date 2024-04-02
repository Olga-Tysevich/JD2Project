package it.academy.services.impl;

import it.academy.dao.device.*;
import it.academy.dao.device.impl.*;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.*;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.Defect;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.device.components.Model;
import it.academy.services.DeviceService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.services.ExceptionManager;
import it.academy.utils.services.converters.device.*;
import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class DeviceServiceImpl implements DeviceService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private BrandDAO brandDAO = new BrandDAOImpl();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private ModelDAO modelDAO = new ModelDAOImpl();
    private DeviceDAO deviceDAO = new DeviceDAOImpl();
    private DefectDAO defectDAO = new DefectDAOImpl();

    @Override
    public RespDTO<BrandDTOReq> addBrand(BrandDTOReq req) {
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertToEntity(req));
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
        Brand brand = ExceptionManager.tryExecute(() -> BrandConverter.convertToEntity(req));
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


    @Override
    public RespDTO<DeviceTypeDTOReq> addDeviceType(DeviceTypeDTOReq req) {
        DeviceType deviceType = ExceptionManager.tryExecute(() -> DeviceTypeConverter.convertToEntity(req));
        Supplier<DeviceType> save = () -> deviceTypeDAO.create(deviceType);
        RespDTO<DeviceTypeDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> DeviceTypeConverter.convertToDTOReq(transactionManger.execute(save)));

        if (deviceType != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, deviceType.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<DeviceTypeDTOReq> changeDeviceType(DeviceTypeDTOReq req) {
        DeviceType deviceType = ExceptionManager.tryExecute(() -> DeviceTypeConverter.convertToEntity(req));
        Supplier<DeviceType> update = () -> deviceTypeDAO.update(deviceType);
        RespDTO<DeviceTypeDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> DeviceTypeConverter.convertToDTOReq(transactionManger.execute(update)));

        if (deviceType != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, deviceType.getName()));
        }

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

    @Override
    public RespDTO<ModelDTOReq> addModel(ModelDTOReq req) {
        Model model = ExceptionManager.tryExecute(() -> ModelConverter.convertToEntity(req));
        Supplier<Model> save = () -> modelDAO.create(model);
        RespDTO<ModelDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> ModelConverter.convertToDTOReq(transactionManger.execute(save)));

        if (model != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, model.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<ModelDTOReq> changeModel(ModelDTOReq req) {
        Model model = ExceptionManager.tryExecute(() -> ModelConverter.convertToEntity(req));
        Supplier<Model> update = () -> modelDAO.update(model);
        RespDTO<ModelDTOReq> resp = ExceptionManager.getObjectUpdateResult(() -> ModelConverter.convertToDTOReq(transactionManger.execute(update)));

        if (model != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, model.getName()));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels() {
        List<Model> result = transactionManger.execute(() -> modelDAO.findAll());

        RespListDTO<ModelDTOReq> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels(int pageNumber, int listSize) {
        List<Model> result = transactionManger.execute(() -> modelDAO.findForPage(pageNumber, listSize));

        RespListDTO<ModelDTOReq> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<ModelDTOReq> findModels(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Model> result = transactionManger.execute(() ->
                modelDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<ModelDTOReq> resp = ExceptionManager.getListSearchResult(() -> ModelConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<DeviceDTOReq> addDevice(DeviceDTOReq req) {
        Device device = ExceptionManager.tryExecute(() -> DeviceConverter.convertToEntity(req));
        Supplier<Device> save = () -> deviceDAO.create(device);
        RespDTO<DeviceDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> DeviceConverter.convertToDTOReq(transactionManger.execute(save)));

        if (device != null) {
            resp.setMessage(MessageManager.getFormattedMessage(SAVED_SUCCESSFULLY, device));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<DeviceDTOReq> changeDevice(DeviceDTOReq req) {
        Device device = ExceptionManager.tryExecute(() -> DeviceConverter.convertToEntity(req));
        Supplier<Device> save = () -> deviceDAO.update(device);
        RespDTO<DeviceDTOReq> resp = ExceptionManager.getObjectSaveResult(() -> DeviceConverter.convertToDTOReq(transactionManger.execute(save)));

        if (device != null) {
            resp.setMessage(MessageManager.getFormattedMessage(UPDATED_SUCCESSFULLY, device));
        }

        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DeviceDTOReq> findDevices() {
        List<Device> result = transactionManger.execute(() -> deviceDAO.findAll());

        RespListDTO<DeviceDTOReq> resp = ExceptionManager.getListSearchResult(() -> DeviceConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DeviceDTOReq> findDevices(int pageNumber, int listSize) {
        List<Device> result = transactionManger.execute(() -> deviceDAO.findForPage(pageNumber, listSize));

        RespListDTO<DeviceDTOReq> resp = ExceptionManager.getListSearchResult(() -> DeviceConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespListDTO<DeviceDTOReq> findDevices(ParametersForSearchDTO parameters) {
        List<ParameterContainer<?>> parametersList = ParameterManager.getQueryParameters(parameters.getFilters(), parameters.getUserInput());
        List<Device> result = transactionManger.execute(() ->
                deviceDAO.findForPageByAnyMatch(parameters.getPageNumber(), parameters.getListSize(), parametersList));

        RespListDTO<DeviceDTOReq> resp = ExceptionManager.getListSearchResult(() -> DeviceConverter.convertListToDTOReq(result));
        transactionManger.closeManager();
        return resp;
    }

    @Override
    public RespDTO<DefectDTOReq> addDefect(DefectDTOReq req) {
        Defect defect = ExceptionManager.tryExecute(() -> DefectConverter.convertToEntity(req));
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
        Defect defect = ExceptionManager.tryExecute(() -> DefectConverter.convertToEntity(req));
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
