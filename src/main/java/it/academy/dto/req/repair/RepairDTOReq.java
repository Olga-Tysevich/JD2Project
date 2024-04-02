package it.academy.dto.req.repair;

import it.academy.dto.req.device.DefectDTOReq;
import it.academy.dto.req.device.DeviceDTOReq;
import it.academy.dto.req.repair_workshop.RepairWorkshopDTOReq;
import it.academy.entities.repair.components.RepairStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class RepairDTOReq {

    private Long id;

    private Long number;

    private RepairWorkshopDTOReq repairWorkshop;

    private String serviceRepairNumber;

    private RepairStatus status;

    private RepairCategoryDTOReq category;

    private RepairTypeDTOReq type;

    private Date startDate;

    private Date endDate;

    private String defectDescription;

    private DefectDTOReq identifiedDefect;

    private Date deliveryDate;

    private DeviceDTOReq device;

    private boolean isDeleted;

}
