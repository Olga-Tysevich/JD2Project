package it.academy.services;

import it.academy.dto.BrandDTO;

import java.util.List;

public interface BrandService {

    BrandDTO findBrand(long id);

    List<BrandDTO> findBrands();

}
