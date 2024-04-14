package it.academy.dto.req;

import it.academy.dto.resp.AccountDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class SparePartDTO {

    private AccountDTO currentAccount;

    private List<Long> deviceTypeIdList;

    private Long id;

    private String name;

}
