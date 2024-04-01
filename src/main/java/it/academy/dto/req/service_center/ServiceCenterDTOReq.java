package it.academy.dto.req.service_center;

import it.academy.dto.req.device.BrandDTOReq;
import it.academy.entities.device.components.Brand;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ServiceCenterDTOReq {

    private Long id;

    private String serviceName;

    private String fullName;

    private String actualAddress;

    private String legalAddress;

    private String phone;

    private String email;

    private String taxpayerNumber;

    private String registrationNumber;

    private String bankAccount;

    private String bankCode;

    private String bankName;

    private String bankAddress;

    private Set<BrandDTOReq> brands;

}
