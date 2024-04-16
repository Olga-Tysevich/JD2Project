package it.academy.utils.converters;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.entities.DeviceType;
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

    public static DeviceType convertToEntity(DeviceTypeDTO typeDTO) {
        return DeviceType.builder()
                .id(typeDTO.getId())
                .name(typeDTO.getName())
                .isActive(typeDTO.getIsActive())
                .build();
    }

    public static List<DeviceTypeDTO> convertToDTOList(List<DeviceType> brands) {
        return brands.stream()
                .map(DeviceTypeConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
