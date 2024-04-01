package it.academy.utils.services.converters.device;

import it.academy.dto.req.device.BrandDTOReq;
import it.academy.entities.device.components.Brand;
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
                .build();
    }

}
