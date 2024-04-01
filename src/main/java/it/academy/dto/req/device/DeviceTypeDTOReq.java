package it.academy.dto.req.device;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DeviceTypeDTOReq {

    private Long id;

    private String name;

    private Boolean isActive;

    private Set<DefectDTOReq> defects;

}
