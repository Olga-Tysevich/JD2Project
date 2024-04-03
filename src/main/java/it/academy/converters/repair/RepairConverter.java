package it.academy.converters.repair;

import it.academy.converters.Converter;
import it.academy.dto.req.repair.RepairDTOReq;
import it.academy.entities.repair.Repair;
import it.academy.converters.device.DefectConverter;
import it.academy.converters.device.DeviceConverter;
import it.academy.converters.repair_workshop.RepairWorkshopConverter;
import java.util.List;
import java.util.stream.Collectors;

public class RepairConverter implements Converter<Repair, RepairDTOReq> {
    private RepairWorkshopConverter repairWorkshopConverter = new RepairWorkshopConverter();
    private RepairTypeConverter repairTypeConverter = new RepairTypeConverter();
    private RepairCategoryConverter repairCategoryConverter = new RepairCategoryConverter();
    private DefectConverter defectConverter = new DefectConverter();
    private DeviceConverter deviceConverter = new DeviceConverter();

    @Override
    public RepairDTOReq convertToDTOReq(Repair repair) {
        return RepairDTOReq.builder()
                .id(repair.getId())
                .number(repair.getNumber())
                .repairWorkshop(repairWorkshopConverter
                        .convertToDTOReq(repair.getRepairWorkshop()))
                .serviceRepairNumber(repair.getServiceRepairNumber())
                .status(repair.getStatus())
                .category(repairCategoryConverter
                        .convertToDTOReq(repair.getCategory()))
                .type(repairTypeConverter
                        .convertToDTOReq(repair.getType()))
                .startDate(repair.getStartDate())
                .endDate(repair.getEndDate())
                .defectDescription(repair.getDefectDescription())
                .identifiedDefect(defectConverter
                        .convertToDTOReq(repair.getIdentifiedDefect()))
                .deliveryDate(repair.getDeliveryDate())
                .device(deviceConverter
                        .convertToDTOReq(repair.getDevice()))
                .isDeleted(repair.isDeleted())
                .build();
    }

    @Override
    public Repair convertDTOReqToEntity(RepairDTOReq req) {
        return Repair.builder()
                .id(req.getId())
                .number(req.getNumber())
                .repairWorkshop(repairWorkshopConverter
                        .convertDTOReqToEntity(req.getRepairWorkshop()))
                .serviceRepairNumber(req.getServiceRepairNumber())
                .status(req.getStatus())
                .category(repairCategoryConverter
                        .convertDTOReqToEntity(req.getCategory()))
                .type(repairTypeConverter
                        .convertDTOReqToEntity(req.getType()))
                .endDate(req.getEndDate())
                .defectDescription(req.getDefectDescription())
                .identifiedDefect(defectConverter
                        .convertDTOReqToEntity(req.getIdentifiedDefect()))
                .deliveryDate(req.getDeliveryDate())
                .device(deviceConverter
                        .convertDTOReqToEntity(req.getDevice()))
                .isDeleted(req.isDeleted())
                .build();
    }

    @Override
    public List<RepairDTOReq> convertListToDTOReq(List<Repair> repairs) {
        return repairs.stream()
                .map(this::convertToDTOReq)
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> convertDTOReqListToEntityList(List<RepairDTOReq> repairs) {
        return repairs.stream()
                .map(this::convertDTOReqToEntity)
                .collect(Collectors.toList());
    }

}
