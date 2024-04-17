package it.academy.utils.converters;

import it.academy.dto.req.ChangeRepairDTO;
import it.academy.dto.resp.RepairDTO;
import it.academy.entities.*;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;
import static it.academy.utils.constants.Constants.DEVICE_DESCRIPTION_PATTERN;

@UtilityClass
public class RepairConverter {

    public static ChangeRepairDTO convertToDTO(Repair repair) {
        Device device = repair.getDevice();
        Model model = device.getModel();
        DeviceType deviceType = model.getType();
        Brand brand = model.getBrand();

        ChangeRepairDTO repairDTO = ChangeRepairDTO.builder()
                .id(repair.getId())
                .serviceCenterName(repair.getServiceCenter().getServiceName())
                .modelDescription(String.format(DEVICE_DESCRIPTION_PATTERN, deviceType.getName(),
                        brand.getName(), model.getName()))
                .brandId(brand.getId())
                .category(repair.getCategory())
                .status(repair.getStatus())
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .defectDescription(repair.getDefectDescription())
                .repairNumber(repair.getRepairNumber())
                .startDate(repair.getStartDate())
                .endDate(repair.getEndDate())
                .deliveryDate(repair.getDeliveryDate())
                .build();

        if (repair.getStatus().isFinishedStatus() && repair.getRepairType() != null) {
            repairDTO.setRepairTypeName(repair.getRepairType().getName());
            repairDTO.setEndDate(repair.getEndDate());
        }

        if (repair.getStatus().isDeliveredStatus()) {
            repairDTO.setDeliveryDate(repair.getDeliveryDate());
        }
        return repairDTO;
    }

    public static RepairDTO convertToRepairDTO(Repair repair) {
        Device device = repair.getDevice();
        Salesman salesman = device.getSalesman();
        Buyer buyer = device.getBuyer();
        Model model = device.getModel();
        Brand brand = model.getBrand();
        RepairType repairType = repair.getRepairType();

        RepairDTO repairDTO = RepairDTO.builder()
                .id(repair.getId())
                .serviceCenterId(repair.getServiceCenter().getId())
                .deviceId(device.getId())
                .brandId(brand.getId())
                .modelId(model.getId())
                .category(repair.getCategory())
                .status(repair.getStatus())
                .serialNumber(device.getSerialNumber())
                .salesmanName(salesman.getName())
                .salesmanPhone(salesman.getPhone())
                .buyerName(buyer.getName())
                .buyerSurname(buyer.getSurname())
                .buyerPhone(buyer.getPhone())
                .dateOfSale(device.getDateOfSale())
                .defectDescription(repair.getDefectDescription())
                .repairNumber(repair.getRepairNumber())
                .startDate(repair.getStartDate())
                .build();

        if (repair.getStatus().isFinishedStatus() && repairType != null) {
            repairDTO.setRepairTypeName(repairType.getName());
            repairDTO.setEndDate(repair.getEndDate());
        }

        if (repair.getStatus().isDeliveredStatus()) {
            repairDTO.setDeliveryDate(repair.getDeliveryDate());
        }
        return repairDTO;
    }



    public static Repair convertToEntity(ChangeRepairDTO repairDTO) {

        return Repair.builder()
                .id(repairDTO.getId())
                .status(repairDTO.getStatus())
                .category(repairDTO.getCategory())
                .defectDescription(repairDTO.getDefectDescription())
                .repairNumber(repairDTO.getRepairNumber())
                .startDate(repairDTO.getStartDate())
                .endDate(repairDTO.getEndDate())
                .deliveryDate(repairDTO.getDeliveryDate())
                .build();
    }

    public static Repair convertToEntity(RepairDTO repairDTO) {
        return Repair.builder()
                .id(repairDTO.getId())
                .category(repairDTO.getCategory())
                .status(repairDTO.getStatus())
                .device(Device.builder()
                        .serialNumber(repairDTO.getSerialNumber())
                        .buyer(Buyer.builder()
                                .name(repairDTO.getBuyerName())
                                .surname(repairDTO.getBuyerSurname())
                                .phone(repairDTO.getBuyerPhone())
                                .build())
                        .salesman(Salesman.builder()
                                .name(repairDTO.getSalesmanName())
                                .phone(repairDTO.getSalesmanPhone())
                                .build())
                        .dateOfSale(repairDTO.getDateOfSale())
                        .build())
                .defectDescription(repairDTO.getDefectDescription())
                .repairNumber(repairDTO.getRepairNumber())
                .startDate(repairDTO.getStartDate())
                .endDate(repairDTO.getEndDate())
                .deliveryDate(repairDTO.getDeliveryDate())
                .build();
    }

    public static List<ChangeRepairDTO> convertToDTOList(List<Repair> repairs) {
        return repairs.stream()
                .map(RepairConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
