package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.RepairDTOReq;
import it.academy.entities.repair.Repair;
import it.academy.utils.converters.device.DefectConverter;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairConverter {

    public static RepairDTOReq convertToDTOReq(Repair repair) {
        return RepairDTOReq.builder()
                .id(repair.getId())
                .number(repair.getNumber())
                .repairWorkshop(RepairWorkshopConverter
                        .convertToDTOReq(repair.getRepairWorkshop()))
                .serviceRepairNumber(repair.getServiceRepairNumber())
                .status(repair.getStatus())
                .category(RepairCategoryConverter
                        .convertToDTOReq(repair.getCategory()))
                .type(RepairTypeConverter
                        .convertToDTOReq(repair.getType()))
                .startDate(repair.getStartDate())
                .endDate(repair.getEndDate())
                .defectDescription(repair.getDefectDescription())
                .identifiedDefect(DefectConverter
                        .convertToDTOReq(repair.getIdentifiedDefect()))
                .deliveryDate(repair.getDeliveryDate())
                .device(DeviceConverter
                        .convertToDTOReq(repair.getDevice()))
                .isDeleted(repair.isDeleted())
                .build();
    }

    public static Repair convertDTOReqToEntity(RepairDTOReq req) {
        return Repair.builder()
                .id(req.getId())
                .number(req.getNumber())
                .repairWorkshop(RepairWorkshopConverter
                        .convertDTOReqToEntity(req.getRepairWorkshop()))
                .serviceRepairNumber(req.getServiceRepairNumber())
                .status(req.getStatus())
                .category(RepairCategoryConverter
                        .convertDTOReqToEntity(req.getCategory()))
                .type(RepairTypeConverter
                        .convertDTOReqToEntity(req.getType()))
                .endDate(req.getEndDate())
                .defectDescription(req.getDefectDescription())
                .identifiedDefect(DefectConverter
                        .convertDTOReqToEntity(req.getIdentifiedDefect()))
                .deliveryDate(req.getDeliveryDate())
                .device(DeviceConverter
                        .convertDTOReqToEntity(req.getDevice()))
                .isDeleted(req.isDeleted())
                .build();
    }

    public static List<RepairDTOReq> convertListToDTOReq(List<Repair> repairs) {
        return repairs.stream()
                .map(RepairConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static List<Repair> convertDTOReqListToEntityList(List<RepairDTOReq> repairs) {
        return repairs.stream()
                .map(RepairConverter::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
