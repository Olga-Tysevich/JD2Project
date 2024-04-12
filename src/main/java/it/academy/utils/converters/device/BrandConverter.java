package it.academy.utils.converters.device;

import it.academy.dto.device.req.BrandDTO;
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

    public static Brand convertToEntity(BrandDTO req) {
        return Brand.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .build();
    }

    public static List<BrandDTO> convertToListDTO(List<Brand> brands) {
        return brands.stream()
                .map(BrandConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Brand> convertToEntityList(List<BrandDTO> brands) {
        return brands.stream()
                .map(BrandConverter::convertToEntity)
                .collect(Collectors.toList());
    }

}
