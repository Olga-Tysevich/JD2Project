package it.academy.dto.device.resp;

import it.academy.dto.device.req.ModelDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class DeviceDTOResp {

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
