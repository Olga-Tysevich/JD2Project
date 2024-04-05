package it.academy.dto.repair;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepairTypeDTO {

    private Long id;

    private String name;

    private String code;

    private String level;

}