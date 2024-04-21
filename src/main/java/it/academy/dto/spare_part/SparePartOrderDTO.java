package it.academy.dto.spare_part;

import it.academy.dto.spare_part.SparePartForChangeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparePartOrderDTO {

    private Long id;

    private Long repairId;

    private String serviceCenterRepairNumber;

    private Date orderDate;

    private Date departureDate;

    private Date deliveryDate;

    private Map<SparePartForChangeDTO, Integer> spareParts;

}
