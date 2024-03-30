package it.academy.dto.req.account;

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

}
