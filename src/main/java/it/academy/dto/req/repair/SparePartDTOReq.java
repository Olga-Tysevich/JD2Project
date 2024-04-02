package it.academy.dto.req.repair;

import it.academy.dto.req.device.DeviceTypeDTOReq;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SparePartDTOReq {

    private Long id;

    private String name;

    private Set<DeviceTypeDTOReq> typeSet;

}
