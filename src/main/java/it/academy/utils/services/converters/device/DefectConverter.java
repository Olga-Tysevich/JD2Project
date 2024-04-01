package it.academy.utils.services.converters.device;

import it.academy.dto.req.device.DefectDTOReq;
import it.academy.entities.device.components.Defect;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DefectConverter {

    public static DefectDTOReq convertToDTOReq(Defect defect) {
        return DefectDTOReq.builder()
                .id(defect.getId())
                .description(defect.getDescription())
                .typeSet(defect.getTypeSet().stream()
                        .map(DeviceTypeConverter::convertToDTOReq)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<DefectDTOReq> convertListToDTOReq(List<Defect> devices) {
        return devices.stream()
                .map(DefectConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static Defect convertToEntity(DefectDTOReq req) {
        return Defect.builder()
                .id(req.getId())
                .description(req.getDescription())
                .typeSet(req.getTypeSet().stream()
                        .map(DeviceTypeConverter::convertToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
