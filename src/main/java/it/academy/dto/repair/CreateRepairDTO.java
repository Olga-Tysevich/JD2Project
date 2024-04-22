package it.academy.dto.repair;

import it.academy.utils.enums.RepairCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRepairDTO {

    private Long serviceCenterId;

    private RepairCategory category;

    private Long selectedBrandId;

    private Long modelId;

    private String serialNumber;

    private String defectDescription;

    private String repairNumber;

    private Date dateOfSale;

    private String salesmanName;

    private String salesmanPhone;

    private String buyerName;

    private String buyerSurname;

    private String buyerPhone;


}
