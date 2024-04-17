package it.academy.dao.impl;

import it.academy.dao.ModelDAO;
import it.academy.entities.Model;
import it.academy.utils.dao.TransactionManger;

import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class ModelDAOImpl extends DAOImpl<Model, Long> implements ModelDAO {

    public ModelDAOImpl() {
        super(Model.class);
    }

    public ModelDAOImpl(TransactionManger manger) {
        super(manger, Model.class);
    }

    @Override
    public List<Model> findAllByBrandId(long brandId) {
        return entityManager()
                .createQuery(FIND_MODEL_BY_BRAND_ID, Model.class)
                .setParameter(BRAND_ID, brandId)
                .getResultList();
    }

    @Override
    public List<Model> findActiveByBrandId(long brandId) {
        return entityManager()
                .createQuery(FIND_ACTIVE_MODEL_BY_BRAND_ID, Model.class)
                .setParameter(BRAND_ID, brandId)
                .setParameter(IS_ACTIVE, true)
                .getResultList();
    }

    @Override
    public Model getModel(Model model) {
        return entityManager()
                .createQuery(FIND_MODEL, Model.class)
                .setParameter(OBJECT_NAME, model.getName())
                .setParameter(BRAND_ID, model.getBrand().getId())
                .setParameter(FIND_MODEL_DEVICE_TYPE_ID, model.getType().getId())
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
