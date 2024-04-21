package it.academy.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelDTO {

    private Long id;

    private String name;

    private Long brandId;

    private String brandName;

    private Long deviceTypeId;

    private String deviceTypeName;

    private Boolean isActive;

}
