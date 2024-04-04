package it.academy.utils.converters;

import it.academy.dto.BrandDTO;
import it.academy.entities.device.components.Brand;
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
                .build();
    }

    public static Brand convertDTOToEntity(BrandDTO req) {
        return Brand.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
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
