package it.academy.utils.services.converters.repair_workshop;

import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.req.repair_workshop.RepairWorkshopDTOReq;
import it.academy.entities.device.components.Brand;
import it.academy.entities.repair_workshop.RepairWorkshop;
import it.academy.entities.repair_workshop.components.BankAccount;
import it.academy.entities.repair_workshop.components.Requisites;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RepairWorkshopConverter {

    public static RepairWorkshopDTOReq convertToDTOReq(RepairWorkshop repairWorkshop) {
        return RepairWorkshopDTOReq.builder()
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
                .brands(repairWorkshop.getBrands().stream()
                        .map(b -> BrandDTOReq.builder()
                        .name(b.getName())
                        .isActive(b.getIsActive())
                        .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<RepairWorkshopDTOReq> convertListToDTOReq(List<RepairWorkshop> repairWorkshops) {
        return repairWorkshops.stream()
                .map(RepairWorkshopConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static RepairWorkshop convertToEntity(RepairWorkshopDTOReq req) {
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
                .brands(req.getBrands().stream()
                        .map(b -> Brand.builder()
                                .name(b.getName())
                                .isActive(b.getIsActive())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
