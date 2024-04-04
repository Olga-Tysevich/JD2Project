package it.academy.dto.req.repair;

import it.academy.dto.req.device.SparePartDTOReq;
import lombok.Builder;
import lombok.Data;
import java.sql.Date;
import java.util.Map;

@Data
@Builder
public class SparePartsOrderDTOReq {

    private Long id;

    private RepairDTO repair;

    private Date orderDate;

    private Date departureDate;

    private Date deliveryDate;

    private Map<SparePartDTOReq, Integer> spareParts;

}
