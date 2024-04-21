package it.academy.dto.repair;

import it.academy.dto.account.AccountDTO;
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
public class ChangeRepairDTO {

    private AccountDTO currentAccount;

    private Long serviceCenterId;

    private String serviceCenterName;

    private Long id;

    private Long deviceId;

    private Long brandId;

    private RepairCategory category;

    private RepairStatus status;

    private String defectDescription;

    private String serialNumber;

    private String repairNumber;

    private Date startDate;

    private Date dateOfSale;

    private Date endDate;

    private String repairTypeName;

    private Date deliveryDate;

    private Long repairTypeId;

    private String modelDescription;

}
