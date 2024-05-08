package it.academy.utils.converters;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.RepairType;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairTypeConverter {

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
                .map(RepairTypeConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
