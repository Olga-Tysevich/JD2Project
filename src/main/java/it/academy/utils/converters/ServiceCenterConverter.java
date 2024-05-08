package it.academy.utils.converters;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.embeddable.BankAccount;
import it.academy.entities.account.embeddable.Requisites;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class ServiceCenterConverter {

    public ServiceCenterDTO convertToDTO(ServiceCenter serviceCenter) {
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

    public ServiceCenter convertToEntity(ServiceCenterDTO serviceCenterDTO) {
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
                .isActive(Objects.requireNonNullElse(serviceCenterDTO.getIsActive(), true))
                .build();
    }

    public List<ServiceCenterDTO> convertToDTOList(List<ServiceCenter> serviceCenters) {
        return serviceCenters.stream()
                .map(ServiceCenterConverter::convertToDTO)
                .collect(Collectors.toList());
    }

}
