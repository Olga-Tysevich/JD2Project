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
                .isActive(repairWorkshop.getIsActive())
                .build();
    }

    public static RepairWorkshop convertDTOToEntity(RepairWorkshopDTO repairWorkshop) {
        return RepairWorkshop.builder()
                .id(repairWorkshop.getId())
                .serviceName(repairWorkshop.getServiceName())
                .requisites(Requisites.builder()
                        .fullName(repairWorkshop.getFullName())
                        .actualAddress(repairWorkshop.getActualAddress())
                        .phone(repairWorkshop.getPhone())
                        .email(repairWorkshop.getEmail())
                        .taxpayerNumber(repairWorkshop.getTaxpayerNumber())
                        .registrationNumber(repairWorkshop.getRegistrationNumber())
                        .build())
                .bankAccount(BankAccount.builder()
                        .bankName(repairWorkshop.getBankName())
                        .bankAccount(repairWorkshop.getBankAccount())
                        .bankAddress(repairWorkshop.getBankAddress())
                        .bankCode(repairWorkshop.getBankCode())
                        .build())
                .isActive(repairWorkshop.getIsActive())
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
