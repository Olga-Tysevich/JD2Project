package it.academy.utils.services.converters.service_center;
import it.academy.dto.req.service_center.ServiceCenterDTOReq;
import it.academy.entities.service_center.ServiceCenter;
import it.academy.entities.service_center.components.BankAccount;
import it.academy.entities.service_center.components.Requisites;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceCenterConverter {

    public static ServiceCenterDTOReq convertToDTOReq(ServiceCenter serviceCenter) {
        return ServiceCenterDTOReq.builder()
                .id(serviceCenter.getId())
                .serviceName(serviceCenter.getServiceName())
                .fullName(serviceCenter.getRequisites().getFullName())
                .actualAddress(serviceCenter.getRequisites().getActualAddress())
                .legalAddress(serviceCenter.getRequisites().getLegalAddress())
                .phone(serviceCenter.getRequisites().getPhone())
                .email(serviceCenter.getRequisites().getEmail())
                .taxpayerNumber(serviceCenter.getRequisites().getTaxpayerNumber())
                .registrationNumber(serviceCenter.getRequisites().getRegistrationNumber())
                .bankAccount(serviceCenter.getBankAccount().getBankAccount())
                .bankCode(serviceCenter.getBankAccount().getBankCode())
                .bankName(serviceCenter.getBankAccount().getBankName())
                .bankAddress(serviceCenter.getBankAccount().getBankAddress())
                .build();
    }

    public static List<ServiceCenterDTOReq> convertListToDTOReq(List<ServiceCenter> serviceCenters) {
        return serviceCenters.stream()
                .map(ServiceCenterConverter::convertToDTOReq)
                .collect(Collectors.toList());
    }

    public static ServiceCenter convertToEntity(ServiceCenterDTOReq req) {
        return ServiceCenter.builder()
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
}
