package it.academy.dto.req.repair;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepairTypeDTOReq {

    private Long id;

    private String name;

    private String code;

    private String level;

}

