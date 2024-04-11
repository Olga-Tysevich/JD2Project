package it.academy.dto.service_center;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCenterDTO {

    private Long id;

    private String serviceName;

    private String bankAccount;

    private String bankCode;

    private String bankName;

    private String bankAddress;

    private String fullName;

    private String actualAddress;

    private String legalAddress;

    private String phone;

    private String email;

    private String taxpayerNumber;

    private String registrationNumber;

    private Boolean isActive;

}
