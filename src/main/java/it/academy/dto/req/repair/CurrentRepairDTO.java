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

//    private Long number;

//    private Long repairWorkshopId;

//    private String repairWorkshopName;

    private String serviceRepairNumber;

    private RepairStatus status;

    private String statusDescription;

    private RepairCategoryDTO category;

    private Date startDate;

    private String defectDescription;

    private DeviceDTO device;

    private boolean isDeleted;
}
