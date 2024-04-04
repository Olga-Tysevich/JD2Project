package it.academy.dto.req.device;

import it.academy.entities.device.components.Buyer;
import it.academy.entities.device.components.Salesman;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class DeviceDTO {

    private Long id;

    private String modelDescription;

    private Long modelId;

    private String modelName;

    private Long brandId;

    private String brandName;

    private Long deviceTypeId;

    private String deviceTypeName;

//    private ModelDTO model;

    private String serialNumber;

    private Date dateOfSale;

    private String buyerName;

    private String buyerSurname;

    private String buyerPhone;

//    private Buyer buyer;


    private String salesmanName;

    private String salesmanPhone;

//    private Salesman salesman;
}
