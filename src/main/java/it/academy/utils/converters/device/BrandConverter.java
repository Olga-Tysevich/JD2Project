package it.academy.utils.converters.device;

import it.academy.dto.req.device.BrandDTO;
import it.academy.entities.device.components.Brand;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BrandConverter {

    public static BrandDTO convertToDTO(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .isActive(brand.getIsActive())
                .repairWorkshops(brand.getRepairWorkshops().stream()
                        .map(RepairWorkshopConverter::convertToDTOReq)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static Brand convertDTOToEntity(BrandDTO req) {
        return Brand.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .repairWorkshops(req.getRepairWorkshops().stream()
                        .map(RepairWorkshopConverter::convertDTOReqToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<BrandDTO> convertListToDTO(List<Brand> brands) {
        return brands.stream()
                .map(BrandConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Brand> convertDTOListToEntityList(List<BrandDTO> brands) {
        return brands.stream()
                .map(BrandConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

}
