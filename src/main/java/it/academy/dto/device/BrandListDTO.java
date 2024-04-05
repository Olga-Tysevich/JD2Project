package it.academy.dto.device;

import it.academy.dto.device.BrandDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BrandListDTO {

    private List<BrandDTO> brandList;
}
