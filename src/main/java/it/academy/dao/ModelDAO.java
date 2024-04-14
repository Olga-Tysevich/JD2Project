package it.academy.dao;

import it.academy.entities.Model;

import java.util.List;

public interface ModelDAO extends DAO<Model, Long> {

    List<Model> findAllByBrandId(long brandId);

    Model getModel(Model model);

}
