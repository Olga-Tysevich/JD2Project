package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.RepairCategoryDTO;
import it.academy.entities.repair.components.RepairCategory;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairCategoryConverter {

    public static RepairCategoryDTO convertToDTO(RepairCategory category) {
        return RepairCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static RepairCategory convertDTOToEntity(RepairCategoryDTO req) {
        return RepairCategory.builder()
                .id(req.getId())
                .name(req.getName())
                .build();
    }

    public static List<RepairCategoryDTO> convertListToDTO(List<RepairCategory> repairCategories) {
        return repairCategories.stream()
                .map(RepairCategoryConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<RepairCategory> convertDTOListToEntityList(List<RepairCategoryDTO> repairCategories) {
        return repairCategories.stream()
                .map(RepairCategoryConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

}
