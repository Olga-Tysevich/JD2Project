package it.academy.services.device;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.BrandDTO;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

import java.util.List;

public interface BrandService {

    RespDTO<BrandDTO> addBrand(BrandDTO req);

    RespDTO<BrandDTO> changeBrand(BrandDTO req);

    BrandDTO findBrand(long id);

    List<BrandDTO> findBrands();

    RespListDTO<BrandDTO> findBrands(int pageNumber, int listSize);

    RespListDTO<BrandDTO> findBrands(ParametersForSearchDTO parameters);

}
