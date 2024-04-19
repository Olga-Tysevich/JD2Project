package it.academy.dao.device;

import it.academy.entities.Model;

import java.util.List;

public interface ModelDAO extends DeviceComponentDAO<Model, Long> {

    List<Model> findAllByBrandId(long brandId);

    List<Model> findActiveByBrandId(long brandId);

    Model getModel(Model model);

}
