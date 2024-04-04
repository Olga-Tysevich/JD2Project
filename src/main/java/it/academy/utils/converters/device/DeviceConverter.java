package it.academy.utils.converters.device;

import it.academy.dto.req.device.DeviceDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.*;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DeviceConverter {
    private final static String MODEL_DESCRIPTION = "%s \n%s %s";

    public static DeviceDTO convertToDTOReq(Device device) {
        return DeviceDTO.builder()
                .id(device.getId())
//                .model(ModelConverter.convertToDTO(device.getModel()))
                .modelDescription(
                        String.format(MODEL_DESCRIPTION,
                                device.getModel().getType().getName(),
                                device.getModel().getBrand().getName(),
                                device.getModel().getName()))
                .modelId(device.getModel().getId())
                .modelName(device.getModel().getName())
                .brandId(device.getModel().getBrand().getId())
                .brandName(device.getModel().getBrand().getName())
                .deviceTypeId(device.getModel().getType().getId())
                .deviceTypeName(device.getModel().getType().getName())
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .buyerName(device.getBuyer().getName())
                .buyerSurname(device.getBuyer().getSurname())
                .buyerPhone(device.getBuyer().getPhone())
                .salesmanName(device.getSalesman().getName())
                .salesmanPhone(device.getSalesman().getPhone())
//                .buyer(device.getBuyer())
//                .salesman(device.getSalesman())
                .build();
    }

    public static Device convertDTOReqToEntity(DeviceDTO req) {
        return Device.builder()
                .id(req.getId())
                .model(Model.builder()
                        .id(req.getModelId())
                        .name(req.getModelName())
                        .brand(Brand.builder()
                                .id(req.getBrandId())
                                .name(req.getBrandName())
                                .build())
                        .type(DeviceType.builder()
                                .id(req.getDeviceTypeId())
                                .name(req.getDeviceTypeName())
                                .build())
                        .build())
                .serialNumber(req.getSerialNumber())
                .dateOfSale(req.getDateOfSale())
                .buyer(Buyer.builder()
                        .name(req.getBuyerName())
                        .surname(req.getBuyerSurname())
                        .phone(req.getBuyerPhone())
                        .build())
                .salesman(Salesman.builder()
                        .name(req.getSalesmanName())
                        .phone(req.getSalesmanPhone())
                        .build())
                .build();
    }

    public static List<DeviceDTO> convertListToDTOReq(List<Device> devices) {
        return devices.stream()
                .map(DeviceConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<Device> convertDTOReqListToEntityList(List<DeviceDTO> devices) {
        return devices.stream()
                .map(DeviceConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
