package it.academy.dto.device.req;

import it.academy.dto.account.resp.AccountDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelDTO {

    private AccountDTO currentAccount;

    private Long id;

    private String name;

    private Long brandId;

    private String brandName;

    private Long deviceTypeId;

    private String deviceTypeName;

    private Boolean isActive;

}
