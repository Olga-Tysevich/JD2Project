package it.academy.dto.device.resp;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.dto.device.BrandDTO;
import it.academy.dto.device.DeviceTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelDTO {

    private AccountDTO currentAccount;

    private Long id;

    private String name;

    private Long brandId;

    private String brandName;

    private Long deviceTypeId;

    private String deviceTypeName;

    private Boolean isActive;

    private List<DeviceTypeDTO> deviceTypes;

    private List<BrandDTO> brands;

}
