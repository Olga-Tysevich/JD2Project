package it.academy.dto.req;

import it.academy.entities.account.role.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTOReq {

    private Long id;

    private Boolean isActive;

    private String email;

    private String userName;

    private String userSurname;

    private String password;

    private String confirmPassword;

    private Role role;

}
