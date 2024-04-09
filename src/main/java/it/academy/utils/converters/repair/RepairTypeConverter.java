package it.academy.utils.converters.repair;

import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.components.RepairType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairTypeConverter {

    public static RepairTypeDTO convertToDTO(RepairType type) {
        return RepairTypeDTO.builder()
                .id(type.getId())
                .name(type.getName())
                .code(type.getCode())
                .level(type.getLevel())
                .build();
    }

    public static RepairType convertDTOToEntity(RepairTypeDTO req) {
        System.out.println("from conv " + req.getId());
        return RepairType.builder()
                .id(req.getId())
                .name(req.getName())
                .code(req.getCode())
                .level(req.getLevel())
                .build();
    }

    public static List<RepairTypeDTO> convertListToDTO(List<RepairType> repairTypes) {
        return repairTypes.stream()
                .map(RepairTypeConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<RepairType> convertDTOListToEntityList(List<RepairTypeDTO> repairTypes) {
        return repairTypes.stream()
                .map(RepairTypeConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }
}
