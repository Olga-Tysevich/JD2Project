package it.academy.dto.req.repair;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiquidationCauseDTOReq {

    private Long id;

    private String name;

}
