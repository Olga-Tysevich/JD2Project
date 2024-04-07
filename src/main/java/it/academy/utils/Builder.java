package it.academy.utils;

import it.academy.dto.ListForPage;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.spare_parts.SparePartOrderDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.*;
import it.academy.entities.repair.components.RepairType;
import it.academy.utils.converters.spare_parst.SparePartForOrder;
import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.util.List;

@UtilityClass
public class Builder {

    public static <T> ListForPage<T> buildListForPage(List<T> list, int pageNumber, int maxPageNumber, List<String> filters) {
        return ListForPage.<T>builder()
                .pageNumber(pageNumber)
                .list(list)
                .maxPageNumber(maxPageNumber)
                .filtersForPage(filters)
                .build();
    }

    public static Device buildDevice(Long id, Model model, String serialNumber,
                                     Date dateOfSale, Buyer buyer, Salesman salesman) {
        return Device.builder()
                .id(id)
                .model(model)
                .serialNumber(serialNumber)
                .dateOfSale(dateOfSale)
                .buyer(buyer)
                .salesman(salesman)
                .build();
    }

    public static Model buildModel(Long id, String name, Brand brand, DeviceType type) {
        return Model.builder()
                .id(id)
                .name(name)
                .brand(brand)
                .type(type)
                .build();
    }

    public static Brand buildBrand(Long id, String name, boolean isActive) {
        return Brand.builder()
                .id(id)
                .name(name)
                .isActive(isActive)
                .build();
    }

        public static DeviceType buildDeviceType(Long id, String name, boolean isActive) {
        return DeviceType.builder()
                .id(id)
                .name(name)
                .isActive(isActive)
                .build();
    }

    public static Buyer buildBuyer(String name, String surname, String phone) {
        return Buyer.builder()
                .name(name)
                .surname(surname)
                .phone(phone)
                .build();
    }

    public static Salesman buildSalesman(String name, String phone) {
        return Salesman.builder()
                .name(name)
                .phone(phone)
                .build();
    }

        public static RepairType buildRepairType(Long id, String name, String code, String level) {
        return RepairType.builder()
                .id(id)
                .name(name)
                .code(code)
                .level(level)
                .build();
    }

}
