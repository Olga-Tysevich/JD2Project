package it.academy.dao.device;

import it.academy.dao.DAO;
import it.academy.entities.device.components.Brand;

import java.util.List;

public interface BrandDAO extends DAO<Brand, Long> {

    List<Brand> findActiveBrands(boolean isActive);

    List<Brand> findActiveBrandsForPage(boolean isActive, int pageNumber, int listSize);

    List<Brand> findActiveBrandsForPage(boolean isActive, int pageNumber, int listSize,
                                        String filter, String value);

}
