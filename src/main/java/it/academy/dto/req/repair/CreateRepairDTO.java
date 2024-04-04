package it.academy.dto.req.repair;

import it.academy.entities.repair.components.RepairStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class CreateRepairDTO {

//    private Long workshopId;

    private Long id;

    private Long modelId;

    private Long categoryId;

    private RepairStatus status;

    private String serialNumber;

    private String defectDescription;

    private String serviceRepairNumber;

    private Date dateOfSale;

    private String buyerName;

    private String buyerSurname;

    private String buyerPhone;

    private String salesmanName;

    private String salesmanPhone;

}
