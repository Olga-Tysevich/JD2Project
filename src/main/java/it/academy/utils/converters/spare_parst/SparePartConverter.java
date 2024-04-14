package it.academy.utils.converters.spare_parst;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.req.SparePartDTO;
import it.academy.dto.resp.SparePartForTableDTO;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.utils.converters.device.DeviceTypeConverter;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartConverter {

    public static SparePartForTableDTO convertToDTO(SparePart sparePart, List<DeviceTypeDTO> deviceTypes) {
        List<DeviceType> relatedDeviceTypes = new ArrayList<>(sparePart.getTypeSet());
        List<DeviceTypeDTO> types = DeviceTypeConverter.convertToDTOList(relatedDeviceTypes);

        return SparePartForTableDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .relatedDeviceTypes(types)
                .allDeviceTypes(deviceTypes)
                .isActive(sparePart.getIsActive())
                .build();
    }

    public static SparePart convertDTOToEntity(SparePartDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .build();
    }

    public static List<SparePartForTableDTO> convertToDTOList(List<SparePart> spareParts, List<DeviceTypeDTO> deviceTypes) {
        return spareParts.stream()
                .map(s -> convertToDTO(s, deviceTypes))
                .collect(Collectors.toList());
    }

    public static List<SparePart> convertDTOListToEntityList(List<SparePartDTO> sparePars) {
        return sparePars.stream()
                .map(SparePartConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

    public static SparePartDTO convertToSparePartDTO(SparePartForOrder sparePart) {
        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .build();
    }
}
