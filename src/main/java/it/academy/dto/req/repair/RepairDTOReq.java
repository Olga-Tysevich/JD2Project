package it.academy.dto.req.repair;

import it.academy.dto.req.account.AccountDTOReq;
import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.req.device.DeviceDTOReq;
import it.academy.dto.req.device.ModelDTOReq;
import it.academy.entities.repair.components.RepairStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class RepairDTOReq {

    private Long id;

    private Long number;

    private Long repairWorkshopId;

    private String repairWorkshopName;

    private String serviceRepairNumber;

    private RepairStatus status;

    private String statusDescription;

    private RepairCategoryDTOReq category;

    private RepairTypeDTOReq type;

    private Date startDate;

    private Date endDate;

    private String defectDescription;

    private Long identifiedDefectId;

    private String identifiedDefectDescription;

    private Date deliveryDate;

    private DeviceDTOReq device;

    private boolean isDeleted;

}
