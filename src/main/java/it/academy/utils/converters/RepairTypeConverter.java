package it.academy.utils.converters;

import it.academy.dto.resp.RepairTypeDTO;
import it.academy.entities.RepairType;
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

}
