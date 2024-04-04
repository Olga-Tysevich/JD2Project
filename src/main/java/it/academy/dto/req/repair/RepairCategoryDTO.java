package it.academy.dto.req.repair;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RepairCategoryDTO {

    private Long id;

    private String name;

}
