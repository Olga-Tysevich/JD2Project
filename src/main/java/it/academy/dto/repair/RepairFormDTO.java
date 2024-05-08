package it.academy.dto.repair;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.ModelDTO;
import it.academy.dto.spare_part_order.SparePartOrderDTO;
import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairFormDTO {

    private List<BrandDTO> brands;

    private List<ModelDTO> models;

    private List<RepairTypeDTO> repairTypes;

    private Long currentBrandId;

    private RepairDTO repairDTO;

    private List<SparePartOrderDTO> orders;


}
