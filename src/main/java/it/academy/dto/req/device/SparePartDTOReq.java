package it.academy.dto.req.device;

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
