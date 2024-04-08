package it.academy.dto.repair;

import it.academy.dto.spare_parts.SparePartDTO;
import it.academy.entities.repair.components.RepairCategory;
import it.academy.entities.repair.components.RepairStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.Map;

@Data
@Builder
public class RepairDTO {
    //TODO добавить сервсный центр

    private Long id;

    private Long deviceId;

    private Long modelId;

    private String modelName;

    private Long brandId;

    private String brandName;

    private Long deviceTypeId;

    private String deviceTypeName;

    private String serialNumber;

    private Date dateOfSale;

    private String salesmanName;

    private String salesmanPhone;

    private String buyerName;

    private String buyerSurname;

    private String buyerPhone;

    private RepairCategory category;

    private RepairStatus status;

    private String defectDescription;

    private String repairWorkshopRepairNumber;

    private Date startDate;

    private Date endDate;

    private Date deliveryDate;

    private Long repairTypeId;

    private String repairTypeName;

    private String repairTypeCode;

    private String repairTypeLevel;

    private boolean isDeleted;

    private String modelDescription;

}
