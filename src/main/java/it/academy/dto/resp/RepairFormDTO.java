package it.academy.dto.resp;

import it.academy.dto.req.BrandDTO;
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
