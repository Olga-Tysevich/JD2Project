package it.academy.utils.converters;

import it.academy.dto.resp.DeviceDTOResp;
import it.academy.entities.Device;
import it.academy.entities.Buyer;
import it.academy.entities.Model;
import it.academy.entities.Salesman;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DeviceConverter {

    public static DeviceDTOResp convertToDTO(Device device) {
        Model model = device.getModel();
        Salesman salesman = device.getSalesman();
        Buyer buyer = device.getBuyer();
        return DeviceDTOResp.builder()
                .id(device.getId())
                .model(ModelConverter.convertToDTO(model))
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .salesmanName(salesman.getName())
                .salesmanPhone(salesman.getPhone())
                .buyerName(buyer.getName())
                .buyerSurname(buyer.getSurname())
                .buyerPhone(buyer.getPhone())
                .build();
    }

    public static Device convertDTOToEntity(DeviceDTOResp deviceDTOResp) {
        return Device.builder()
                .id(deviceDTOResp.getId())
                .model(ModelConverter.convertToEntity(deviceDTOResp.getModel()))
                .serialNumber(deviceDTOResp.getSerialNumber())
                .dateOfSale(deviceDTOResp.getDateOfSale())
                .buyer(Buyer.builder()
                        .name(deviceDTOResp.getBuyerName())
                        .surname(deviceDTOResp.getBuyerSurname())
                        .phone(deviceDTOResp.getBuyerPhone())
                        .build())
                .salesman(Salesman.builder()
                        .name(deviceDTOResp.getSalesmanName())
                        .phone(deviceDTOResp.getSalesmanPhone())
                        .build())
                .build();
    }

    public static List<DeviceDTOResp> convertListToDTOReq(List<Device> devices) {
        return devices.stream()
                .map(DeviceConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Device> convertDTOReqListToEntityList(List<DeviceDTOResp> devices) {
        return devices.stream()
                .map(DeviceConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }
}
