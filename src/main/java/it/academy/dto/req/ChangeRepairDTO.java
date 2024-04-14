package it.academy.dto.req;

import it.academy.dto.resp.DeviceDTOResp;
import it.academy.dto.resp.RepairTypeDTO;
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

    private long serviceCenterId;

    private Long id;

    private long deviceId;

    private RepairCategory category;

    private RepairStatus status;

    private String defectDescription;

    private String serviceCenterRepairNumber;

    private Date startDate;

    private Date endDate;

    private Date deliveryDate;

    private long repairTypeId;

    private boolean isDeleted;

    private String modelDescription;

}
