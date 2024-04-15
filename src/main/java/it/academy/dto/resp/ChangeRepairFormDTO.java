package it.academy.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRepairFormDTO {

    private RepairDTO repairDTO;

    private RepairFormDTO repairFormDTO;

}
