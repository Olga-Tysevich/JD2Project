package it.academy.dto.repair;

import it.academy.dto.device.DeviceDTO;
import it.academy.dto.repair_workshop.RepairWorkshopDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import lombok.Builder;
import lombok.Data;
import java.sql.Date;

@Data
@Builder
public class RepairDTO {
    //TODO добавить сервсный центр

    private Long id;

    private RepairWorkshopDTO repairWorkshop;

    private DeviceDTO device;

    private RepairCategory category;

    private RepairStatus status;

    private String defectDescription;

    private String repairWorkshopRepairNumber;

    private Date startDate;

    private Date endDate;

    private Date deliveryDate;

    private RepairTypeDTO repairType;

    private boolean isDeleted;

    private String modelDescription;

}
