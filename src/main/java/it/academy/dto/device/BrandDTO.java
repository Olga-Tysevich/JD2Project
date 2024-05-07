package it.academy.dto.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private Long id;

    private String name;

    private Boolean isActive;

    private String errorMessage;

}
