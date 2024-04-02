package it.academy.utils.converters.device;

import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.entities.device.components.SparePart;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SparePartConverter {

    public static SparePartDTOReq convertToDTOReq(SparePart sparePart) {
        return SparePartDTOReq.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .typeSet(sparePart.getTypeSet().stream()
                        .map(DeviceTypeConverter::convertToDTOReq)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<SparePartDTOReq> convertListToDTOReq(List<SparePart> spareParts) {
        return spareParts.stream()
                .map(SparePartConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static SparePart convertToEntity(SparePartDTOReq req) {
        return SparePart.builder()
                .id(req.getId())
                .name(req.getName())
                .typeSet(req.getTypeSet().stream()
                        .map(DeviceTypeConverter::convertToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
