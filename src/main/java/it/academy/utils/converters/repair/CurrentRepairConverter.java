package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.CurrentRepairDTO;
import it.academy.entities.repair.Repair;
import it.academy.utils.MessageManager;
import it.academy.utils.converters.device.DeviceConverter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CurrentRepairConverter {

    public static CurrentRepairDTO convertToDTO(Repair repair) {
        return CurrentRepairDTO.builder()
                .id(repair.getId())
//                .number(repair.getNumber())
//                .repairWorkshopId(repair.getRepairWorkshop().getId())
//                .repairWorkshopName(repair.getRepairWorkshop().getServiceName())
                .serviceRepairNumber(repair.getServiceRepairNumber())
                .status(repair.getStatus())
                .statusDescription(MessageManager.getMessage(repair.getStatus().name().toLowerCase()))
                .category(RepairCategoryConverter
                        .convertToDTO(repair.getCategory()))
                .startDate(repair.getStartDate())
                .defectDescription(repair.getDefectDescription())
                .device(DeviceConverter
                        .convertToDTOReq(repair.getDevice()))
                .isDeleted(repair.isDeleted())
                .build();
    }

    public static Repair convertDTOToEntity(CurrentRepairDTO req) {
        return Repair.builder()
                .id(req.getId())
//                .number(req.getNumber())
//                .repairWorkshop(RepairWorkshop.builder()
//                        .id(req.getRepairWorkshopId())
//                        .build())
                .serviceRepairNumber(req.getServiceRepairNumber())
                .status(req.getStatus())
                .category(RepairCategoryConverter
                        .convertDTOToEntity(req.getCategory()))
                .defectDescription(req.getDefectDescription())
                .device(DeviceConverter
                        .convertDTOReqToEntity(req.getDevice()))
                .isDeleted(req.isDeleted())
                .build();
    }

    public static List<CurrentRepairDTO> convertListToDTO(List<Repair> repairs) {
        return repairs.stream()
                .map(CurrentRepairConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Repair> convertDTOListToEntityList(List<CurrentRepairDTO> repairs) {
        return repairs.stream()
                .map(CurrentRepairConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }
}
