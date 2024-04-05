package it.academy.utils.converters.device;

import it.academy.dto.device.DeviceDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.Buyer;
import it.academy.entities.device.components.Model;
import it.academy.entities.device.components.Salesman;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DeviceConverter {

    public static DeviceDTO convertToDTO(Device device) {
        Model model = device.getModel();
        Salesman salesman = device.getSalesman();
        Buyer buyer = device.getBuyer();
        return DeviceDTO.builder()
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

    public static Device convertDTOToEntity(DeviceDTO deviceDTO) {
        return Device.builder()
                .id(deviceDTO.getId())
                .model(ModelConverter.convertDTOToEntity(deviceDTO.getModel()))
                .serialNumber(deviceDTO.getSerialNumber())
                .dateOfSale(deviceDTO.getDateOfSale())
                .buyer(Buyer.builder()
                        .name(deviceDTO.getBuyerName())
                        .surname(deviceDTO.getBuyerSurname())
                        .phone(deviceDTO.getBuyerPhone())
                        .build())
                .salesman(Salesman.builder()
                        .name(deviceDTO.getSalesmanName())
                        .phone(deviceDTO.getSalesmanPhone())
                        .build())
                .build();
    }

    public static List<DeviceDTO> convertListToDTOReq(List<Device> devices) {
        return devices.stream()
                .map(DeviceConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Device> convertDTOReqListToEntityList(List<DeviceDTO> devices) {
        return devices.stream()
                .map(DeviceConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }
}
