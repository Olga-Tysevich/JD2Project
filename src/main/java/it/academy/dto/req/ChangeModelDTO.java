package it.academy.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeModelDTO {

    private Long id;

    private String name;

    private Long brandId;

    private Long deviceTypeId;

    private Boolean isActive;

}
