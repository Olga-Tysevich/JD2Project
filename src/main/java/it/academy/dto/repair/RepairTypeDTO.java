package it.academy.dto.repair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairTypeDTO {

    private Long id;

    private String name;

    private String code;

    private String level;

    private Boolean isActive;

}
