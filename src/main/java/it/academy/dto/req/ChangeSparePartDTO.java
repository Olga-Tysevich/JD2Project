package it.academy.dto.req;

import it.academy.dto.resp.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeSparePartDTO {

    private AccountDTO currentAccount;

    private List<Long> deviceTypeIdList;

    private Long id;

    private String name;

    private Boolean isActive;

}
