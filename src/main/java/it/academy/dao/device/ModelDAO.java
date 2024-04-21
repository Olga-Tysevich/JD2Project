package it.academy.dao.device;

import it.academy.dao.DAO;
import it.academy.entities.Model;

import java.util.List;

public interface ModelDAO extends DAO<Model, Long> {

    List<Model> findAllByBrandId(long brandId);

    List<Model> findActiveByBrandId(long brandId);

    boolean checkIfExist(Model model);

}
