package it.academy.dto.req.account;

import it.academy.entities.account.role.Role;
import it.academy.entities.service_center.ServiceCenter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {
    private Long id;

    private Boolean isActive;

    private String email;

    private String userName;

    private String userSurname;

    private Role role;

    private ServiceCenter serviceCenter;


}
