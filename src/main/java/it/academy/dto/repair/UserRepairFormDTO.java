package it.academy.dto.repair;

import it.academy.dto.spare_part_order.SparePartOrderDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRepairFormDTO {

    private RepairDTO repairDTO;

    private List<SparePartOrderDTO> orders;

    private List<RepairTypeDTO> repairTypes;

}
