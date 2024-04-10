package it.academy.utils.converters.device;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.entities.device.components.DeviceType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DeviceTypeConverter {

    public static DeviceTypeDTO convertToDTO(DeviceType deviceType) {
        return DeviceTypeDTO.builder()
                .id(deviceType.getId())
                .name(deviceType.getName())
                .isActive(deviceType.getIsActive())
                .build();
    }

    public static DeviceType convertDTOToEntity(DeviceTypeDTO typeDTO) {
        return DeviceType.builder()
                .id(typeDTO.getId())
                .name(typeDTO.getName())
                .isActive(typeDTO.getIsActive())
                .build();
    }

    public static List<DeviceTypeDTO> convertListToDTO(List<DeviceType> brands) {
        return brands.stream()
                .map(DeviceTypeConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<DeviceType> convertDTOListToEntityList(List<DeviceTypeDTO> brands) {
        return brands.stream()
                .map(DeviceTypeConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

}
