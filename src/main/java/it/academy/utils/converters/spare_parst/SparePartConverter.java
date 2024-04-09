package it.academy.utils.converters.spare_parst;

import it.academy.dto.device.DeviceTypeDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import it.academy.utils.converters.device.DeviceTypeConverter;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartConverter {

    public static SparePartDTO convertToDTO(SparePart sparePart) {
        List<DeviceType> deviceTypes = new ArrayList<>(sparePart.getTypeSet());
        List<DeviceTypeDTO> types = DeviceTypeConverter.convertListToDTO(deviceTypes);

        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .relatedDeviceTypes(types)
                .build();
    }

    public static SparePart convertDTOToEntity(SparePartDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .build();
    }

    public static List<SparePartDTO> convertListToDTO(List<SparePart> brands) {
        return brands.stream()
                .map(SparePartConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<SparePart> convertDTOListToEntityList(List<SparePartDTO> brands) {
        return brands.stream()
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
