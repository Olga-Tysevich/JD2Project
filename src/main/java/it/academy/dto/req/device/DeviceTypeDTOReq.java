package it.academy.dto.req.device;

import it.academy.dto.req.repair.SparePartDTOReq;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DeviceTypeDTOReq {

    private Long id;

    private String name;

    private Boolean isActive;

    private Set<SparePartDTOReq> spareParts;

    private Set<DefectDTOReq> defects;

}
