package it.academy.dto.req.repair_workshop;

import it.academy.dto.req.device.BrandDTOReq;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RepairWorkshopDTOReq {

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
