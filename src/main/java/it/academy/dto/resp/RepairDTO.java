package it.academy.dto.resp;

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

    private Long id;

    private DeviceDTOResp device;

    private RepairCategory category;

    private RepairStatus status;

    private String defectDescription;

    private String serviceCenterRepairNumber;

    private Date startDate;

    private Date endDate;

    private Date deliveryDate;

    private RepairTypeDTO repairType;

    private boolean isDeleted;

    private String modelDescription;

}
