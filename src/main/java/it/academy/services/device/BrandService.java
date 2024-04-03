package it.academy.services.device;

import it.academy.dto.common.ParametersForSearchDTO;
import it.academy.dto.req.device.BrandDTOReq;
import it.academy.dto.resp.RespDTO;
import it.academy.dto.resp.RespListDTO;

public interface BrandService {

    RespDTO<BrandDTOReq> addBrand(BrandDTOReq req);

    RespDTO<BrandDTOReq> changeBrand(BrandDTOReq req);

    RespListDTO<BrandDTOReq> findBrands();

    RespListDTO<BrandDTOReq> findBrands(int pageNumber, int listSize);

    RespListDTO<BrandDTOReq> findBrands(ParametersForSearchDTO parameters);

}
