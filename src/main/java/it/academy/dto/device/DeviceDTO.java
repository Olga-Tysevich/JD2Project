package it.academy.dto.device;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class DeviceDTO {

    private Long id;

    private ModelDTO model;

    private String serialNumber;

    private Date dateOfSale;

    private String salesmanName;

    private String salesmanPhone;

    private String buyerName;

    private String buyerSurname;

    private String buyerPhone;

}
