package it.academy.utils.converters.repair;

import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.device.Device;
import it.academy.entities.device.components.*;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairType;
import it.academy.utils.Builder;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
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
                .repairWorkshop(RepairWorkshopConverter.convertToDTO(repair.getRepairWorkshop()))
                .device(DeviceConverter.convertToDTO(repair.getDevice()))
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

        if (repair.getStatus().isFinishedStatus() && repair.getRepairType() != null) {
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

        Repair repair = Repair.builder()
                .id(repairDTO.getId())
//                .repairWorkshop()
                .repairWorkshop(RepairWorkshopConverter.convertDTOToEntity(repairDTO.getRepairWorkshop()))
                .status(repairDTO.getStatus())
                .category(repairDTO.getCategory())
                .device(DeviceConverter.convertDTOToEntity(repairDTO.getDevice()))
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
