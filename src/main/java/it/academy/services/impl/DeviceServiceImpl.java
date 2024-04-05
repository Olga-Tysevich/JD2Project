package it.academy.services.impl;

import it.academy.dao.device.DeviceDAO;
import it.academy.dao.device.impl.DeviceDAOImpl;
import it.academy.dto.device.DeviceDTO;
import it.academy.entities.device.Device;
import it.academy.services.DeviceService;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.dao.TransactionManger;

import static it.academy.utils.Constants.SERIAL_NUMBER;

public class DeviceServiceImpl implements DeviceService {
    private TransactionManger transactionManger = TransactionManger.getInstance();
    private DeviceDAO deviceDAO = new DeviceDAOImpl();

    @Override
    public DeviceDTO addDevice(DeviceDTO deviceDTO) {
        Device device = DeviceConverter.convertDTOToEntity(deviceDTO);
        device.setSerialNumber(device.getSerialNumber().toLowerCase());
        Device result = deviceDAO.findByUniqueParameter(SERIAL_NUMBER, deviceDTO.getSerialNumber().toLowerCase());
        if (result != null) {
            result = transactionManger.execute(() -> deviceDAO.create(device));
        }

        transactionManger.closeManager();
        assert result != null;
        return DeviceConverter.convertToDTO(result);
    }
}
