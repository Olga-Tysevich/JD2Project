package it.academy.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountFormDTO {

    private List<ServiceCenterDTO> serviceCenters;

    private String message;

}
