package it.academy.dto.repair;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import lombok.*;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairFormDTO {

    @ToString.Exclude
    private Map<Long, String> serviceCenters;

    private Long selectedBrandId;

    @ToString.Exclude
    private List<BrandDTO> brands;

    @ToString.Exclude
    private List<ModelDTO> models;

    private DeviceDTO device;

    private Long currentBrandId;

}
