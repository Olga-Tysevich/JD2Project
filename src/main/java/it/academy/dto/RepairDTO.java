package it.academy.dto;

import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class RepairDTO {

    private Long id;

    private DeviceDTO device;

    //TODO добавить сервсный центр
    private RepairCategory category;

    private RepairStatus status;

    private String defectDescription;

    private String repairWorkshopRepairNumber;

    private Date startDate;

    private Date endDate;

    private Date deliveryDate;

    private RepairTypeDTO type;

    private boolean isDeleted;

}
