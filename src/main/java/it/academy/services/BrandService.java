package it.academy.services;

import it.academy.dto.req.BrandDTO;
import it.academy.dto.resp.AccountDTO;
import it.academy.dto.resp.ListForPage;
import it.academy.exceptions.common.AccessDenied;

import java.util.List;

public interface BrandService {

    void createBrand(BrandDTO brand) throws AccessDenied;

    void updateBrand(BrandDTO brand) throws AccessDenied;

    BrandDTO findBrand(long id);

    List<BrandDTO> findBrands(AccountDTO accountDTO);

    ListForPage<BrandDTO> findBrands(AccountDTO accountDTO, int pageNumber);

    ListForPage<BrandDTO> findBrands(AccountDTO accountDTO, int pageNumber, String filter, String input);

}
