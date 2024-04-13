package it.academy.dto.account.req;

import it.academy.dto.account.resp.AccountDTO;
import it.academy.entities.account.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {

    private AccountDTO currentAccount;

    private RoleEnum role;

    private String email;

    private String userName;

    private String userSurname;

    private String password;

    private String confirmPassword;

    private Long serviceCenterId;

}
