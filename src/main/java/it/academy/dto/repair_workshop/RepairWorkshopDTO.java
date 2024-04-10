package it.academy.dto.repair_workshop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepairWorkshopDTO {

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
