package it.academy.dto.repair_page_N;

import it.academy.dto.req.device.BrandDTO;
import it.academy.dto.req.device.ModelDTO;
import it.academy.dto.req.repair.RepairCategoryDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RepairPageDataDTO {

    private int pageNumber;

    private List<RepairCategoryDTO> categories;

    private List<BrandDTO> brands;

    private BrandDTO currentBrand;

    private List<ModelDTO> models;


}
