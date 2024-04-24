package it.academy.utils.converters.impl;

import it.academy.dto.device.BrandDTO;
import it.academy.entities.device.Brand;
import it.academy.utils.converters.EntityConverter;
import java.util.List;
import java.util.stream.Collectors;

public class BrandConverter implements EntityConverter<BrandDTO, Brand> {

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
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
