package it.academy.utils.converters;

import it.academy.dto.RepairDTO;
import it.academy.entities.repair.Repair;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairConverter {

    //TODO добавить срвисный центр
    public static RepairDTO convertToDTO(Repair repair) {
        return RepairDTO.builder()
                .id(repair.getId())
                .device(DeviceConverter.convertToDTO(repair.getDevice()))
                .type(RepairTypeConverter.convertToDTO(repair.getType()))
                .category(repair.getCategory())
                .status(repair.getStatus())
                .defectDescription(repair.getDefectDescription())
                .repairWorkshopRepairNumber(repair.getRepairWorkshopRepairNumber())
                .startDate(repair.getStartDate())
                .endDate(repair.getEndDate())
                .deliveryDate(repair.getDeliveryDate())
                .isDeleted(repair.isDeleted())
                .build();
    }

    public static Repair convertDTOToEntity(RepairDTO repairDTO) {
        return Repair.builder()
                .id(repairDTO.getId())
                .device(DeviceConverter.convertDTOToEntity(repairDTO.getDevice()))
                .type(RepairTypeConverter.convertDTOToEntity(repairDTO.getType()))
                .category(repairDTO.getCategory())
                .status(repairDTO.getStatus())
                .defectDescription(repairDTO.getDefectDescription())
                .repairWorkshopRepairNumber(repairDTO.getRepairWorkshopRepairNumber())
                .startDate(repairDTO.getStartDate())
                .endDate(repairDTO.getEndDate())
                .deliveryDate(repairDTO.getDeliveryDate())
                .isDeleted(repairDTO.isDeleted())
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
