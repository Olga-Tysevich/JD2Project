package it.academy.utils.services.converters.device;

import it.academy.dto.req.device.DeviceTypeDTOReq;
import it.academy.entities.device.components.DeviceType;
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
                .build();
    }

    public static List<DeviceTypeDTOReq> convertListToDTOReq(List<DeviceType> deviceTypes) {
        return deviceTypes.stream()
                .map(DeviceTypeConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static DeviceType convertToEntity(DeviceTypeDTOReq req) {
        return DeviceType.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .build();
    }
}
