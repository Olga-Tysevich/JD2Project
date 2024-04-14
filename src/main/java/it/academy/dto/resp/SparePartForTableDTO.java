package it.academy.dto.resp;

import it.academy.dto.req.DeviceTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SparePartForTableDTO {

    private AccountDTO currentAccount;

    private Long id;

    private String name;

    private Boolean isActive;

    private List<DeviceTypeDTO> relatedDeviceTypes;

    private List<DeviceTypeDTO> allDeviceTypes;

}
