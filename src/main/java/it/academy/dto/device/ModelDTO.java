package it.academy.dto.device;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelDTO {

    private Long id;

    private String name;

    private Long brandId;

    private String brandName;

    private Long deviceTypeId;

    private String deviceTypeName;
}
