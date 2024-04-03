package it.academy.converters.repair;

import it.academy.converters.Converter;
import it.academy.dto.req.repair.RepairTypeDTOReq;
import it.academy.entities.repair.components.RepairType;
import java.util.List;
import java.util.stream.Collectors;

public class RepairTypeConverter implements Converter<RepairType, RepairTypeDTOReq> {

    @Override
    public RepairTypeDTOReq convertToDTOReq(RepairType type) {
        return RepairTypeDTOReq.builder()
                .id(type.getId())
                .name(type.getName())
                .code(type.getCode())
                .level(type.getLevel())
                .build();
    }

    @Override
    public RepairType convertDTOReqToEntity(RepairTypeDTOReq req) {
        return RepairType.builder()
                .id(req.getId())
                .name(req.getName())
                .code(req.getCode())
                .level(req.getLevel())
                .build();
    }

    @Override
    public List<RepairTypeDTOReq> convertListToDTOReq(List<RepairType> repairTypes) {
        return repairTypes.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairType> convertDTOReqListToEntityList(List<RepairTypeDTOReq> repairTypes) {
        return repairTypes.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
