package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.RepairDTO;
import it.academy.dto.req.repair.RepairTypeDTOReq;
import it.academy.entities.device.components.Defect;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.components.RepairType;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.utils.MessageManager;
import it.academy.utils.converters.device.DeviceConverter;
import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairConverter {

    public static RepairDTO convertToDTO(Repair repair) {
        RepairWorkshop repairWorkshop = repair.getRepairWorkshop();
        Defect defect = repair.getIdentifiedDefect();
        RepairType repairType = repair.getType();
        return RepairDTO.builder()
                .id(repair.getId())
//                .number(repair.getNumber())
                .repairWorkshopId(repairWorkshop != null ? repairWorkshop.getId() : null)
                .repairWorkshopName(repairWorkshop != null ? repairWorkshop.getServiceName() : null)
                .serviceRepairNumber(repair.getServiceRepairNumber())
                .status(repair.getStatus())
                .statusDescription(MessageManager.getMessage(repair.getStatus().name().toLowerCase()))
                .category(RepairCategoryConverter
                        .convertToDTO(repair.getCategory()))
                .type(repairType != null ? RepairTypeConverter
                        .convertToDTOReq(repair.getType()) : null)
                .startDate(repair.getStartDate())
                .endDate(repair.getEndDate())
                .defectDescription(repair.getDefectDescription())
                .identifiedDefectId(defect != null ? defect.getId() : null)
                .identifiedDefectDescription(defect != null ? defect.getDescription() : null)
                .deliveryDate(repair.getDeliveryDate())
                .device(DeviceConverter
                        .convertToDTOReq(repair.getDevice()))
                .isDeleted(repair.isDeleted())
                .build();
    }

    public static Repair convertDTOToEntity(RepairDTO req) {
        RepairTypeDTOReq repairType = req.getType();
        return Repair.builder()
                .id(req.getId())
//                .number(req.getNumber())
                .repairWorkshop(RepairWorkshop.builder()
                        .id(req.getRepairWorkshopId())
                        .build())
                .serviceRepairNumber(req.getServiceRepairNumber())
                .status(req.getStatus())
                .category(RepairCategoryConverter
                        .convertDTOToEntity(req.getCategory()))
                .type(repairType != null? RepairTypeConverter
                        .convertDTOReqToEntity(req.getType()) : null)
                .endDate(req.getEndDate())
                .defectDescription(req.getDefectDescription())
                .identifiedDefect(Defect.builder()
                        .id(req.getIdentifiedDefectId())
                        .description(req.getDefectDescription())
                        .build())
                .deliveryDate(req.getDeliveryDate())
                .device(DeviceConverter
                        .convertDTOReqToEntity(req.getDevice()))
                .isDeleted(req.isDeleted())
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
