package it.academy.dto.repair;

import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceDTO;
import it.academy.dto.device.ModelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairFormDTO {

    private Map<Long, String> serviceCenters;

    private Long currentServiceCenterId;

    private List<BrandDTO> brands;

    private List<ModelDTO> models;

    private DeviceDTO device;

    private Long currentBrandId;

}
