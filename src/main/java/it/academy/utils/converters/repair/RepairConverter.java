package it.academy.utils.converters.repair;

import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.*;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairType;
import it.academy.utils.Builder;
import lombok.experimental.UtilityClass;

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
            RepairTypeDTO repairType = RepairTypeDTO.builder()
                    .id(repair.getRepairType().getId())
                    .code(repair.getRepairType().getCode())
                    .level(repair.getRepairType().getLevel())
                    .name(repair.getRepairType().getName())
                    .build();

            repairDTO.setRepairType(repairType);
            repairDTO.setEndDate(repair.getEndDate());
        }
        return repairDTO;
    }

    public static Repair convertDTOToEntity(RepairDTO repairDTO) {
        Salesman salesman = Builder.buildSalesman(repairDTO.getSalesmanName(), repairDTO.getSalesmanPhone());
        Buyer buyer = Builder.buildBuyer(repairDTO.getBuyerName(), repairDTO.getBuyerSurname(), repairDTO.getBuyerPhone());
        Brand brand = Builder.buildBrand(repairDTO.getBrandId(), repairDTO.getBrandName(), true);
        DeviceType type = Builder.buildDeviceType(repairDTO.getDeviceTypeId(), repairDTO.getDeviceTypeName(), true);
        Model model = Builder.buildModel(repairDTO.getModelId(), repairDTO.getModelName(), brand, type);
        Device device = Builder.buildDevice(repairDTO.getDeviceId(), model, repairDTO.getSerialNumber(), repairDTO.getDateOfSale(), buyer, salesman);

        Repair repair = Repair.builder()
                .id(repairDTO.getId())
//                .repairWorkshop()
                .status(repairDTO.getStatus())
                .category(repairDTO.getCategory())
                .device(device)
                .defectDescription(repairDTO.getDefectDescription())
                .repairWorkshopRepairNumber(repairDTO.getRepairWorkshopRepairNumber())
                .startDate(repairDTO.getStartDate())
                .endDate(repairDTO.getEndDate())
                .deliveryDate(repairDTO.getDeliveryDate())
                .isDeleted(repairDTO.isDeleted())
                .build();


        if (repairDTO.getStatus().isFinishedStatus()) {
            RepairType repairType = RepairType.builder()
                    .id(repairDTO.getRepairType().getId())
                    .code(repairDTO.getRepairType().getCode())
                    .level(repairDTO.getRepairType().getLevel())
                    .name(repairDTO.getRepairType().getName())
                    .build();

            repair.setRepairType(repairType);
            repairDTO.setEndDate(repairDTO.getEndDate());
        }
        return repair;
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
