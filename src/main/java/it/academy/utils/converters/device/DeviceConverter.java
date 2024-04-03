package it.academy.utils.converters.device;

import it.academy.dto.req.device.DeviceDTOReq;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Model;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DeviceConverter {
    private final static String MODEL_DESCRIPTION = "%s \n%s %s";

    public static DeviceDTOReq convertToDTOReq(Device device) {
        return DeviceDTOReq.builder()
                .id(device.getId())
                .model(ModelConverter.convertToDTOReq(device.getModel()))
                .modelDescription(
                        String.format(MODEL_DESCRIPTION,
                                device.getModel().getType(),
                                device.getModel().getBrand(),
                                device.getModel().getName()))
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .buyer(device.getBuyer())
                .salesman(device.getSalesman())
                .build();
    }

    public static Device convertDTOReqToEntity(DeviceDTOReq req) {
        return Device.builder()
                .id(req.getId())
                .model(Model.builder()
                        .id(req.getModelId())
                        .build())
                .serialNumber(req.getSerialNumber())
                .dateOfSale(req.getDateOfSale())
                .buyer(req.getBuyer())
                .salesman(req.getSalesman())
                .build();
    }

    public static List<DeviceDTOReq> convertListToDTOReq(List<Device> devices) {
        return devices.stream()
                .map(DeviceConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<Device> convertDTOReqListToEntityList(List<DeviceDTOReq> devices) {
        return devices.stream()
                .map(DeviceConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
