package it.academy.utils.converters.service_center;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.entities.service_center.BankAccount;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.entities.service_center.Requisites;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ServiceCenterConverter {

    public static ServiceCenterDTO convertToDTO(ServiceCenter serviceCenter) {
        return ServiceCenterDTO.builder()
                .id(serviceCenter.getId())
                .serviceName(serviceCenter.getServiceName())
                .fullName(serviceCenter.getRequisites().getFullName())
                .actualAddress(serviceCenter.getRequisites().getActualAddress())
                .legalAddress(serviceCenter.getRequisites().getLegalAddress())
                .phone(serviceCenter.getRequisites().getPhone())
                .email(serviceCenter.getRequisites().getEmail())
                .taxpayerNumber(serviceCenter.getRequisites().getTaxpayerNumber().toString())
                .registrationNumber(serviceCenter.getRequisites().getRegistrationNumber().toString())
                .bankAccount(serviceCenter.getBankAccount().getBankAccount())
                .bankCode(serviceCenter.getBankAccount().getBankCode())
                .bankName(serviceCenter.getBankAccount().getBankName())
                .bankAddress(serviceCenter.getBankAccount().getBankAddress())
                .isActive(serviceCenter.getIsActive())
                .build();
    }

    public static ServiceCenter convertDTOToEntity(ServiceCenterDTO serviceCenterDTO) {
        return ServiceCenter.builder()
                .id(serviceCenterDTO.getId())
                .serviceName(serviceCenterDTO.getServiceName())
                .requisites(Requisites.builder()
                        .fullName(serviceCenterDTO.getFullName())
                        .actualAddress(serviceCenterDTO.getActualAddress())
                        .legalAddress(serviceCenterDTO.getLegalAddress())
                        .phone(serviceCenterDTO.getPhone())
                        .email(serviceCenterDTO.getEmail())
                        .taxpayerNumber(Integer.parseInt(serviceCenterDTO.getTaxpayerNumber()))
                        .registrationNumber(Integer.parseInt(serviceCenterDTO.getRegistrationNumber()))
                        .build())
                .bankAccount(BankAccount.builder()
                        .bankName(serviceCenterDTO.getBankName())
                        .bankAccount(serviceCenterDTO.getBankAccount())
                        .bankAddress(serviceCenterDTO.getBankAddress())
                        .bankCode(serviceCenterDTO.getBankCode())
                        .build())
                .isActive(serviceCenterDTO.getIsActive())
                .build();
    }

    public static List<ServiceCenterDTO> convertListToDTO(List<ServiceCenter> serviceCenters) {
        return serviceCenters.stream()
                .map(ServiceCenterConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<ServiceCenter> convertDTOListToEntityList(List<ServiceCenterDTO> reqList) {
        return reqList.stream()
                .map(ServiceCenterConverter::convertDTOToEntity)
                .collect(Collectors.toList());
    }

}
