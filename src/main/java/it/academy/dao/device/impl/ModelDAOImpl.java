package it.academy.dao.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.Model;
import it.academy.utils.dao.TransactionManger;

import javax.persistence.TypedQuery;
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
    public boolean checkIfExist(Model model) {
        TypedQuery<Long> query = entityManager().createQuery(CHECK_MODEL, Long.class);
        long modelId = model.getId() != null ? model.getId() : 0L;
        return query.setParameter(OBJECT_ID, modelId)
                .setParameter(OBJECT_NAME, model.getName())
                .setParameter(BRAND_ID, model.getBrand().getId())
                .setParameter(FIND_MODEL_DEVICE_TYPE_ID, model.getType().getId())
                .getSingleResult() != 0;
    }
}
