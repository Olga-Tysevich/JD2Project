package it.academy.dao.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.DAOImpl;
import it.academy.entities.device.components.Model;

public class ModelDAOImpl extends DAOImpl<Model, Long> implements ModelDAO {

    public ModelDAOImpl() {
        super(Model.class);

    }
}
