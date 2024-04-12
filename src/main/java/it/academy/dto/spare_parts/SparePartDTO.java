package it.academy.dto.spare_parts;

import it.academy.dto.device.req.DeviceTypeDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SparePartDTO {

    private Long id;

    private String name;

    private List<DeviceTypeDTO> allDeviceTypes;

    private List<DeviceTypeDTO> relatedDeviceTypes;

}
