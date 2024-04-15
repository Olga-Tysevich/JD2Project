package it.academy.dto.req;

import it.academy.dto.resp.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairFormReq {

    private AccountDTO currentAccount;

    private Long brandId;

    private Long repairId;

}
