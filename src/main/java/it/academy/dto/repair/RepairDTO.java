package it.academy.dto.repair;

import it.academy.dto.device.DeviceDTO;
import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {

    private Long serviceCenterId;

    private Long serviceCenterName;

    private Long id;

    private DeviceDTO deviceDTO;

    private RepairCategory category;

    private RepairStatus status;

    private String serialNumber;

    private Date dateOfSale;

    private String salesmanName;

    private String salesmanPhone;

    private String buyerName;

    private String buyerSurname;

    private String buyerPhone;

    private String defectDescription;

    private String repairNumber;

    private Date startDate;

    private Date endDate;

    private Long repairTypeId;

    private String repairTypeName;

    private String repairTypeCode;

    private String repairTypeLevel;

}
