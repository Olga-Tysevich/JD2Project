package it.academy.dto.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelForChangeDTO {

    private List<DeviceTypeDTO> deviceTypes;

    private List<BrandDTO> brands;

    private ModelDTO modelDTO;

}
