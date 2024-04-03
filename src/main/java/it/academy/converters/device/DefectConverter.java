package it.academy.converters.device;

import it.academy.converters.Converter;
import it.academy.dto.req.device.DefectDTOReq;
import it.academy.entities.device.components.Defect;
import java.util.List;
import java.util.stream.Collectors;

public class DefectConverter implements Converter<Defect, DefectDTOReq> {
    private DeviceTypeConverter deviceTypeConverter = new DeviceTypeConverter();

    @Override
    public DefectDTOReq convertToDTOReq(Defect defect) {
        return DefectDTOReq.builder()
                .id(defect.getId())
                .description(defect.getDescription())
                .typeSet(defect.getTypeSet().stream()
                        .map(deviceTypeConverter::convertToDTOReq)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Defect convertDTOReqToEntity(DefectDTOReq req) {
        return Defect.builder()
                .id(req.getId())
                .description(req.getDescription())
                .typeSet(req.getTypeSet().stream()
                        .map(deviceTypeConverter::convertDTOReqToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<DefectDTOReq> convertListToDTOReq(List<Defect> devices) {
        return devices.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Defect> convertDTOReqListToEntityList(List<DefectDTOReq> devices) {
        return devices.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
