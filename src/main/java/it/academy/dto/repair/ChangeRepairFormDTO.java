package it.academy.dto.repair;

import it.academy.dto.spare_part.SparePartOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRepairFormDTO {

    private RepairDTO repairDTO;

    private RepairFormDTO repairFormDTO;

     private List<SparePartOrderDTO> orders;

}
