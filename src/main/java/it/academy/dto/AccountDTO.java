package it.academy.dto;

import it.academy.dto.service_center.ServiceCenterDTO;
import it.academy.entities.RoleEnum;
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

    private RoleEnum role;

    private ServiceCenterDTO serviceCenter;

}
