package it.academy.dto.resp;

import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.DeviceTypeDTO;
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
