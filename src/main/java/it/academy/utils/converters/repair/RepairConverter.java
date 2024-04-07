package it.academy.utils.converters.repair;

import it.academy.dto.repair.RepairDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.*;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairType;
import it.academy.utils.Builder;
import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.DEVICE_DESCRIPTION_PATTERN;


@UtilityClass
public class RepairConverter {

    //TODO добавить срвисный центр
    public static RepairDTO convertToDTO(Repair repair) {
        Device device = repair.getDevice();
        Model model = device.getModel();
        String deviceType = model.getType().getName();
        String brand = model.getBrand().getName();

        RepairDTO repairDTO = RepairDTO.builder()
                .id(repair.getId())
                .deviceId(repair.getDevice().getId())
                .modelId(repair.getDevice().getModel().getId())
                .modelName(repair.getDevice().getModel().getName())
                .brandId(repair.getDevice().getModel().getBrand().getId())
                .brandName(repair.getDevice().getModel().getBrand().getName())
                .deviceTypeId(repair.getDevice().getModel().getType().getId())
                .deviceTypeName(repair.getDevice().getModel().getType().getName())
                .serialNumber(repair.getDevice().getSerialNumber())
                .dateOfSale(repair.getDevice().getDateOfSale())
                .salesmanName(repair.getDevice().getSalesman().getName())
                .salesmanPhone(repair.getDevice().getSalesman().getPhone())
                .buyerName(repair.getDevice().getBuyer().getName())
                .buyerSurname(repair.getDevice().getBuyer().getSurname())
                .buyerPhone(repair.getDevice().getBuyer().getPhone())
                .buyerPhone(repair.getDevice().getBuyer().getPhone())
                .category(repair.getCategory())
                .status(repair.getStatus())
                .defectDescription(repair.getDefectDescription())
                .repairWorkshopRepairNumber(repair.getRepairWorkshopRepairNumber())
                .startDate(repair.getStartDate())
                .endDate(repair.getEndDate())
                .deliveryDate(repair.getDeliveryDate())
                .isDeleted(repair.isDeleted())
                .modelDescription(String.format(DEVICE_DESCRIPTION_PATTERN, deviceType,
                        brand, model.getName()))
                .build();

        if (repair.getStatus().isFinishedStatus()) {
            RepairType repairType = RepairType.builder()
                    .id(1L)
                    .code("Code1")
                    .level("Level1")
                    .name("Замена платы")
                    .build();
            repair.setType(repairType);
            repair.setEndDate(Date.valueOf("2024-04-07"));

            repairDTO.setEndDate(repair.getEndDate());
            repairDTO.setRepairTypeId(repair.getType().getId());
            repairDTO.setRepairTypeName(repair.getType().getName());
            repairDTO.setRepairTypeCode(repair.getType().getCode());
            repairDTO.setRepairTypeLevel(repair.getType().getLevel());
        }
        return repairDTO;
    }

    public static Repair convertDTOToEntity(RepairDTO dto) {
        Salesman salesman = Builder.buildSalesman(dto.getSalesmanName(), dto.getSalesmanPhone());
        Buyer buyer = Builder.buildBuyer(dto.getBuyerName(), dto.getBuyerSurname(), dto.getBuyerPhone());
        Brand brand = Builder.buildBrand(dto.getBrandId(), dto.getBrandName(), true);
        DeviceType type = Builder.buildDeviceType(dto.getDeviceTypeId(), dto.getDeviceTypeName(), true);
        Model model = Builder.buildModel(dto.getModelId(), dto.getModelName(), brand, type);
        Device device = Builder.buildDevice(dto.getDeviceId(), model, dto.getSerialNumber(), dto.getDateOfSale(), buyer, salesman);
        RepairType repairType = dto.getRepairTypeId() != null?
                Builder.buildRepairType(dto.getRepairTypeId(), dto.getRepairTypeName(),
                        dto.getRepairTypeCode(), dto.getRepairTypeLevel()) : null;
        return Repair.builder()
                .id(dto.getId())
//                .repairWorkshop()
                .status(dto.getStatus())
                .category(dto.getCategory())
                .device(device)
                .defectDescription(dto.getDefectDescription())
                .repairWorkshopRepairNumber(dto.getRepairWorkshopRepairNumber())
                .type(repairType)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .deliveryDate(dto.getDeliveryDate())
                .isDeleted(dto.isDeleted())
                .build();
    }

    public static List<RepairDTO> convertListToDTO(List<Repair> repairs) {
        return repairs.stream()
                .map(RepairConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Repair> convertDTOListToEntityList(List<RepairDTO> repairs) {
        return repairs.stream()
                .map(RepairConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }
}
