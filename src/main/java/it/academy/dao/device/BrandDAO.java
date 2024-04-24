package it.academy.dao.device;

import it.academy.dao.DAO;
import it.academy.entities.device.Brand;

import java.util.List;

public interface BrandDAO extends DAO<Brand, Long> {

    List<Brand> findBrandsWithModels();

}
