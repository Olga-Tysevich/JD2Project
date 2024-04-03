package it.academy.utils.converters.device;

import it.academy.dto.req.device.DefectDTOReq;
import it.academy.dto.req.device.DeviceTypeDTOReq;
import it.academy.dto.req.device.SparePartDTOReq;
import it.academy.entities.device.components.Defect;
import it.academy.entities.device.components.DeviceType;
import it.academy.entities.spare_parts_order.SparePart;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DeviceTypeConverter {

    public static DeviceTypeDTOReq convertToDTOReq(DeviceType deviceType) {
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

    public static DeviceType convertDTOReqToEntity(DeviceTypeDTOReq req) {
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

    public List<DeviceTypeDTOReq> convertListToDTOReq(List<DeviceType> deviceTypes) {
        return deviceTypes.stream()
                .map(DeviceTypeConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<DeviceType> convertDTOReqListToEntityList(List<DeviceTypeDTOReq> deviceTypes) {
        return deviceTypes.stream()
                .map(DeviceTypeConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
