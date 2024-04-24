package it.academy.utils.converters.impl;

import it.academy.dto.device.DeviceDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.embeddable.Buyer;
import it.academy.entities.device.Model;
import it.academy.entities.device.embeddable.Salesman;
import it.academy.utils.converters.EntityConverter;
import java.util.List;
import java.util.stream.Collectors;


public class DeviceConverter implements EntityConverter<DeviceDTO, Device> {
    private final ModelConverter modelConverter = new ModelConverter();

    public DeviceDTO convertToDTO(Device device) {
        Model model = device.getModel();
        Salesman salesman = device.getSalesman();
        Buyer buyer = device.getBuyer();
        return DeviceDTO.builder()
                .id(device.getId())
                .model(modelConverter.convertToDTO(model))
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .salesmanName(salesman.getName())
                .salesmanPhone(salesman.getPhone())
                .buyerName(buyer.getName())
                .buyerSurname(buyer.getSurname())
                .buyerPhone(buyer.getPhone())
                .build();
    }

    @Override
    public Device convertToEntity(DeviceDTO deviceDTO) {
        return Device.builder()
                .id(deviceDTO.getId())
                .model(modelConverter.convertToEntity(deviceDTO.getModel()))
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

    @Override
    public List<DeviceDTO> convertToDTOList(List<Device> deviceList) {
        return deviceList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
