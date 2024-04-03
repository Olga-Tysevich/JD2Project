package it.academy.converters.repair;

import it.academy.converters.Converter;
import it.academy.dto.req.repair.RepairCategoryDTOReq;
import it.academy.entities.repair.components.RepairCategory;

import java.util.List;
import java.util.stream.Collectors;

public class RepairCategoryConverter implements Converter<RepairCategory, RepairCategoryDTOReq> {

    @Override
    public RepairCategoryDTOReq convertToDTOReq(RepairCategory category) {
        return RepairCategoryDTOReq.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public RepairCategory convertDTOReqToEntity(RepairCategoryDTOReq req) {
        return RepairCategory.builder()
                .id(req.getId())
                .name(req.getName())
                .build();
    }

    @Override
    public List<RepairCategoryDTOReq> convertListToDTOReq(List<RepairCategory> repairCategories) {
        return repairCategories.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairCategory> convertDTOReqListToEntityList(List<RepairCategoryDTOReq> repairCategories) {
        return repairCategories.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
