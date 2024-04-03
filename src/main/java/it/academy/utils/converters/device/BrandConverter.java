package it.academy.utils.converters.device;

import it.academy.dto.req.device.BrandDTOReq;
import it.academy.entities.device.components.Brand;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BrandConverter {

    public static BrandDTOReq convertToDTOReq(Brand brand) {
        return BrandDTOReq.builder()
                .id(brand.getId())
                .name(brand.getName())
                .isActive(brand.getIsActive())
                .repairWorkshops(brand.getRepairWorkshops().stream()
                        .map(RepairWorkshopConverter::convertToDTOReq)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static Brand convertDTOReqToEntity(BrandDTOReq req) {
        return Brand.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .repairWorkshops(req.getRepairWorkshops().stream()
                        .map(RepairWorkshopConverter::convertDTOReqToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<BrandDTOReq> convertListToDTOReq(List<Brand> brands) {
        return brands.stream()
                .map(BrandConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<Brand> convertDTOReqListToEntityList(List<BrandDTOReq> brands) {
        return brands.stream()
                .map(BrandConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
