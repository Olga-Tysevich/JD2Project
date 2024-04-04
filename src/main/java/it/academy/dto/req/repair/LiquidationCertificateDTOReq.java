package it.academy.dto.req.repair;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LiquidationCertificateDTOReq {

    private Long id;

    private String number;

    private RepairDTO repair;

    private LiquidationCauseDTOReq cause;

}
