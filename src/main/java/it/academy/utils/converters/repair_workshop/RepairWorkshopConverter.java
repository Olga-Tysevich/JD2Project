package it.academy.utils.converters.repair_workshop;

import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.entities.repair_workshop.BankAccount;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.entities.repair_workshop.Requisites;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairWorkshopConverter {

    public static RepairWorkshopDTO convertToDTO(RepairWorkshop repairWorkshop) {
        return RepairWorkshopDTO.builder()
                .id(repairWorkshop.getId())
                .serviceName(repairWorkshop.getServiceName())
                .fullName(repairWorkshop.getRequisites().getFullName())
                .actualAddress(repairWorkshop.getRequisites().getActualAddress())
                .legalAddress(repairWorkshop.getRequisites().getLegalAddress())
                .phone(repairWorkshop.getRequisites().getPhone())
                .email(repairWorkshop.getRequisites().getEmail())
                .taxpayerNumber(repairWorkshop.getRequisites().getTaxpayerNumber())
                .registrationNumber(repairWorkshop.getRequisites().getRegistrationNumber())
                .bankAccount(repairWorkshop.getBankAccount().getBankAccount())
                .bankCode(repairWorkshop.getBankAccount().getBankCode())
                .bankName(repairWorkshop.getBankAccount().getBankName())
                .bankAddress(repairWorkshop.getBankAccount().getBankAddress())
                .build();
    }

    public static RepairWorkshop convertDTOToEntity(RepairWorkshopDTO req) {
        return RepairWorkshop.builder()
                .id(req.getId())
                .serviceName(req.getServiceName())
                .requisites(Requisites.builder()
                        .fullName(req.getFullName())
                        .actualAddress(req.getActualAddress())
                        .phone(req.getPhone())
                        .email(req.getEmail())
                        .taxpayerNumber(req.getTaxpayerNumber())
                        .registrationNumber(req.getRegistrationNumber())
                        .build())
                .bankAccount(BankAccount.builder()
                        .bankName(req.getBankName())
                        .bankAccount(req.getBankAccount())
                        .bankAddress(req.getBankAddress())
                        .bankCode(req.getBankCode())
                        .build())
                .build();
    }

    public static List<RepairWorkshopDTO> convertListToDTO(List<RepairWorkshop> repairWorkshops) {
        return repairWorkshops.stream()
                .map(RepairWorkshopConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<RepairWorkshop> convertDTOListToEntityList(List<RepairWorkshopDTO> reqList) {
        return reqList.stream()
                .map(RepairWorkshopConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

}
