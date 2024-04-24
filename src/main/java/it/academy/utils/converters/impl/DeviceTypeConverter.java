package it.academy.utils.converters.impl;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.entities.device.DeviceType;
import it.academy.utils.converters.EntityConverter;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceTypeConverter implements EntityConverter<DeviceTypeDTO, DeviceType> {

    public DeviceTypeDTO convertToDTO(DeviceType deviceType) {
        return DeviceTypeDTO.builder()
                .id(deviceType.getId())
                .name(deviceType.getName())
                .isActive(deviceType.getIsActive())
                .build();
    }

    public DeviceType convertToEntity(DeviceTypeDTO typeDTO) {
        return DeviceType.builder()
                .id(typeDTO.getId())
                .name(typeDTO.getName())
                .isActive(typeDTO.getIsActive())
                .build();
    }

    public  List<DeviceTypeDTO> convertToDTOList(List<DeviceType> brands) {
        return brands.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
