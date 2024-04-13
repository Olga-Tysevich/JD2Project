package it.academy.dto.device.req;

import it.academy.dto.account.resp.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceTypeDTO {

    private AccountDTO currentAccount;

    private Long id;

    private String name;

    private Boolean isActive;

}
