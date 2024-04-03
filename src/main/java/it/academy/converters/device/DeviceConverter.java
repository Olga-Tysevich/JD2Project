package it.academy.converters.device;

import it.academy.converters.Converter;
import it.academy.dto.req.device.DeviceDTOReq;
import it.academy.entities.device.Device;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceConverter implements Converter<Device, DeviceDTOReq> {

    @Override
    public DeviceDTOReq convertToDTOReq(Device device) {
        return DeviceDTOReq.builder()
                .id(device.getId())
                .model(device.getModel())
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .buyer(device.getBuyer())
                .salesman(device.getSalesman())
                .build();
    }

    @Override
    public Device convertDTOReqToEntity(DeviceDTOReq req) {
        return Device.builder()
                .id(req.getId())
                .model(req.getModel())
                .serialNumber(req.getSerialNumber())
                .dateOfSale(req.getDateOfSale())
                .buyer(req.getBuyer())
                .salesman(req.getSalesman())
                .build();
    }

    @Override
    public List<DeviceDTOReq> convertListToDTOReq(List<Device> devices) {
        return devices.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Device> convertDTOReqListToEntityList(List<DeviceDTOReq> devices) {
        return devices.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
