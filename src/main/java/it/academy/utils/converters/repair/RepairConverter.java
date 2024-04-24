package it.academy.utils.converters.repair;

import it.academy.dto.repair.ChangeRepairDTO;
import it.academy.dto.repair.CreateRepairDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairForTableDTO;
import it.academy.entities.device.Brand;
import it.academy.entities.device.Device;
import it.academy.entities.device.DeviceType;
import it.academy.entities.device.Model;
import it.academy.entities.device.embeddable.Buyer;
import it.academy.entities.device.embeddable.Salesman;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.RepairType;
import it.academy.utils.enums.RepairStatus;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.stream.Collectors;
import static it.academy.utils.constants.Constants.DEVICE_DESCRIPTION_PATTERN;

@UtilityClass
public class RepairConverter {

    public static RepairForTableDTO convertToDTO(Repair repair) {
        Device device = repair.getDevice();
        Model model = device.getModel();
        DeviceType deviceType = model.getType();
        Brand brand = model.getBrand();

        RepairForTableDTO repairDTO = RepairForTableDTO.builder()
                .id(repair.getId())
                .brandId(brand.getId())
                .serviceCenterName(repair.getServiceCenter().getServiceName())
                .modelDescription(String.format(DEVICE_DESCRIPTION_PATTERN, deviceType.getName(),
                        brand.getName(), model.getName()))
                .category(repair.getCategory())
                .status(repair.getStatus())
                .serialNumber(device.getSerialNumber())
                .dateOfSale(device.getDateOfSale())
                .defectDescription(repair.getDefectDescription())
                .repairNumber(repair.getRepairNumber())
                .startDate(repair.getStartDate())
                .build();

        if (repair.getStatus().isFinishedStatus() && repair.getRepairType() != null) {
            repairDTO.setRepairTypeName(repair.getRepairType().getName());
            repairDTO.setEndDate(repair.getEndDate());
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
                .build();
    }

    public static Repair convertToEntity(CreateRepairDTO repairDTO) {

        return Repair.builder()
                .status(RepairStatus.REQUEST)
                .category(repairDTO.getCategory())
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
                .build();
    }

    public static List<RepairForTableDTO> convertToDTOList(List<Repair> repairs) {
        return repairs.stream()
                .map(RepairConverter::convertToDTO)
                .collect(Collectors.toList());
    }


}
