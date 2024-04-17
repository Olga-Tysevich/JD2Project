package it.academy.dto.req;

import it.academy.utils.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAccountDTO {

    private Long id;

    private Boolean isActive;

    private String email;

    private String userName;

    private String userSurname;

    private String password;

    private RoleEnum role;

    private Long serviceCenterId;

}
