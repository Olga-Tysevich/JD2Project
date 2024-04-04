package it.academy.dto.req.repair;

import it.academy.dto.req.device.DeviceDTO;
import it.academy.entities.repair.components.RepairStatus;
import it.academy.utils.MessageManager;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class CurrentRepairDTO {

    private Long id;

    private Long number;

    private Long repairWorkshopId;

    private String repairWorkshopName;

    private String serviceRepairNumber;

    @Builder.Default
    private RepairStatus status = RepairStatus.REQUEST;

    @Builder.Default
    private String statusDescription = MessageManager.getMessage(RepairStatus.REQUEST.name().toLowerCase());

    private RepairCategoryDTO category;

    private RepairTypeDTOReq type;

    private Date startDate;

    private Date endDate;

    private String defectDescription;

    private Long identifiedDefectId;

    private String identifiedDefectDescription;

    private Date deliveryDate;

    private DeviceDTO device;

    private boolean isDeleted;
}
