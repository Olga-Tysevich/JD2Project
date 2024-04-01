package it.academy.dto.req.device;

import it.academy.dto.req.repair_workshop.RepairWorkshopDTOReq;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class BrandDTOReq {

    private Long id;

    private String name;

    private Boolean isActive;

    private Set<RepairWorkshopDTOReq> repairWorkshops;
}
