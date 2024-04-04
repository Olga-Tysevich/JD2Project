package it.academy.dao.device;

import it.academy.dao.DAO;
import it.academy.entities.device.components.Brand;
import it.academy.entities.device.components.Model;

import java.util.List;

public interface ModelDAO extends DAO<Model, Long> {

    List<Model> findAllByBrandId(long brandId);

}
