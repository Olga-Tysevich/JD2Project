package it.academy.dto.device.req;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DeviceTypeDTO {

    private Long id;

    private String name;

    private Boolean isActive;

}
