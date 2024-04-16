package it.academy.dto.resp;

import it.academy.utils.enums.RepairCategory;
import it.academy.utils.enums.RepairStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {

    private AccountDTO currentAccount;

    private Long serviceCenterId;

    private Long id;

    private Long deviceId;

    private Long brandId;

    private Long modelId;

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

    private Date deliveryDate;

    private Long repairTypeId;

    private String repairTypeName;

    private String repairTypeCode;

    private String repairTypeLevel;

}
