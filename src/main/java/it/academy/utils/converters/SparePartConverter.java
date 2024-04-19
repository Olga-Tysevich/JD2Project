package it.academy.utils.converters;

import it.academy.dto.req.DeviceTypeDTO;
import it.academy.dto.req.ChangeSparePartDTO;
import it.academy.dto.resp.SparePartDTO;
import it.academy.entities.DeviceType;
import it.academy.entities.SparePart;
import it.academy.utils.SparePartForOrder;
import it.academy.utils.converters.device.DeviceTypeConverter;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartConverter {

    public static SparePartDTO convertToDTO(SparePart sparePart, List<DeviceTypeDTO> deviceTypes) {
        List<DeviceType> relatedDeviceTypes = new ArrayList<>(sparePart.getTypeSet());
        List<DeviceTypeDTO> types = DeviceTypeConverter.convertToDTOList(relatedDeviceTypes);

        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .relatedDeviceTypes(types)
                .allDeviceTypes(deviceTypes)
                .isActive(sparePart.getIsActive())
                .build();
    }

    public static SparePart convertToEntity(ChangeSparePartDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .isActive(partDTO.getIsActive())
                .build();
    }

    public static SparePart convertToEntity(SparePartDTO partDTO) {
        return SparePart.builder()
                .id(partDTO.getId())
                .name(partDTO.getName())
                .isActive(partDTO.getIsActive())
                .build();
    }

    public static List<SparePartDTO> convertToDTOList(List<SparePart> spareParts, List<DeviceTypeDTO> deviceTypes) {
        return spareParts.stream()
                .map(s -> convertToDTO(s, deviceTypes))
                .collect(Collectors.toList());
    }

    public static SparePartDTO convertToSparePartDTO(SparePartForOrder sparePart) {
        return SparePartDTO.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .isActive(true)
                .build();
    }
}
