package it.academy.utils.services.converters.device;

import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.req.repair_workshop.RepairWorkshopDTOReq;
import it.academy.entities.device.components.Brand;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.utils.services.converters.repair_workshop.RepairWorkshopConverter;
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

    public static List<BrandDTOReq> convertListToDTOReq(List<Brand> brands) {
        return brands.stream()
                .map(BrandConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static Brand convertToEntity(BrandDTOReq req) {
        return Brand.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .repairWorkshops(req.getRepairWorkshops().stream()
                        .map(RepairWorkshopConverter::convertToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

}
