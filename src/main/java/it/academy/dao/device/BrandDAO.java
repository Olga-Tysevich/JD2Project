package it.academy.dao.device;

import it.academy.dao.ComponentDAO;
import it.academy.entities.device.Brand;

import java.util.List;

public interface BrandDAO extends ComponentDAO<Brand, Long> {

    List<Brand> findBrandsWithModels();

}
