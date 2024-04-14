package it.academy.dao.impl;

import it.academy.dao.ModelDAO;
import it.academy.entities.device.components.Model;
import java.util.List;

import static it.academy.utils.Constants.*;

public class ModelDAOImpl extends DAOImpl<Model, Long> implements ModelDAO {

    public ModelDAOImpl() {
        super(Model.class);
    }

    @Override
    public List<Model> findAllByBrandId(long brandId) {
        return entityManager()
                .createQuery(FIND_MODEL_BY_BRAND_ID, Model.class)
                .setParameter(FIND_MODEL_BRAND_ID, brandId)
                .getResultList();
    }

    @Override
    public Model getModel(Model model) {
        return entityManager()
                .createQuery(FIND_MODEL, Model.class)
                .setParameter(MODEL_NAME, model.getName())
                .setParameter(FIND_MODEL_BRAND_ID, model.getBrand().getId())
                .setParameter(FIND_MODEL_DEVICE_TYPE_ID, model.getType().getId())
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
