package it.academy.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrandListDTO {

    private List<BrandDTO> brandList;
}
