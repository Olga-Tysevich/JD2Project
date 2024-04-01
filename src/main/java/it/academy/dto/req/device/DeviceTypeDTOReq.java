package it.academy.dto.req.device;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceTypeDTOReq {

    private Long id;

    private String name;

    private Boolean isActive;

}
