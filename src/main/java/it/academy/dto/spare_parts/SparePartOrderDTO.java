package it.academy.dto.spare_parts;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;
import java.util.Map;

@Data
@Builder
public class SparePartOrderDTO {

    private Long id;

    private Long repairId;

    private String serviceCenterRepairNumber;

    private Date orderDate;

    private Date departureDate;

    private Date deliveryDate;

    private Map<SparePartDTO, Integer> spareParts;

}
