package it.academy.utils.converters.device;

import it.academy.dto.req.device.DeviceDTOReq;
import it.academy.entities.device.Device;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DeviceConverter {

    public static DeviceDTOReq convertToDTOReq(Device device) {
        return DeviceDTOReq.builder()
                .id(device.getId())
                .model(device.getModel())
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .buyer(device.getBuyer())
                .salesman(device.getSalesman())
                .build();
    }

    public static List<DeviceDTOReq> convertListToDTOReq(List<Device> devices) {
        return devices.stream()
                .map(DeviceConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static Device convertToEntity(DeviceDTOReq req) {
        return Device.builder()
                .id(req.getId())
                .model(req.getModel())
                .serialNumber(req.getSerialNumber())
                .dateOfSale(req.getDateOfSale())
                .buyer(req.getBuyer())
                .salesman(req.getSalesman())
                .build();
    }
}
