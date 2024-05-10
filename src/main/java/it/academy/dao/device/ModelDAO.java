package it.academy.dao.device;

import it.academy.dao.DAO;
import it.academy.entities.device.Model;

import java.util.List;

public interface ModelDAO extends DAO<Model, Long> {

    List<Model> findActiveByBrandId(long brandId);

    boolean checkIfExist(Model model);

}
