package it.academy.converters.device;

import it.academy.converters.Converter;
import it.academy.dto.req.device.DefectDTOReq;
import it.academy.dto.req.device.DeviceTypeDTOReq;
import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.entities.device.components.Defect;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.device.components.SparePart;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceTypeConverter implements Converter<DeviceType, DeviceTypeDTOReq> {

    @Override
    public DeviceTypeDTOReq convertToDTOReq(DeviceType deviceType) {
        return DeviceTypeDTOReq.builder()
                .id(deviceType.getId())
                .name(deviceType.getName())
                .isActive(deviceType.getIsActive())
                .spareParts(deviceType.getSpareParts().stream()
                        .map(sp -> SparePartDTOReq.builder()
                                .id(sp.getId())
                                .name(sp.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .defects(deviceType.getDefects().stream()
                        .map(d -> DefectDTOReq.builder()
                                .id(d.getId())
                                .description(d.getDescription())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public DeviceType convertDTOReqToEntity(DeviceTypeDTOReq req) {
        return DeviceType.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .spareParts(req.getSpareParts().stream()
                        .map(sp -> SparePart.builder()
                                .id(sp.getId())
                                .name(sp.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .defects(req.getDefects().stream()
                        .map(d -> Defect.builder()
                                .id(d.getId())
                                .description(d.getDescription())
                                .build()).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<DeviceTypeDTOReq> convertListToDTOReq(List<DeviceType> deviceTypes) {
        return deviceTypes.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeviceType> convertDTOReqListToEntityList(List<DeviceTypeDTOReq> deviceTypes) {
        return deviceTypes.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
