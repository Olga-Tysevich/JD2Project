package it.academy.dto.device;

import it.academy.dto.account.resp.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private AccountDTO currentAccount;

    private Long id;

    private String name;

    private Boolean isActive;

}
