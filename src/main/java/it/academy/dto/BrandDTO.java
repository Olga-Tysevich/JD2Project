package it.academy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandDTO {

    private Long id;

    private String name;

    private Boolean isActive;
}
