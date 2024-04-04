package it.academy.dao;

import it.academy.entities.device.components.Model;

import java.util.List;

public interface ModelDAO extends DAO<Model, Long> {

    List<Model> findAllByBrandId(long brandId);

}
