package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.RepairCategoryDTOReq;
import it.academy.entities.repair.components.RepairCategory;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairCategoryConverter {

    public static RepairCategoryDTOReq convertToDTOReq(RepairCategory category) {
        return RepairCategoryDTOReq.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static RepairCategory convertDTOReqToEntity(RepairCategoryDTOReq req) {
        return RepairCategory.builder()
                .id(req.getId())
                .name(req.getName())
                .build();
    }

    public static List<RepairCategoryDTOReq> convertListToDTOReq(List<RepairCategory> repairCategories) {
        return repairCategories.stream()
                .map(RepairCategoryConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<RepairCategory> convertDTOReqListToEntityList(List<RepairCategoryDTOReq> repairCategories) {
        return repairCategories.stream()
                .map(RepairCategoryConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
