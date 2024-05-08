package it.academy.dao.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.device.Model;
import it.academy.utils.TransactionManger;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.constants.Constants.*;

public class ModelDAOImpl extends DAOImpl<Model, Long> implements ModelDAO {

    public ModelDAOImpl(TransactionManger manger) {
        super(manger, Model.class);
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

    @Override
    public List<Model> findByComponent(String componentName, String input, int pageNumber, int listSize) {
        CriteriaQuery<Model> find = criteriaBuilder().createQuery(Model.class);
        Root<Model> root = find.from(Model.class);

        find.select(root)
                .where(criteriaBuilder()
                        .like(root.get(componentName)
                                .get(OBJECT_NAME), String.format(LIKE_QUERY_PATTERN, input)));

        return entityManager().createQuery(find)
                .setFirstResult((pageNumber - 1) * listSize)
                .setMaxResults(listSize)
                .getResultList();
    }

    @Override
    public long getNumberOfEntriesByComponent(String componentName, String input) {
        String query = String.format(GET_NUMBER_OF_MODELS_BY_COMPONENT, componentName);
        TypedQuery<Long> count = entityManager().createQuery(query, Long.class);
        count.setParameter(PARAMETER_VALUE, String.format(LIKE_QUERY_PATTERN, input));
        return count.getSingleResult();
    }
}
