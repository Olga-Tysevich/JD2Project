package it.academy.dto.account;

import it.academy.utils.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;

    private Boolean isActive;

    private String email;

    private String userName;

    private String userSurname;

    private String password;

    private String passwordConfirm;

    private RoleEnum role;

    private Long serviceCenterId;

    private String serviceCenterName;

    private String errorMessage;

}
