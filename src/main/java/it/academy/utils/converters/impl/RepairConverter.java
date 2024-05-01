package it.academy.utils.converters.impl;

import it.academy.dto.repair.CreateRepairDTO;
import it.academy.dto.repair.RepairDTO;
import it.academy.dto.repair.RepairTypeDTO;
import it.academy.entities.device.Brand;
import it.academy.entities.device.Device;
import it.academy.entities.device.DeviceType;
import it.academy.entities.device.Model;
import it.academy.entities.device.embeddable.Buyer;
import it.academy.entities.device.embeddable.Salesman;
import it.academy.entities.repair.Repair;
import it.academy.entities.repair.RepairType;
import it.academy.utils.converters.EntityConverter;
import it.academy.utils.enums.RepairStatus;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.constants.Constants.DEVICE_DESCRIPTION_PATTERN;

public class RepairConverter implements EntityConverter<RepairDTO, Repair> {
    private RepairTypeConverter repairTypeConverter = new RepairTypeConverter();

    public RepairDTO convertToDTO(Repair repair) {
        Device device = repair.getDevice();
        Model model = device.getModel();
        DeviceType deviceType = model.getType();
        Brand brand = model.getBrand();
        Salesman salesman = device.getSalesman();
        Buyer buyer = device.getBuyer();
        RepairType repairType = repair.getRepairType();
        String deviceDescription = String.format(DEVICE_DESCRIPTION_PATTERN, brand.getName(),
                deviceType.getName(), model.getName());

        RepairDTO repairDTO = RepairDTO.builder()
                .id(repair.getId())
                .serviceCenterId(repair.getServiceCenter().getId())
                .serviceCenterName(repair.getServiceCenter().getServiceName())
                .deviceId(device.getId())
                .modelId(model.getId())
                .brandId(brand.getId())
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
                .deviceDescription(deviceDescription)
                .build();

        if (repair.getStatus().isFinishedStatus() && repairType != null) {
            RepairTypeDTO repairTypeDTO = repairTypeConverter.convertToDTO(repair.getRepairType());
            repairDTO.setRepairType(repairTypeDTO);
            repairDTO.setEndDate(repair.getEndDate());
        }
        return repairDTO;
    }

    public Repair convertToEntity(CreateRepairDTO repairDTO) {

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

    public Repair convertToEntity(RepairDTO repairDTO) {
        Repair repair = Repair.builder()
                .id(repairDTO.getId())
                .category(repairDTO.getCategory())
                .status(repairDTO.getStatus())
                .device(Device.builder()
                        .id(repairDTO.getDeviceId())
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

        if (repairDTO.getStatus().isFinishedStatus() && repairDTO.getRepairType() != null) {
            RepairType repairType = repairTypeConverter.convertToEntity(repairDTO.getRepairType());
            repair.setRepairType(repairType);
            repair.setEndDate(repair.getEndDate());
        }
        return repair;

    }

    public List<RepairDTO> convertToDTOList(List<Repair> repairs) {
        return repairs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
