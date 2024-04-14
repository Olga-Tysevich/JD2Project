package it.academy.dto.resp;

import it.academy.dto.req.BrandDTO;
import it.academy.dto.req.DeviceTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelListDTO {

    private List<DeviceTypeDTO> deviceTypes;

    private List<BrandDTO> brands;

    private List<ModelDTO> models;

}
