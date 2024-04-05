package it.academy.utils.converters.repair;

import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairType;
import it.academy.utils.converters.device.DeviceConverter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class RepairConverter {

    //TODO добавить срвисный центр
    public static RepairDTO convertToDTO(Repair repair) {
        RepairTypeDTO type = repair.getType() == null ?
                null : RepairTypeConverter.convertToDTO(repair.getType());

        return RepairDTO.builder()
                .id(repair.getId())
                .device(DeviceConverter.convertToDTO(repair.getDevice()))
                .type(type)
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
        RepairType type = repairDTO.getType() == null ?
                null : RepairTypeConverter.convertDTOToEntity(repairDTO.getType());
        
        return Repair.builder()
                .id(repairDTO.getId())
                .device(DeviceConverter.convertDTOToEntity(repairDTO.getDevice()))
                .type(type)
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
