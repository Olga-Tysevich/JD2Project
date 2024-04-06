package it.academy.dto.spare_parts;

import it.academy.entities.spare_parts_order.SparePart;
import lombok.Builder;
import lombok.Data;
import java.sql.Date;
import java.util.Map;

@Data
@Builder
public class SparePartOrderDTO {

    private Long id;

    private Long repairId;

    private Date orderDate;

    private Date departureDate;

    private Date deliveryDate;

    private Map<SparePart, Integer> spareParts;

}
