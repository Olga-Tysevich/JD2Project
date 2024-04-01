package it.academy.dto.req.device;

import lombok.Builder;
import lombok.Data;
import java.util.Set;

@Data
@Builder
public class DefectDTOReq {

    private Long id;

    private String description;

    private Set<DeviceTypeDTOReq> typeSet;
}
