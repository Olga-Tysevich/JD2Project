package it.academy.converters.device;

import it.academy.converters.Converter;
import it.academy.dto.req.device.BrandDTOReq;
import it.academy.entities.device.components.Brand;
import it.academy.converters.repair_workshop.RepairWorkshopConverter;

import java.util.List;
import java.util.stream.Collectors;

public class BrandConverter implements Converter<Brand, BrandDTOReq> {
    private RepairWorkshopConverter repairWorkshopConverter = new RepairWorkshopConverter();

    @Override
    public BrandDTOReq convertToDTOReq(Brand brand) {
        return BrandDTOReq.builder()
                .id(brand.getId())
                .name(brand.getName())
                .isActive(brand.getIsActive())
                .repairWorkshops(brand.getRepairWorkshops().stream()
                        .map(repairWorkshopConverter::convertToDTOReq)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Brand convertDTOReqToEntity(BrandDTOReq req) {
        return Brand.builder()
                .id(req.getId())
                .name(req.getName())
                .isActive(req.getIsActive())
                .repairWorkshops(req.getRepairWorkshops().stream()
                        .map(repairWorkshopConverter::convertDTOReqToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<BrandDTOReq> convertListToDTOReq(List<Brand> brands) {
        return brands.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Brand> convertDTOReqListToEntityList(List<BrandDTOReq> brands) {
        return brands.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
