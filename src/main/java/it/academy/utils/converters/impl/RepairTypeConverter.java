package it.academy.utils.converters.impl;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.RepairType;
import it.academy.utils.converters.EntityConverter;
import java.util.List;
import java.util.stream.Collectors;

public class RepairTypeConverter implements EntityConverter<RepairTypeDTO, RepairType> {

    public RepairTypeDTO convertToDTO(RepairType repairType) {
        return RepairTypeDTO.builder()
                .id(repairType.getId())
                .name(repairType.getName())
                .code(repairType.getCode())
                .level(repairType.getLevel())
                .isActive(repairType.getIsActive())
                .build();
    }

    public RepairType convertToEntity(RepairTypeDTO repairTypeDTO) {
        return RepairType.builder()
                .id(repairTypeDTO.getId())
                .name(repairTypeDTO.getName())
                .code(repairTypeDTO.getCode())
                .level(repairTypeDTO.getLevel())
                .isActive(repairTypeDTO.getIsActive())
                .build();
    }

    public List<RepairTypeDTO> convertToDTOList(List<RepairType> repairTypes) {
        return repairTypes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
