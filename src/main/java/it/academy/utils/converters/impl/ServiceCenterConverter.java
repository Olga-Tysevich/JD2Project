package it.academy.utils.converters.impl;

import it.academy.dto.account.ServiceCenterDTO;
import it.academy.entities.account.ServiceCenter;
import it.academy.entities.account.embeddable.BankAccount;
import it.academy.entities.account.embeddable.Requisites;
import it.academy.utils.converters.EntityConverter;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceCenterConverter implements EntityConverter<ServiceCenterDTO, ServiceCenter> {

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
                .isActive(serviceCenterDTO.getIsActive())
                .build();
    }

    public List<ServiceCenterDTO> convertToDTOList(List<ServiceCenter> serviceCenters) {
        return serviceCenters.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
