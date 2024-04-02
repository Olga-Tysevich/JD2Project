package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.RepairTypeDTOReq;
import it.academy.entities.repair.components.RepairType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairTypeConverter {

    public static RepairTypeDTOReq convertToDTOReq(RepairType type) {
        return RepairTypeDTOReq.builder()
                .id(type.getId())
                .name(type.getName())
                .code(type.getCode())
                .level(type.getLevel())
                .build();
    }

    public static List<RepairTypeDTOReq> convertListToDTOReq(List<RepairType> repairTypes) {
        return repairTypes.stream()
                .map(RepairTypeConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static RepairType convertToEntity(RepairTypeDTOReq req) {
        return RepairType.builder()
                .id(req.getId())
                .name(req.getName())
                .code(req.getCode())
                .level(req.getLevel())
                .build();
    }
}
