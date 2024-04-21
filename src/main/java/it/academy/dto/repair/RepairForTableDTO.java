package it.academy.dto.repair;

import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairForTableDTO {

    private String serviceCenterName;

    private Long id;

    private String modelDescription;

    private RepairCategory category;

    private RepairStatus status;

    private String serialNumber;

    private Date dateOfSale;

    private String defectDescription;

    private String repairNumber;

    private Date startDate;

    private Date endDate;

    private Date deliveryDate;

    private String repairTypeName;
}
