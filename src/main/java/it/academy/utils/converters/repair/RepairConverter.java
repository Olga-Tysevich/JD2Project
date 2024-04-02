package it.academy.utils.converters.repair;

import it.academy.dto.req.repair.RepairDTOReq;
import it.academy.entities.repair.Repair;
import it.academy.utils.converters.device.DefectConverter;
import it.academy.utils.converters.device.DeviceConverter;
import it.academy.utils.converters.repair_workshop.RepairWorkshopConverter;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<RepairDTOReq> convertListToDTOReq(List<Repair> repairs) {
        return repairs.stream()
                .map(RepairConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static Repair convertToEntity(RepairDTOReq req) {
        return Repair.builder()
                .id(req.getId())
                .number(req.getNumber())
                .repairWorkshop(RepairWorkshopConverter
                        .convertToEntity(req.getRepairWorkshop()))
                .serviceRepairNumber(req.getServiceRepairNumber())
                .status(req.getStatus())
                .category(RepairCategoryConverter
                        .convertToEntity(req.getCategory()))
                .type(RepairTypeConverter
                        .convertToEntity(req.getType()))
//                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .defectDescription(req.getDefectDescription())
                .identifiedDefect(DefectConverter
                        .convertToEntity(req.getIdentifiedDefect()))
                .deliveryDate(req.getDeliveryDate())
                .device(DeviceConverter
                        .convertToEntity(req.getDevice()))
                .isDeleted(req.isDeleted())
                .build();
    }

}
