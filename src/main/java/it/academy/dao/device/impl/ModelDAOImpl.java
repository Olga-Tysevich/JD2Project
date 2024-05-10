package it.academy.dao.device.impl;

import it.academy.dao.device.ModelDAO;
import it.academy.dao.impl.DAOImpl;
import it.academy.entities.device.*;
import it.academy.utils.TransactionManger;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    protected Predicate[] createPredicate(From<?, ?> root, Map<String, String> searchParam) {
        List<Predicate> predicates = new ArrayList<>();
        searchParam.forEach((key, value) -> {
            if (!StringUtils.isBlank(key) && !StringUtils.isBlank(value)) {
                String filter = key.trim();
                String input = value.trim().toLowerCase();
                if (Model_.BRAND.equals(filter)) {
                    Join<Model, Brand> brandJoin = root.join(Model_.BRAND);
                    Class<?> fClass = brandJoin.get(Brand_.NAME).getJavaType();
                    addSimpleAttributes(predicates, brandJoin, fClass, Brand_.NAME, input);
                } else if (Model_.TYPE.equals(filter)) {
                    Join<Model, DeviceType> deviceTypeJoin = root.join(Model_.TYPE);
                    Class<?> fClass = deviceTypeJoin.get(DeviceType_.NAME).getJavaType();
                    addSimpleAttributes(predicates, deviceTypeJoin, fClass, DeviceType_.NAME, input);
                } else {
                    Class<?> fClass = root.get(filter).getJavaType();
                    addSimpleAttributes(predicates, root, fClass, filter, input);
                }
            }
        });

        return predicates.toArray(new Predicate[0]);
    }
}
