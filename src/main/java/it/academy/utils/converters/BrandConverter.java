package it.academy.utils.converters;

import it.academy.dto.device.BrandDTO;
import it.academy.entities.device.Brand;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BrandConverter {

    public BrandDTO convertToDTO(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .isActive(brand.getIsActive())
                .build();
    }

    public Brand convertToEntity(BrandDTO req) {
        return Brand.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .build();
    }

    public List<BrandDTO> convertToDTOList(List<Brand> brands) {
        return brands.stream()
                .map(BrandConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
