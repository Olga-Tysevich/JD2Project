package it.academy.services.device.impl;

import it.academy.dao.device.DeviceTypeDAO;
import it.academy.dao.device.impl.DeviceTypeDAOImpl;
import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.ListForPage;
import it.academy.entities.device.DeviceType;
import it.academy.services.device.DeviceTypeService;
import it.academy.utils.ServiceHelper;
import it.academy.utils.converters.impl.DeviceTypeConverter;
import it.academy.utils.dao.TransactionManger;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import static it.academy.utils.constants.Constants.*;

@Slf4j
public class DeviceTypeServiceImpl implements DeviceTypeService {
    private final TransactionManger transactionManger = new TransactionManger();
    private final DeviceTypeDAO deviceTypeDAO = new DeviceTypeDAOImpl(transactionManger);
    private final DeviceTypeConverter deviceTypeConverter = new DeviceTypeConverter();
    private final ServiceHelper<DeviceType, DeviceTypeDTO> deviceTypeHelper =
            new ServiceHelper<>(deviceTypeDAO, DeviceType.class, deviceTypeConverter, transactionManger);

    @Override
    public void createDeviceType(DeviceTypeDTO deviceTypeDTO) {
        deviceTypeHelper.create(deviceTypeDTO, () ->
                deviceTypeDAO.checkIfExist(ID_FOR_CHECK, deviceTypeDTO.getName()));
    }

    @Override
    public void updateDeviceType(DeviceTypeDTO deviceTypeDTO) {
        deviceTypeHelper.update(deviceTypeDTO, () ->
                deviceTypeDAO.checkIfExist(deviceTypeDTO.getId(), deviceTypeDTO.getName()));
    }

    @Override
    public void deleteDeviceType(long id) {
        deviceTypeHelper.delete(id);
    }

    @Override
    public DeviceTypeDTO findDeviceType(long id) {
        return deviceTypeHelper.find(id);
    }

    @Override
    public List<DeviceTypeDTO> findDeviceTypes() {
        return deviceTypeHelper.findAll();
    }

   @Override
    public ListForPage<DeviceTypeDTO> findDeviceTypes(int pageNumber, String filter, String input) {
       return deviceTypeHelper.find(pageNumber, filter, input);
    }

}
