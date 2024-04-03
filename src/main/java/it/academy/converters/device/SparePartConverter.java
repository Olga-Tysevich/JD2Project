package it.academy.converters.device;

import it.academy.converters.Converter;
import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.entities.device.components.SparePart;

import java.util.List;
import java.util.stream.Collectors;

public class SparePartConverter implements Converter<SparePart, SparePartDTOReq> {
    private DeviceTypeConverter deviceTypeConverter = new DeviceTypeConverter();

    @Override
    public SparePartDTOReq convertToDTOReq(SparePart sparePart) {
        return SparePartDTOReq.builder()
                .id(sparePart.getId())
                .name(sparePart.getName())
                .typeSet(sparePart.getTypeSet().stream()
                        .map(deviceTypeConverter::convertToDTOReq)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public SparePart convertDTOReqToEntity(SparePartDTOReq req) {
        return SparePart.builder()
                .id(req.getId())
                .name(req.getName())
                .typeSet(req.getTypeSet().stream()
                        .map(deviceTypeConverter::convertDTOReqToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<SparePartDTOReq> convertListToDTOReq(List<SparePart> spareParts) {
        return spareParts.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<SparePart> convertDTOReqListToEntityList(List<SparePartDTOReq> spareParts) {
        return spareParts.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
