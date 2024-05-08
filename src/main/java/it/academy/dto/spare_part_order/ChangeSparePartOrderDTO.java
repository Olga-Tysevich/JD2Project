package it.academy.dto.spare_part_order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeSparePartOrderDTO {

    private Long id;

    private Date orderDate;

    private Date departureDate;

    private Date deliveryDate;

}
