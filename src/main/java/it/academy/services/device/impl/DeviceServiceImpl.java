package it.academy.services.device.impl;

import it.academy.dao.device.*;
import it.academy.dao.device.impl.*;
import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.*;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Defect;
import it.academy.entities.device.components.DeviceType;
import it.academy.services.device.DeviceService;
import it.academy.utils.MessageManager;
import it.academy.utils.dao.ParameterContainer;
import it.academy.utils.dao.ParameterManager;
import it.academy.utils.dao.TransactionManger;
import it.academy.utils.ExceptionManager;
import it.academy.utils.converters.device.*;
import java.util.List;
import java.util.function.Supplier;
import static it.academy.utils.Constants.SAVED_SUCCESSFULLY;
import static it.academy.utils.Constants.UPDATED_SUCCESSFULLY;

public class DeviceServiceImpl implements DeviceService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl();
    private DeviceDAO deviceDAO = new DeviceDAOImpl();



    @Override
    public RespDTO<DeviceTypeDTOReq> addDeviceType(DeviceTypeDTOReq req) {
        DeviceType deviceType = ExceptionManager.tryExecute(() -> DeviceTypeConverter.convertDTOReqToEntity(req));
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
        DeviceType deviceType = ExceptionManager.tryExecute(() -> DeviceTypeConverter.convertDTOReqToEntity(req));
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
    public RespDTO<DeviceDTOReq> addDevice(DeviceDTOReq req) {
        Device device = ExceptionManager.tryExecute(() -> DeviceConverter.convertDTOReqToEntity(req));
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
        Device device = ExceptionManager.tryExecute(() -> DeviceConverter.convertDTOReqToEntity(req));
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

}
